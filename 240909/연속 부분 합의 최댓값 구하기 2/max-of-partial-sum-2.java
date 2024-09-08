import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws Exception{
        // 여기에 코드를 작성해주세요.
        int n = Integer.parseInt(br.readLine());
        int [] a = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) a[i] = Integer.parseInt(st.nextToken());

        System.out.println(sol(n, a));

    }

    private static int sol(int n, int [] nums){
        int sum = 0;
        int max = nums[0];
        for(int i = 0; i < n; i++){
            if(sum >= 0){
                sum += nums[i];
            }else{
                sum = nums[i];
            }
            max = Math.max(max, sum);
        }
        return max;
    }
}