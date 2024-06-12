import java.util.*;
import java.io.*;

public class Main {
    static class Marble{
        int idx, x, y, dir;
        public Marble(int idx, int x, int y, int dir){
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
        public String toString(){
            return "["+idx + "번 [(" + x + "," + y + ")d=" + dir + "]";
        }
    }
    static int T, N, M;
    static Marble [][] marbleMap;
    static int [][] willBomb; // 폭팔 예상 정보
    static int MAX_TIME;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int [][] dirs = {{-1,0}, {0,1}, {0,-1}, {1,0}}; // up, down, right, left
    static StringTokenizer st;

    public static void main(String[] args) throws Exception{
        // input 
        int T  = toInt(br.readLine());
        for(int t = 1; t <=T; t++){

            // input inner 
            st = new StringTokenizer(br.readLine());
            N = toInt(st.nextToken());
            M = toInt(st.nextToken());
            MAX_TIME = N * 2;
            marbleMap = new Marble[N+1][N+1];
            for(int i = 0; i < M; i++){
                st = new StringTokenizer(br.readLine());
                int x = toInt(st.nextToken());
                int y = toInt(st.nextToken());
                int d = toDirNum(st.nextToken());
                marbleMap[x][y] = new Marble(i, x, y, d);  
            }

            // pro 
            // System.out.println(" t = "+ t);
            // System.out.println(" 초기 상태");
            // printMarbleMap();
            for(int i = 1; i <=MAX_TIME; i++){
                willBomb = new int[N+1][N+1];
                marbleMap = simulate(marbleMap);
                // System.out.println(" 횟수 = "+ i);
                // printMarbleMap();
            }
            sb.append(getCount(marbleMap)).append("\n");
            
        }
        System.out.println(sb);
        // 여기에 코드를 작성해주세요.
    }
    static int getCount(Marble [][] map){
        int leftCount = 0;
        for(int i = 1; i <=N; i++){
            for(int j = 1; j <=N; j++){
                if(map[i][j] != null){
                    leftCount +=1;
                }
            }
        }
        return leftCount;
    }
    static Marble[][] simulate(Marble [][] marbleMap){

        willBomb = new int[N+1][N+1]; // 폭팔 예상 정보 초기화
        Marble [][] afterMarbleMap = new Marble[N+1][N+1]; // 1초 후의 지도 

        for(int i = 1; i <=N; i++){
            for(int j = 1; j <=N; j++){
                if(marbleMap[i][j] != null){
                    Marble marble = marbleMap[i][j];
                    // System.out.println(" before >> "+ marble);
                    int dir = marble.dir;
                    int nx = marble.x + dirs[dir][0];
                    int ny = marble.y + dirs[dir][1];
                    // System.out.println(" nx = "+ nx + " ny = "+ ny);
                    
                    // 다음 위치가 벽이라면
                    if(!inArea(nx, ny)){
                        dir = revDir(marble.dir);
                        // 현재 위치 그대로 이동
                        nx = marble.x;
                        ny = marble.y;
                    } 
                    if(afterMarbleMap[nx][ny] != null){
                        willBomb[nx][ny] = 1;
                    }else{
                        afterMarbleMap[nx][ny] = new Marble(marble.idx, nx, ny, dir);
                    }
                }
            }
        }
        for(int i = 1; i <=N; i++){
            for(int j = 1; j <=N; j++){
                if(willBomb[i][j] == 1) afterMarbleMap[i][j] = null;
            }
        }
        return afterMarbleMap;
    }

    static boolean inArea(int r, int c){
        return r > 0 && c > 0 && r <=N && c <=N;
    }
    static int revDir(int dir){
        return 3 - dir;
    }
    static int toDirNum(String s){
        if(s.charAt(0) == 'U') return 0;
        if(s.charAt(0) == 'D') return 3;
        if(s.charAt(0) == 'R') return 1;
        if(s.charAt(0) == 'L') return 2;
        return -1;
    }

    static int toInt(String s){
        return Integer.parseInt(s);
    }
    static void printMarbleMap(){
        for(int i = 1; i <=N; i++){
            for(int j = 1; j <=N; j++){
                if( marbleMap[i][j] == null ) System.out.print("  [ " + "  " + "    " + " ]");
                else System.out.print(marbleMap[i][j]+" ");
            }
            System.out.println();
        }
    }
}