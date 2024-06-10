import java.util.*;
import java.io.*;

public class Main {
    static int [][] map;
    static int N, M, K;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        // 여기에 코드를 작성해주세요.
        input();
        pro();
        printArr(map, sb);
    }
    static void pro() {
        // printArr(map);
        int setRow = N;
        Loop: for(int r = 1; r <= N; r++){
            for(int c = K; c < K+M; c++){
                if(map[r+1][c]== 1){
                    setRow = r;
                    break Loop;
                }
            }
        }
        // System.out.println("setRow = "+ setRow);
        for(int c = K; c <=K+M-1; c++){
            map[setRow][c] = 1;
        }
    }

    static void input() throws Exception{
        st = new StringTokenizer(br.readLine());
        N = toInt(st.nextToken());
        M = toInt(st.nextToken());
        K = toInt(st.nextToken());
        map = new int[N+1][N+1];
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++){
                map[i][j] = toInt(st.nextToken());
            }
        }

    }
    static void printArr(int [][] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.println(Arrays.toString(arr[i]));
        }
    }
    static void printArr(int [][] arr, StringBuilder sb){
        for(int i = 1; i < arr.length; i++){
            for(int j =1; j <arr.length; j++){
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    static int toInt(String s){
        return Integer.parseInt(s);
    }
}