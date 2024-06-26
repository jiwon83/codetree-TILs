import java.util.*;
import java.io.*;
public class Main {
    static int N, maxSum;
    static int [] rows, cols;
    static int [][] map;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        rows = new int[N+1];
        cols = new int[N+1];
        for(int i = 1; i <=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <=N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        recurFill(1, 0);
        System.out.println(maxSum);
        
    }
    private static void recurFill(int k, int sum ){
        if(k == N+1){
            maxSum = Math.max(maxSum, sum);
            return;
        }
        for(int r = 1; r <=N; r++){
            for(int c = 1; c <=N; c++){
                if(rows[r] == 0 && cols[c] == 0){
                    rows[r] = 1;
                    cols[c] = 1;
                    recurFill(k+1, sum+map[r][c]);
                    rows[r] = 0;
                    cols[c] = 0;
                }
            }
        }
    }
}