import java.io.*;
import java.util.*;

public class Main {
    static int select[] = new int[3]; // 선택한 위치
    static int arr[];
    static int K, N, ans;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = toInt(st.nextToken());
        K = toInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = toInt(st.nextToken());
        }
        recur(0, -1);
        System.out.println(ans);
    }

    static void recur(int idx, int pre){
        if( idx == 3 ){
            // System.out.println(Arrays.toString(select));
            // System.out.println(Arrays.toString(arr));
            if(sumIsNumber(select, arr, K)){
                ans += 1;
            }
            return;
        }
        for(int i = pre + 1; i < N; i++){
            select[idx] = i;
            recur(idx + 1, i);
            select[idx] = -1;
        }

    }
    static boolean sumIsNumber(int [] select, int [] arr, int num){
        int sum = 0;
        for(int i = 0; i < select.length; i++){
            sum += arr[select[i]];
        }
        return sum == num;
    }
    static int toInt(String s){
        return Integer.parseInt(s);
    }
}