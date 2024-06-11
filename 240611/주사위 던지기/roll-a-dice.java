import java.util.*;
import java.io.*;
public class Main {
    static int N, M, nowR, nowC; // M은 횟수
    static int [][] map;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int [] cube = {4,6,3,1,5,2}; //주사위 정보

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
    static boolean nextIsOutOfArea(char cmd, int r, int c){
        int nextR = -1, nextC = -1;
        if(cmd=='L'){
            nextR = r + dirs[0][0];
            nextC = c + dirs[0][1];
        }
        if(cmd == 'R'){
            nextR = r + dirs[1][0];
            nextC = c + dirs[1][1];
        }
        if(cmd == 'U'){
            nextR = r + dirs[2][0];
            nextC = c + dirs[2][1];
        }
        if(cmd == 'D'){
            nextR = r + dirs[3][0];
            nextC = c + dirs[3][1];
        }
        return !inArea(nextR, nextC);
    }
    static boolean inArea(int r, int c){
        return r > 0 && c > 0 && r <=N && c <=N;
    }
    static int [][] dirs = {{0,-1}, {0,1}, {-1,0}, {1,0}};
    static void doGame(char cmd){
        // 주사위를 굴리고
        switch(cmd){
            case 'L':
            nowR = nowR + dirs[0][0];
            nowC = nowC + dirs[0][1];
            cube = moveL(cube);
            break;
            case 'R':
            nowR = nowR + dirs[1][0];
            nowC = nowC + dirs[1][1];
            cube = moveR(cube);
            break;
            case 'U':
            nowR = nowR + dirs[2][0];
            nowC += dirs[2][1];
            cube = moveU(cube);
            break;
            case 'D':
            nowR += dirs[3][0];
            nowC += dirs[3][1];
            cube = moveD(cube);
        }

        // 바닥면을 세긴다.
        map[nowR][nowC] = cube[1];

    }
    // 0~3까지에 대해 +1 만큼 이동
    static int [] moveL(int [] cube){
        int [] tmp = cube.clone();
        for(int i = 0;i < 4; i++){
            int ni = (i+1) % 4;
            tmp[ni] = cube[i];
        }
        return tmp;
    }

    static int [] moveR(int [] cube){
        int [] tmp = cube.clone();
        for(int i = 0;i < 4; i++){
            int ni = (i-1) >=0 ? i-1 : 3;
            tmp[ni] = cube[i];
        }
        return tmp;
    }

    static int [] moveD(int [] cube){
        int [] tmp = cube.clone();
        tmp[1] = cube[5];
        tmp[3] = cube[4];
        tmp[5] = cube[3];
        tmp[4] = cube[1];
        return tmp;
    }

    static int [] moveU(int [] cube){
        int [] tmp = cube.clone();
        tmp[1] = cube[4];
        tmp[3] = cube[5];
        tmp[4] = cube[3];
        tmp[5] = cube[1];
        return tmp;
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