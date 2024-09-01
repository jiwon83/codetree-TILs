import java.util.*;
import java.io.*;

public class Main {
    static int [] arr = new int[100001];
    static int N, M;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i=1; i <=N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){
            int target = Integer.parseInt(st.nextToken());
            System.out.println(lower_bound(target));
        }
        
    }

    static int lower_bound(int target){
        int left = 1, right = N;
        int minIdx = N + 1;

        while(left <= right){
            int mid = ( left + right ) / 2;
            
            if(arr[mid] >= target){
                if(arr[mid] == target) minIdx = Math.min(minIdx, mid);
                right = mid - 1;
            }else{
                left = mid + 1;
            }

        }
        return minIdx == N+1? -1 : minIdx;

    }
}