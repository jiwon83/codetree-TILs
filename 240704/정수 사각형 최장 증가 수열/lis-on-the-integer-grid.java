import java.util.*;
import java.io.*;

public class Main {
    static int MAX_N = 500;
    static int maxAns = 1;
    static int N;
    static int [][] dp = new int[MAX_N+1][MAX_N+1]; // 해당 인덱스에서 이동 가능한 최대 칸 수 
    static int [][] map = new int[MAX_N+1][MAX_N+1];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;


    public static void main(String[] args)throws Exception {
        // 여기에 코드를 작성해주세요.
        N = Integer.parseInt(br.readLine());

        for(int i = 1; i <=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j <=N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1; i <=N ; i++){
            for(int j = 1; j <=N; j++){
                dfs(i, j);
            }
        }
        System.out.println(maxAns);
    }

    static int [][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};

    private static int dfs(int r, int c){
        if(dp[r][c] != 0) return dp[r][c];

        dp[r][c] = 1;
        for(int d = 0; d < 4; d++){
            int nr = r + dirs[d][0];
            int nc = c + dirs[d][1];
            if(!inArea(nr,nc) || map[nr][nc] <= map[r][c] ) continue;

            dp[r][c] = Math.max(dp[r][c], dfs(nr, nc) + 1);
        }
        maxAns = Math.max(maxAns, dp[r][c]);
        return dp[r][c];
    }
    static boolean inArea(int r, int c){
        return r > 0 && c > 0 && r <=N && c <=N;
    }
}