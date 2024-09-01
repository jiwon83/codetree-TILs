import java.util.*;
import java.io.*;
public class Main {
    static long N, K;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextLong();
        K = sc.nextLong(); 
        System.out.println(binary_search());
    }
    static long binary_search(){
        long left = 1, right = N * N;
        long ans = right + 1;
        while(left <= right){
            long mid = (left + right) / 2;
            if(isPossible(mid)){
                ans = mid;
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return ans;
    }
    static boolean isPossible(long number){
        long sum = 0;
        for(int r = 1; r <= N; r++){
            sum += Math.min( N, number / r );
        }
        return sum >= K;
    }
}