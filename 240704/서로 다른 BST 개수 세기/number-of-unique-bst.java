import java.util.*;
import java.io.*;
public class Main {
    static int MAX_N = 19;
    static int N;
    static boolean [] visit = new boolean[MAX_N+1];
    static int [][] dp = new int[MAX_N+1][MAX_N+1]; // start ~ end에서 갯수 

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        
        for(int i = 1; i <= N; i++) dp[i][i] = 1;

        for(int len = 2; len <= N; len++){
            for(int i = 1; i <=N; i++){

                visit = new boolean[MAX_N+1];
                int totalCnt = 0;
                for(int node = i ; node < i + len; node++){
                    visit[node] = true;
                    totalCnt += recurDownTreeCnt(node, i, i + len - 1, node + " -> ");
                    visit[node] = false;
                }
                dp[i][i+len-1] = totalCnt;
                
            }
        }

        System.out.println(dp[1][N]);
        
    }
    static void pro(){

    }
    public static int recurDownTreeCnt(int curr, int start, int end, String route){
        // System.out.println(route);
        if(dp[start][end] != 0) return dp[start][end];

        int left = 0;
        int right = 0;
        //left child
        for(int node = start; node < curr; node++){
            if(visit[node]) continue;
            visit[node] = true;
            left += recurDownTreeCnt(node, start, curr-1, route + node + " -> ");
            visit[node] = false;
        }
        // right child
        for(int node = curr+1; node <= end; node++){
            if(visit[node]) continue;
            visit[node] = true;
            right += recurDownTreeCnt(node, curr+1, end, route + node + " -> ");
            visit[node] = false;           
        }

        
        if(left == 0 && right == 0) return 1;
        return Math.max(1, left) * Math.max(1, right);

        

    }
}