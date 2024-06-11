import java.util.*;
import java.io.*;
public class Main {

    static int N, M, nowR, nowC; // M은 횟수
    static int [][] map;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int [] cube = new int[]{2, 1, 3}; //주사위 정보 0 = front, 1 = up, 2 = right;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = toInt(st.nextToken());
        M = toInt(st.nextToken());
        map = new int[N+1][N+1];
        nowR = toInt(st.nextToken());
        nowC = toInt(st.nextToken());
        map[nowR][nowC] = cube[1];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){
            char cmd = st.nextToken().charAt(0);
            if(nextIsOutOfArea(cmd, nowR, nowC)) continue;
            doGame(cmd);
        }
        // printArr(map, "map");
        sout(getSumOfNumbers(map)+"");  
    }
    static int getSumOfNumbers(int [][] map){
        int num = 0;
        for(int i = 1; i <=N; i++){
            for(int j = 1; j <=N; j++){
                num += map[i][j];
            }
        }
        return num;
    }
    static Point getNextPos(char cmd, int r, int c){       
        if(cmd=='L'){
            r += dirs[0][0];
            c += dirs[0][1];
        }
        if(cmd == 'R'){
            r += dirs[1][0];
            nextC = c + dirs[1][1];
        }
        if(cmd == 'U'){
            r += dirs[2][0];
            c += dirs[2][1];
        }
        if(cmd == 'D'){
            r += dirs[3][0];
            c += dirs[3][1];
        }
    }
    static boolean nextIsOutOfArea(char cmd, int r, int c){
        Point nextP = getNextPos(cmd, r, c);
        return !inArea(nextP.x, nextP.y);
    }
    static boolean inArea(int r, int c){
        return r > 0 && c > 0 && r <=N && c <=N;
    }
    static int [][] dirs = {{0,-1}, {0,1}, {-1,0}, {1,0}};
    static void doGame(char cmd){
        Point nextPos =getNextPos(cmd, nowR, nowC);
        cube = moveCube(cmd, cube);  // 주사위를 굴리고
        map[nowR][nowC] = cube[1]; // 바닥면을 세긴다.

    }
    static int [] moveCube(char cmd, int [] cube){
        int newCube [] = cube.clone();
        if(cmd == 'U'){
            newCube[0] = 7-cube[1];
            newCube[1] = cube[0];
        }
        if(cmd == 'D'){
            newCube[0] = cube[1];
            newCube[1] = 7-cube[0];
        }
        if(cmd == 'L'){
            newCube[1] = cube[2];
            newCube[2] = 7-cube[0];
        }
        if(cmd == 'R'){
            newCube[1] = 7-cube[2];
            newCube[2] = cube[1];
        }
    }
 
    static int toInt(String s){
        return Integer.parseInt(s);
    }
    static void printArr(int [][] arr, String msg){
        System.out.println("======== "+msg+" ========");
        for(int i= 0; i < arr.length; i++){
            System.out.println(Arrays.toString(arr[i]));
        }
        System.out.println("========  ========");
    }
    static void sout(String s){
        System.out.println(s);
    }
}