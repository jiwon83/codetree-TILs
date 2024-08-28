import java.util.*;
import java.io.*;
public class Main {
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    static PriorityQueue<Integer> removedQ = new PriorityQueue<>();
    static int N;
    static double ans;
    static int [] arr = new int[100001];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        // 여기에 코드를 작성해주세요.

        double sum = 0;
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <=N; i++){
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
        }


        for(int k = N; k >=1 ; k--){
            
            if( k <= N - 2){
                // System.out.println("k = "+ k + " , sum = "+ sum );
                int min = pq.peek();
                double avg = (sum - min) / (N - k - 1);
                ans = Math.max(ans, avg);
            }
            sum += arr[k];
            pq.add(arr[k]);
        }
        System.out.println(String.format("%.2f", ans));
        // System.out.println(Math.round(ans * 100) / 100.0);
    }
}