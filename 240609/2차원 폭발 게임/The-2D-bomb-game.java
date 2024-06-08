import java.util.*;
import java.io.*;
/*
틀린 이유 : 문제의 문장하나하나 꼼꼼히 보지 않고 로직을 가정했다. 
*/

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M, K, cntOfDeldeted;
    static int [][] map;

    public static void main(String[] args) throws Exception{
        input();
        for(int k = 0; k < K; k++){
            while(explodes());
            rotate90();
        }
        while(explodes());
        printCnt();
    }
    static void input() throws Exception{
        st = new StringTokenizer(br.readLine());
        N = toInt(st.nextToken());
        M = toInt(st.nextToken());
        K = toInt(st.nextToken());
        map = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = toInt(st.nextToken());
            }
        }
    }
    static void printCnt(){
        int cnt = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j] > 0) cnt++;
            }
        }
        System.out.println(cnt);
    }
    // explode
    static boolean explodes(){
        boolean isExploded = false;
        for(int c = 0; c < N; c++){
            int i = 0;
            for(;i < N; i++){
                if(map[i][c] == 0) continue;
                int j = i;
                while(j+1 < N && map[i][c] == map[j+1][c]) ++j;
                int cnt = j - i +1;
                if(cnt >= M){
                    isExploded = true;
                    for(int r = i; r <= j; r++) map[r][c] = 0;
                }
                i = j;
            }
        }
        gravityDown();
        return isExploded;
    }
    // rotate
    static void rotate90(){
        int [][] tmp = new int[N][N];
        for(int i = 0; i < N; i++) tmp[i] = map[i].clone();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                int ni = j;
                int nj = (N-1) - i;
                tmp[ni][nj] = map[i][j];
            }
        }
        for(int i = 0; i < N; i++) map[i] = tmp[i].clone();
        gravityDown();
    }

    static void gravityDown(){
        for(int c = 0; c < N; c++){
            int [] tmp = new int[N];
            int endOfTmp = N-1;
            for(int r = N-1; r >= 0; r--){
                if(map[r][c] > 0) tmp[endOfTmp--] = map[r][c];
            }
            for(int r = N-1; r >=0; r--){
                map[r][c] = tmp[r];
            }
        }
    }

    static int toInt(String s){
        return Integer.parseInt(s);
    }
}