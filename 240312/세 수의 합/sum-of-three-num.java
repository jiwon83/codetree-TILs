import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int [] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(sol(arr, N, K));
        
    }
    private static int sol(int [] arr, int n, int k){
        int cnt = 0;
        Arrays.sort(arr);
        // System.out.println(Arrays.toString(arr));
        for(int i = 0;i <arr.length; i++){
            for(int j = i+1 ;j + 1 <arr.length; j++){
                // System.out.println("find..."+ " i = "+ i + " ~ j = "+ j + " :targete = "+ (k-arr[i]-arr[j]));
                if(binary_search(arr, j+1, arr.length-1, k-arr[i]-arr[j])){
                    // System.out.println(" 존재 ! ");
                    cnt++;
                } 
            }
        }
        return cnt;
    }
    private static boolean binary_search(int [] arr, int sIdx, int eIdx, int target){
        
        while(sIdx <= eIdx){
            int mid = (sIdx + eIdx) / 2;
            if( arr[mid] == target ) return true;
            if( arr[mid] > target) eIdx = mid -1;
            else sIdx = mid + 1;  
        }
        return false;
    }
}