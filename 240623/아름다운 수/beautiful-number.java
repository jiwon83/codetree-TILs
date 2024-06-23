import java.io.*;
import java.util.*;
public class Main {
    static int ans, N;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        // 여기에 코드를 작성해주세요.
        N = Integer.parseInt(br.readLine());
        recur(0);
        System.out.println(ans);

    }
    static void recur(int k){
        if(k >= N){
            if(k == N) ans += 1;
            return;
        }
        // 1~9 까지 try
        for(int num = 1; num <= 4; num++){
            recur(k + num);
        }
    }
}