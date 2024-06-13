import java.util.*;
import java.io.*;

public class Main {
    static class Person{
        int num, r, c, h, w, k, damage; // k = 체력
        boolean isDead;

        public Person(int num, int r, int c, int h, int w, int k){
            this.num = num;
            this.r = r;
            this.c = c;
            this.h = h;
            this.w = w;
            this.k = k;
            this.damage = 0;
            this.isDead = false;
        }

        public String toString(){
            return num + "번 기사: ("+ r+ " , "+ c + ") "+ h+ " * " + w + " k = "
             + k + " isDead="+ isDead + " damage="+ damage;
        }
        
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int L, N, Q;
    static int [][] map; // 맵 정보 
    static int [][] mapPersonNum; // 기사들의 번호 정보
    static HashMap<Integer, Person> personHMap = new HashMap<>(); 
    static int [][] dirs = {{-1,0}, {0,1}, {1,0}, {0, -1}}; // up, right, down, left

    public static void main(String[] args) throws Exception{
        // input 
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        map = new int[L+1][L+1];
        mapPersonNum = new int[L+1][L+1];
        for(int i = 1; i <=L; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= L; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }   
        }
        for(int i = 0; i < N; i++){
            int num = i+1;
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            personHMap.put(num, new Person(num, r, c, h, w, k));
            markPersonInMapPersonNum(personHMap.get(num), mapPersonNum);
        }

        // main process
        for(int q = 1; q <= Q; q++){
            st = new StringTokenizer(br.readLine());
            int personNum = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            // sout(personNum+ " 기사 -> dir = "+ dir );
            
            if( personHMap.get(personNum).isDead ) continue;
            process(personNum, dir); //기사의 이동
        }
        // 생존한 기사들의 총 damage 합 출력
        printTotalDamageALive();
    }
    static void printTotalDamageALive(){
        int sum = 0;
        for(Map.Entry<Integer, Person> entry : personHMap.entrySet()){
            if(entry.getValue().isDead) continue;
            sum += entry.getValue().damage;
        }
        sout(sum+"");
    }

    static void process(int num, int dir){
        Person person = personHMap.get(num);
        if(canMove(num, dir, map)){
            // sout("움직일 수 있다!");
            if(exitsPersonNextPos(num, dir, mapPersonNum)){ // 다른 기사 존재
                // sout(" 다른 기사 존재 ");
                mapPersonNum = interact(person, dir); // 연쇄적 작용
            }else{ 
                // mapPersonNum = move(person, dir);
            } 
        }
    }

    // 연쇄적으로 밀려남
    static int[][] interact(Person startPerson, int dir){
        int [][] tmp = new int [L+1][L+1];
        // 시작 기사를 중점으로  가장 아래에 있는 기사부터 밀려난다.
        // 움직여야하는 기사 리스트를 받는다. 
        ArrayDeque<Integer> haveToMovePeople = getHaveToMove(startPerson.num, dir);
        // sout(haveToMovePeople.toString());

        // 움직인다.
        ArrayDeque<Integer> cp = haveToMovePeople.clone();
        while(!cp.isEmpty()){
            Person person = personHMap.get(cp.pollFirst());
            int endR = person.r + person.h - 1;
            int endC = person.c + person.w - 1;
            for(int r = person.r; r <= endR; r++){
                for(int c = person.c; c <= endC; c++){
                    int nr = r + dirs[dir][0];
                    int nc = c + dirs[dir][1];
                    // sout("고려 위치 :  nr = "+ nr + " nc  = "+ nc);
                    tmp[nr][nc] = person.num;
                    
                }
            }
            int nextR = person.r + dirs[dir][0];
            int nextC = person.c + dirs[dir][1];
            personHMap.put(person.num, new Person(person.num, nextR, nextC, person.h, person.w, person.k));
            
        }
        mapPersonNum = tmp;

        // 밀려난 모든 기사들의 피해 발생
        while(!haveToMovePeople.isEmpty()){
            Person person = personHMap.get(haveToMovePeople.pollFirst());
            int willDamage = 0;
            int endR = person.r + person.h - 1;
            int endC = person.c + person.w - 1;
            for(int i = person.r; i <= endR; i++){
                for(int j = person.c; j <=endC; j++){
                    if(map[i][j]  == 1 ){
                        willDamage += 1;
                    }
                }
            }
            person.damage += willDamage;
            person.k -= willDamage;
            if(person.k <= 0){
                //disappear
                person.isDead = true;
                remarkPersonInMapPersonNum(person,mapPersonNum);

            }

        }

        // if 체력이 0 이하이면 fail 후, MAP에서 삭제
        return tmp;
    }

    // 움직여야하는 기사 리스트를 받는다. 
    static ArrayDeque<Integer> getHaveToMove(int startNum, int dir){
        ArrayDeque<Integer> result = new ArrayDeque<>();
        ArrayDeque<Integer> q = new ArrayDeque<>();
        int [] ch = new int[N+1]; // 고려한 기사 체크
        ch[startNum] = 1;
        q.addLast(startNum);
        result.addLast(startNum);
        // printArr(mapPersonNum, "mapPersonNum");
        // sout(Arrays.toString(ch));

        while(!q.isEmpty()){
            int num =  q.pollFirst();
            Person out = personHMap.get(num);
            // sout(" 탐색 ..."+out.toString());
            int endR = out.r + out.h - 1;
            int endC = out.c + out.w - 1;
            for(int r = out.r; r <= endR; r++){
                for(int c = out.c; c <= endC; c++){
                    int nr = r + dirs[dir][0];
                    int nc = c + dirs[dir][1];
                    // sout("탐색 ... "+ nr + " , "+ nc);
                    if(mapPersonNum[nr][nc] > 0 && ch[mapPersonNum[nr][nc]]==0 ){
                        ch[mapPersonNum[nr][nc]] = 1;
                        q.addLast(mapPersonNum[nr][nc]);
                        result.addLast(mapPersonNum[nr][nc]);
                    }
                }
            } 
        }
        return result;
    }
    static void markPersonInMapPersonNum(Person person, int [][] mapPersonNum){
        int endR = person.r + person.h - 1;
        int endC = person.c + person.w - 1;
        for(int r = person.r; r <= endR; r++){
            for(int c = person.c; c <= endC; c++){
                mapPersonNum[r][c] = person.num;
            }
        }

    }
    static void remarkPersonInMapPersonNum(Person person, int [][] mapPersonNum){
        int endR = person.r + person.h - 1;
        int endC = person.c + person.w - 1;
        for(int r = person.r; r <= endR; r++){
            for(int c = person.c; c <= endC; c++){
                mapPersonNum[r][c] = 0;
            }
        }

    }
    
    // 이동 방향 끝 벽 존재 여부
    static boolean canMove(int startPersonNum, int dir, int[][] map){
        // sout(" canMove.. dir = " + dir);
        // printArr(map, " map ");
        // printArr(mapPersonNum, "mapPersonNum");
        ArrayDeque<Integer> q = new ArrayDeque<>();
        int [] ch = new int[N+1]; // 고려한 기사 체크
        ch[startPersonNum] = 1;
        q.addLast(startPersonNum);

        while(!q.isEmpty()){
            int num =  q.pollFirst();
            Person out = personHMap.get(num);
            // sout(" 탐색 ..."+out.toString());
            // 모든 기사의 범위에 대해서, 중복은 무시
            int endR = out.r + out.h - 1;
            int endC = out.c + out.w - 1;
            for(int r = out.r; r <= endR; r++){
                for(int c = out.c; c <= endC; c++){
                    int nr = r + dirs[dir][0];
                    int nc = c + dirs[dir][1];
                    // sout("고려 위치 :  nr = "+ nr + " nc  = "+ nc);
                    if(!inArea(nr, nc) || map[nr][nc] == 2){
                        sout("움직일 수 X");
                        return false;
                    } 
                    if(mapPersonNum[nr][nc] > 0 && ch[mapPersonNum[nr][nc]]==0 ){
                        ch[mapPersonNum[nr][nc]] = 1;
                        q.addLast(mapPersonNum[nr][nc]);
                    }
                }
            } 
        }
        return true;
    }

    static boolean exitsPersonNextPos(int personNum, int dir, int [][] mapPersonNum){
        Person person = personHMap.get(personNum);
        int endR = person.r + person.h - 1;
        int endC = person.c + person.w - 1;
        for(int r = person.r; r <= endR; r++){
            for(int c = person.c; c <= endC; c++){
                int nr = r + dirs[dir][0];
                int nc = c + dirs[dir][1];
                if(mapPersonNum[nr][nc] != personNum && mapPersonNum[nr][nc] > 0){
                    return true;
                }
            }
        }
        return false;
    }

    // 한명의 기사만 이동
    static int[][] move(Person person, int dir){
        int [][] tmp = new int [L+1][L+1];
        // hm 에서도 수정
        // personNumMap에서도 수정
        return tmp;
    }

    static void printArr(int [][] arr, String msg){
        sout( " ======= "+ msg+  " ======= ");
        for(int i = 1; i< arr.length; i++){
            for(int j = 1; j <arr.length; j++){
                System.out.print(arr[i][j]+" ");

            }
            sout("");
        }
    }

    static boolean inArea(int r, int c){
        return r > 0 && c > 0 && r <= L && c <=L;
    }

    static void sout(String s){
        System.out.println(s);
    }

    
}