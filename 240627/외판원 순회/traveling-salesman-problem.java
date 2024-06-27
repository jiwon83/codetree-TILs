import java.io.*;
import java.util.*;

public class Main {
    static int [][] costs;
    static int N;
    static int [] ch;
    static int minCost = Integer.MAX_VALUE;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    public static void main(String[] args) throws Exception{
        // 여기에 코드를 작성해주세요.
        N = Integer.parseInt(br.readLine());
        costs = new int[N+1][N+1];
        ch = new int [N+1];
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <=N; j++){
                costs[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int st = 1; st <=N; st++){
            ch[st] = 1;
            recurVisit(1, 0, st, st);
            ch[st] = 0;
        }
        
        System.out.println(minCost);
    }
    private static void recurVisit(int k, int cost, int pre, int start){
        if(k == N){
            if(costs[pre][start] != 0) minCost = Math.min(minCost, cost + costs[pre][start]);
            return;
        }
        for(int i = 1; i <= N; i++){
            if(ch[i] == 1) continue;
            if(costs[pre][i] == 0) continue;
            ch[i] = 1;
            recurVisit(k+1, cost + costs[pre][i], i, start);
            ch[i] = 0;
        }
    }
}