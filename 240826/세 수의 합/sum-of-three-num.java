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
        arr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            arr[i] = toInt(st.nextToken());
        }
        recur(1, 0);
        System.out.println(ans);
    }

    static void recur(int i, int cnt){ // i = arr의 인덱스, cnt = 선택한 갯수
        if( cnt == 3 || i == N + 1 ){
            // if(cnt == 3) System.out.println(Arrays.toString(select));
            if(cnt == 3 && sumIsNumber(select, arr, K)){
                
                ans += 1;
            }
            return;
        }
        recur(i+1, cnt);
        select[cnt] = i;
        recur(i+1, cnt+1);
        select[cnt] = 0;
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