import java.util.*;
import java.io.*;

public class Main {
    static long M;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    public static void main(String[] args) throws Exception {
        // 여기에 코드를 작성해주세요.
        M = Long.parseLong(br.readLine());
        st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        int minCnt = M + 1;
        int maxCnt = -1;

        for(int x = a; x <=b; x++){
            int cnt = cntOfBinarySearch(x);
            minCnt = Math.min(minCnt, cnt);
            maxCnt = Math.max(maxCnt, cnt);
        }
        System.out.println(minCnt + " "+ maxCnt);
    }

    static int cntOfBinarySearch(long target){
        int cnt = 0;
        long left = 1, right = M;
        while(left <= right){
            cnt += 1;
            long mid = (left + right) / 2;
            if(mid == target) return cnt;
            if(mid > target){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return -1; // 이럴 가능성은 없다.
    }


}