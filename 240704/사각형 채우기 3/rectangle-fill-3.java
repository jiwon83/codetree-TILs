import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        
        
        int [] dp = new int[1001];
        dp[1] = 2;
        dp[2] = 7;
        dp[3] = 22;
        for(int i = 4; i <= N; i++){
            dp[i] = dp[i-1] * 2 + dp[i-2] * 3 + dp[i-3] * 2 + 2;
        }
        System.out.println(dp[N]);




    }
}