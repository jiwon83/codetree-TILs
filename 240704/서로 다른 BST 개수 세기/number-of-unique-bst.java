import java.util.*;
import java.io.*;
/*
 left < parent
right > parent

*/
public class Main {
    static int MAX_N = 19;
    static int N;
    static int [] dp = new int[MAX_N+1]; // idx개의 노드를 갖을 때 서브트리의 갯수

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        dp[1] = 1;
        for(int i = 2; i <=N; i++){
            getSubTreeCount(i);
        }
        // System.out.println(Arrays.toString(dp));
        System.out.println(dp[N]);
        
    }
    static void getSubTreeCount(int n){
        for(int root = 1; root <=n; root++){
            int left = root - 1;
            int right = n - root;
            dp[n] += Math.max(1, dp[left]) * Math.max(1, dp[right]);
        }
    }
   
}