import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        int mod = 1000000007;
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        
        
        long [] dp = new long[1001];
        dp[1] = 2;
        dp[2] = 7;
        dp[3] = 22;
        for(int i = 4; i <= N; i++){
            dp[i] = dp[i-1] * 2 + dp[i-2] * 3 + 2;
            
            for(int j = i - 3 ; j >= 1; j--){
                dp[i] = (dp[i] + dp[j] * 2) % mod;
            }
        }
        System.out.println(dp[N]);




    }
}