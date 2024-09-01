import java.util.*;
import java.io.*;
public class Main {
    static int [] arr;
    static int N,M;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    public static void main(String[] args) throws Exception{
        // 여기에 코드를 작성해주세요.
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i =1; i <=N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            System.out.println(satisfiedCnt(a, b));
        }

    }
    static int satisfiedCnt(int a, int b){
        int lowerBound = getLowerBound(a); // A이상 만족하는 최초 위치
        int upperBound = getUpperBound(b); // B를 초과하는 최초 위치
        if(lowerBound == upperBound) return 0;
        return upperBound - lowerBound;
    }
    static int getLowerBound(int target){
        int right = N, left = 1;
        int minIdx = N+1;
        while(left <= right){
            int mid = (left + right) / 2;
            if(arr[mid] >= target){
                minIdx = Math.min(minIdx, mid);
                right = mid -1;
            }else{
                left = mid + 1;
            }
        }
        return minIdx;
    }
    static int getUpperBound(int target){
        int right = N, left = 1;
        int minIdx = N+1;
        while(left <= right){
            int mid = (left + right) / 2;
            if(arr[mid] > target){
                minIdx = Math.min(minIdx, mid);
                right = mid -1;
            }else{
                left = mid + 1;
            }
        }
        return minIdx;

    }
}