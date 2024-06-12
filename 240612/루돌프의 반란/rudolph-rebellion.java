import java.util.*;
import java.io.*;
import java.awt.*;
/*
need
- N*N 
- for(M개의 턴) 루톨프 1번 움직이고 2. 1번 산타 ~ P번 산타

- 루톨프의 움직임
    - 가장 가까운 산타를 향해 1칸 이동(게임에서 탈락하지 않은 산타, 기절 산타 상관 없음)
    - 우선순위 산타 선정 : 거리, r 좌표 내림차순, c 좌표 내림차순
    - 우선순위 산타에 대해 8방향 중 가장 가까워지는 방향 선택 후, 1칸 이동
    - if( 산타와 루돌프 같은 칸) -> 충돌

- 산타의 움직임
    - 1번부터 ~ P번까지 순서대로
    - 기절해있거나 격자 밖으로 빠져나가 게임에서 탈락한 산타들은 움직이지 않는다. 
    - 게임 밖 불가, 다른 산타 있는 칸 불가
    - 우선 순위 방향 선정 : 4방향 중 거리, 상우하좌  -> 우선 탐색
    - 현재 루돌프와 산타의 거리 
    - 1 이동 시, 루돌프로부터 가장 가까워지는 방향을 찾는다. 이동시 루돌프와 산타의 거리 (항상 가까워 질때만 갱신)
    - if ( 못찾았다면 ) 움직이지 않는다. 
    - if( 산타와 루돌프 같은 칸) -> 충돌
    
- 충돌(충돌한 사람, 충돌한 방향)
    - 산타와 루돌프가 같은 칸이라면  충돌 발생
    - if( 루돌프의 충돌) 산타.점수 += C;, 루돌프 방향으로 + C칸 이동
    - if( 산타의 충돌 ) 산타.점수 += D;, 자신의 반대 방향으로 + D칸 이동
    - if( 밀려난 위치가 게임 밖) 산타 탈락 return
    - if( 밀려난 칸에 다른 산타 존재) 상호작용 발생, 
    - 산타의 위치 변경
    - 산타는 기절

- 상호작용(산타 정보, 착지하는 칸, 방향)
    - 착지 되는 칸에 다른 산타가 있다면, -> 원래 있던 산타는 방향으로 이동
    - 연쇄적으로 작용
    - if( 밀려난 위치가 범위 밖) 산타 탈락

- 기절
    - 산타는 루돌프와의 충돌 후 기절
    - 현재 턴 + 1 번 턴까지 기절해있음, 기절 카운드로 적용할 수 있을 듯
    - 기절 도중 충돌 ok, 상호작용 ok 

- 게임 종료
    - for (~M)
        if(모든 산타가 탈락했다면 )-> 즉시 종료
        아직 탈락하지 않은 산타들에게 1점 점수 부여
        기절 점수 -1점 감점
    - 최종 점수 출력


*/
public class Main {
    static int N, M, P, C, D;
    static int rouR, rouC;

    static class Santa implements Comparable<Santa>{
        int num, r, c, score;
        int sleep;
        boolean isFail = false;
        public Santa(int num, int r, int c){
            this.num = num;
            this.r = r;
            this.c = c;
        }
        public void startSleep(){
            this.sleep = 2;
        }
        public boolean isFail(){
            return isFail;
        }
        public boolean isSleeping(){
            return sleep > 0;
        }
        public int compareTo(Santa other){
            if(this.r > other.r) return -1;
            else if(this.r == other.r){
                if(this.c > other.c) return -1;
                else return 0;
            }else{
                return 1;
            }
        }
        public String toString() {
            return "산타 번호: "+ num + " isFail : "+isFail +" ( " + r +" , "+ c +" ) "+ " 남은 기절 수 : "+ sleep;
        }

    }
    
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static HashMap<Number, Santa> santaHMap = new HashMap<>();
    static int [][] rouDirs = {{1,0},{-1,0}, {0,-1}, {0,1}, {-1,1}, {1,1}, {-1,-1}, {1,-1}};
    static int [][] santaDirs = {{-1,0}, {0, 1}, {1,0}, {-1,0}};
    
    public static void main(String[] args) throws Exception{
        input();
        for(int i = 1; i <= M; i++){
            sout(i+" th turn ...");
            moveRoudole();
            // moveSantas();
            if(allSantaAreFail()){
                break;
            }
            addOneScoreNotFailedSantas();
            reduceSleep();
        }  
        
    }

    static void moveRoudole(){

        Santa targetSanta = null;
        int minDist = Integer.MAX_VALUE;
        // 우선 순위 산타 선정
        for(int num = 1; num <= P; num++){
            Santa santa = santaHMap.get(num);
            int dist = getDist(santa.r, santa.c, rouR, rouC);
            if( dist < minDist || (dist == minDist && santa.compareTo(targetSanta) < 0)){
                targetSanta = santa;
                minDist = dist;
            }
        }

        sout("우선순위 산타: "+ targetSanta.toString());
        // 우선순위 산타에 대해 8방향 중 가장 가까워지는 방향 선택 후, 1칸 이동
        for(int d = 0; d < 8; d++){
            int nextRouR = rouR + rouDirs[d][0];
            int nextRouC = rouC + rouDirs[d][1];
            int dist = 
        }
        int minDir = bfsFindMinDir(rouR, rouC, targetSanta.r, targetSanta.c, rouDirs);
        rouR += rouDirs[minDir][0];
        rouC += rouDirs[minDir][1];

        sout("선정된 루돌프 이동 위치 "+ rouR+" , "+ rouC+ " minDir = "+ minDir);

        // if( 산타와 루돌프 같은 칸) -> 충돌
    }
    

    static void moveSantas(){
        
    }
    
    







    static boolean inArea(int x, int y){
        return x > 0 && y > 0 && x <=N && y <=N;
    }


    static void reduceSleep(){
        for(Map.Entry<Number, Santa> entry : santaHMap.entrySet()){
            if(!entry.getValue().isSleeping()) entry.getValue().sleep -= 1;
        }
    }

    static int getDist(int r1, int c1, int r2, int c2){
        return (int) (Math.pow(r1-r2, 2) + Math.pow(c1-c2, 2));
    }

    static void addOneScoreNotFailedSantas(){
        for(Map.Entry<Number, Santa> entry : santaHMap.entrySet()){
            if(!entry.getValue().isFail()) entry.getValue().score += 1;
        }
    }
    static boolean allSantaAreFail(){
        for(Map.Entry<Number, Santa> entry : santaHMap.entrySet()){
            if(!entry.getValue().isFail()) return false;
        }
        return true;
    }


    static void input() throws Exception{
        st = new StringTokenizer(br.readLine());
        N = toInt(st.nextToken());
        M = toInt(st.nextToken());
        P = toInt(st.nextToken());
        C = toInt(st.nextToken());
        D = toInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        rouR = toInt(st.nextToken());
        rouC = toInt(st.nextToken());
        for(int i = 0; i <P; i++){
            st = new StringTokenizer(br.readLine());
            int num = toInt(st.nextToken());
            int r = toInt(st.nextToken());
            int c = toInt(st.nextToken());
            santaHMap.put(num, new Santa(num, r,c));
        }
        
    }

    static int toInt(String s){
        return Integer.parseInt(s);
    }

    static void sout(String s){
        System.out.println(s);
    }
    
}