import java.util.*;
import java.io.*;
public class Main {

    static int N;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static void input() throws IOException{
        N = Integer.parseInt(br.readLine());
    }
    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        input();
        System.out.println(solve(N));
        
    }
    static int solve(int N){
        if( N <= 2) return 1;

        int [] dp = new int[N+1];

        dp[1] = 1; dp[2] = 1;
        for(int i = 3; i <= N; i++ ){
            dp[i] = dp[i-2] + dp[i-1];
        }
        return dp[N];
    }
}