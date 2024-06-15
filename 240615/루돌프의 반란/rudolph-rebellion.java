import java.io.*;
import java.util.*;
// 1. struct // 2. main(test) + sout ...+ methods(sample) // debug
public class Main {

    static class Santa implements Comparable<Santa>{
        int num, r, c, score;
        int sleep;
        boolean is_out;
        public Santa(int num, int r, int c){
            this.num = num;
            this.r = r;
            this.c = c;
            is_out = false;
        }
        public int compareTo(Santa other){
            if(this.r == other.r){
                return Integer.compare(other.c, this.c);
            }
            return Integer.compare(other.r, this.r);
        }
        public String toString(){
            return num+"번 산타: ("+ r + " , "+ c + " ) "+ "score = " + score
                    + " sleep = "+ sleep + " is_out = "+ is_out;
        }

    }
    static int N, M, P, C, D; // p = 산타의 수
    static int rouR, rouC; // roudolf 위치
    static HashMap<Integer, Santa> santaMap = new HashMap<>(); // 번호 별 산타들의 정보
    static StringBuilder sb = new StringBuilder();
    static int [][] santaNumMap; // 산타의 번호 map
    static int [][] dirs8 = {{-1,-1}, {-1, 0}, {-1,1}, {0,-1}, {0,1}, {1, -1}, {1,0}, {1,1}}; //
    static int [][] dirs4 = {{-1,0}, {0, 1}, {1,0}, {0, -1}};

    public static void main(String[] args) throws Exception{
        input();
        for(int i = 1; i <= M; i++){
            // System.out.println(i + " turn start ============");
            // printNow();
            // 루돌프 1번 move
            roudolfMove();

            // 순서대로 산타 move
            for(int num = 1; num <=P; num++){
                santaMove(num);
            }
            // if(산타 모두 탈락) -> 게임 종료
            if(allSantaFail()) break;
            // 탈락하지 않은 산타들에 대해 1점 점수 부여
            addOneScoreNotFailSantas();
            // 기절한 산타들은 카운트 -1
            reduceSleepCount();
            //  sout(i+" turn exited ....");
            //  printAllSanta();
            // printNow();
        }
        printEachSantaScores();
    }
    static void printNow(){
        System.out.println(" 루돌프 위치 : " + rouR +" , "+ rouC);
        printArr(santaNumMap, "santaNumMap");
    }

    static void crash(int isRoudolf, int dir, Santa santa, int rouR, int rouC){
        //  sout("!!!!!!!!!!!!! crash !!!!!!!!!!!!!");
        //  sout(" isRoudolf ?  "+ isRoudolf);
        //  sout("roudolf :"+ rouR+ " , "+rouC);
        //  sout(santa.toString());
        //  sout(" dir = "+ dir);

        //  printArr(santaNumMap, "santaNumMap");
        int x = rouR;
        int y = rouC;
        int nextR = -1;
        int nextC = -1;
        if(isRoudolf == 1){
            santa.score += C;
            nextR = x + (dirs8[dir][0]*C);
            nextC = y + (dirs8[dir][1]*C);

        }else{
            santa.score += D;
            dir = dir < 2 ? dir+2 : dir -2;
            nextR = x + (dirs4[dir][0]*D);
            nextC = y + (dirs4[dir][1]*D);
        }
        // sout("밀려난 next 위치 "+ nextR+ " , "+nextC);
        if(!inArea(nextR, nextC)) {
            santaFail(santa);
            return;
        }
        // 다른 산타가 있다면
        if(santaNumMap[nextR][nextC] > 0){ // 상호작용

            santaNumMap[x][y] = 0; // 이전 산타의 위치
            // sout("-------------상호작용 start-----------" + "목표위치 "+ " (" + nextR+ " , " + nextC+" )");
            //  printArr(santaNumMap, " santaNumMap");
            interact(isRoudolf, dir, nextR, nextC, santa); // 산타를 다음 방향에 착지시킨다.
            //  sout("-------------상호작용 end -----------");
            //  printArr(santaNumMap, " santaNumMap");
        }else{ // 없다면
            moveSantaInSantaNumMap(santa.r, santa.c, nextR, nextC);
            santa.r = nextR;
            santa.c = nextC;
        }
//
        // 기절
        santa.sleep = 2; // 애매

    }
    static void interact(int isRoudolf, int dir, int r, int c, Santa santa){
        // santa를 해당 위치에 착지 시킨다
        if(!inArea(r, c)){
            santaFail(santa);
            return;
        }
        if(santaNumMap[r][c] > 0){
            int num = santaNumMap[r][c];
            int nx = r;
            int ny = c;
            if(isRoudolf == 1){
                nx += dirs8[dir][0];
                ny += dirs8[dir][1];
            }else{
                nx += dirs4[dir][0];
                ny += dirs4[dir][1];
            }

            interact(isRoudolf, dir, nx, ny, santaMap.get(num));

        }
        santaNumMap[r][c] = santa.num;
        santa.r = r;
        santa.c = c;

    }
    static void printArr(int [][] map , String msg){
        sout(" --------- "+ msg + " -----------");
        for(int i = 0;i < map.length; i++){
            sout(Arrays.toString(map[i]));
        }
        sout(" --------- -----------");
    }

    static boolean existSameArea(Santa santa, int rouR, int rouC){
        return santa.r == rouR && santa.c == rouC;
    }

    static void santaFail(Santa santa){
        santa.is_out = true;
        santaNumMap[santa.r][santa.c] = 0;
    }
    static void moveSantaInSantaNumMap(int fx, int fy, int ex, int ey) {
        santaNumMap[ex][ey] = santaNumMap[fx][fy];
        santaNumMap[fx][fy] = 0;
    }

    static void santaMove(int num){
        // sout(" santa move... ");
        Santa santa = santaMap.get(num);
        // sout(santa.toString());
        if(santa.sleep > 0 || santa.is_out) return;
        // sout(" before "+ santaMap.get(num).toString());
        int oriDist = getDist(santa.r, santa.c, rouR , rouC);
        int minDist = Integer.MAX_VALUE;
        int minDir = -1;
        // 루돌프와 거리가 가장 가까워지는 방향으로 1칸 이동
        for(int d = 0; d < 4; d++){
            int nx = santa.r + dirs4[d][0];
            int ny = santa.c + dirs4[d][1];
            if(!inArea(nx, ny) || santaNumMap[nx][ny] > 0) continue;
            int dist = getDist(nx, ny, rouR , rouC);
            if( dist >= oriDist ) continue;
            if( dist < minDist ){
                minDist = dist;
                minDir = d;
            }
        }
        // 그러한 방향이 있을 경우에만 이동
        if(minDir != -1){
            assert santaNumMap[santa.r][santa.c] == 0;
            int nr = santa.r + dirs4[minDir][0];
            int nc = santa.c + dirs4[minDir][1];
            santaNumMap[santa.r][santa.c] = 0;
            santa.r = nr;
            santa.c = nc;
            santaNumMap[nr][nc] = santa.num;
            //  sout(" after "+ santaMap.get(num).toString());
            if(existSameArea(santa, rouR, rouC)) crash(0, minDir, santa, rouR, rouC);
        }

    }

    static void roudolfMove(){
        // System.out.println(" roudolf move  원래 위치: " + rouR + " , " + rouC);
        // 가장 높은 우선순위 산타를 정한다.
        Santa targetSanta = null;
        int minDist= Integer.MAX_VALUE;

        for(int num = 1; num <=P; num++){
            Santa santa = santaMap.get(num);
            if(santa.is_out) continue;
            int dist = getDist(rouR, rouC, santa.r, santa.c);
            if(isPrior(dist, santa, minDist, targetSanta)){
                targetSanta = santa;
                minDist = dist;
            }
        }

        //  sout("가장 높은 우선 순위 산타 : " + targetSanta.num + " " + targetSanta.r + " , " + targetSanta.c);

        // 8 방향 중 가장 가까워지는 방향을 찾는다.
        // int closestDir = findCloesetDirIn8Dirs(rouR, rouC, targetSanta.r, targetSanta.c);
        int closestDir = findCloesetDirIn8Dirs2(rouR, rouC, targetSanta.r, targetSanta.c);

        if(closestDir != -1){
            // 해당 방향으로 이동
            rouR += dirs8[closestDir][0];
            rouC += dirs8[closestDir][1];
            if(!inArea(rouR, rouC)){
                // sout(" 루돌프 이동 방향 이상");
                System.exit(0);
            }
            if(existSameArea(targetSanta, rouR, rouC)) crash(1, closestDir, targetSanta, rouR, rouC);
            // sout(" 찾은 방향 "+ closestDir+ " 이동한 루돌프 위치 : "+ rouR+ " , "+ rouC);
        }

    }
    static boolean inArea(int r, int c){
        return r > 0 && c > 0 && r <=N && c <=N;
    }

    static int findCloesetDirIn8Dirs2(int r, int c, int targetR, int targetC){
        int minDist = Integer.MAX_VALUE;
        int minDir = -1;
        
        for(int d = 0; d < 8; d++){
            int nx = r + dirs8[d][0];
            int ny = c + dirs8[d][1];
            if(!inArea(nx, ny)) continue;
            int dist = getDist(nx, ny, targetR , targetC);
            if( dist < minDist ){
                minDist = dist;
                minDir = d;
            }
        }
        return minDir;
    }

    // {{-1,-1}, {-1, 0}, {-1,1}, {0,-1}, {0,1}, {1, -1}, {1,0}, {1,1}};
    static int findCloesetDirIn8Dirs(int r, int c, int targetR, int targetC){
        if( targetR < r ){ // r < 0
            if( targetC < c ){ //c < 0
                return 0;
            }else if (targetC - c == 0){
                return 1;
            }else{
                return 2;
            }
        }else if(targetR == r){ // r == 0
            if( targetC < c ){ //c < 0
                return 3;
            }else if (targetC == c){
                return -1;
            }else{
                return 4;
            }
        }else{ //r < 0
            if( targetC < c){ //c > 0
                return 5;
            }else if (targetC == c ){
                return 6;
            }else{
                return 7;
            }

        }
    }

    static boolean isPrior(int dist, Santa santa, int minDist, Santa targetSanta){
        if(dist == minDist) return santa.compareTo(targetSanta) < 0;
        return dist < minDist;
    }

    static int getDist(int x1, int y1, int x2, int y2){
        return (int) (Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }

    static void reduceSleepCount(){
        for(Map.Entry<Integer, Santa> entry : santaMap.entrySet()) {
            Santa santa = entry.getValue();
            if(santa.sleep > 0) santa.sleep -= 1;
        }
    }
    static void addOneScoreNotFailSantas(){
        for(Map.Entry<Integer, Santa> entry : santaMap.entrySet()) {
            Santa santa = entry.getValue();
            if(!santa.is_out) santa.score += 1;
        }
    }

    static boolean allSantaFail(){
        for(Map.Entry<Integer, Santa> entry : santaMap.entrySet()) {
            if(!entry.getValue().is_out) return false;
        }
        return true;
    }

    static void printEachSantaScores(){
        for(Map.Entry<Integer, Santa> entry : santaMap.entrySet()) {
            sb.append(entry.getValue().score).append(" ");
        }
        sout(sb.toString());
    }
    static void input() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = toInt(st.nextToken());
        M = toInt(st.nextToken());
        P = toInt(st.nextToken());
        C = toInt(st.nextToken());
        D = toInt(st.nextToken());

        santaNumMap = new int[N+1][N+1];

        st = new StringTokenizer(br.readLine());
        rouR = toInt(st.nextToken());
        rouC = toInt(st.nextToken());

        for(int i = 1; i <= P; i++){
            st = new StringTokenizer(br.readLine());
            int num = toInt(st.nextToken());
            int r = toInt(st.nextToken());
            int c = toInt(st.nextToken());
            santaMap.put(num, new Santa(num , r, c));
            santaNumMap[r][c]  = num;
        }
    }
    static int toInt(String s){
        return Integer.parseInt(s);
    }
    static void sout(String s){
        System.out.println(s);
    }
    static void sout(int s){
        System.out.println(s);
    }
    static void printAllSanta(){
        for(Map.Entry<Integer, Santa> entry : santaMap.entrySet()) {
            sout(entry.getValue().toString());
        }
    }

}