import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        /*
        if n = 3
        (1열을 만드는 경우의 수 + n-1 길이의 경우의 수) + 2열을 만들 수 있는 경우의 수 + n-2 경우의 수
        */
        int N = Integer.parseInt(br.readLine());
        int [] dp = new int[1000+1];
        dp[1] = 1;
        dp[2] = 3;
        for(int i = 3; i <=N ; i++){
            dp[i] = ( dp[i-1] + 2 * dp[i-2] ) % 10007;
        }
        // System.out.println(Arrays.toString(dp));
        System.out.println(dp[N]);

    }
}