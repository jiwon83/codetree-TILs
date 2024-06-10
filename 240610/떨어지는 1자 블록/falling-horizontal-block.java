import java.util.*;
import java.io.*;

public class Main {
    static int [][] map;
    static int N, M, K;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        input();
        pro();
        printArr(map, sb);
    }
    
    static void pro() {
        int targetRow = N;
        for(int r = 1; r < N; r++){
            if(hasBlockinRow(map, r+1, K, K+M-1)){
                targetRow = r;
                break;
            }
        }
        for(int c = K; c <=K+M-1; c++) map[targetRow][c] = 1;
    }

    static boolean hasBlockinRow(int [][] map, int r, int from, int to){
        for(int c = from; c <= to; c++){
            if(map[r][c]== 1){
                return true;
            } 
        }
        return false;
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
    static void printArr(int [][] arr, String msg){
        System.out.println("----- "+ msg+" ------");
        for(int i = 0; i < arr.length; i++){
            System.out.println(Arrays.toString(arr[i]));
        }
        System.out.println("----------------");
    }
    static int toInt(String s){
        return Integer.parseInt(s);
    }
    static void sout(String s){
        System.out.println(s);
    }
}