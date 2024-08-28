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
            sum += num;
            arr[i] = num;
            pq.add(num);
        }


        for(int k = 1; k <= N-2; k++){
            //앞에서 K개를 삭제
            sum -= arr[k];
            while(!removedQ.isEmpty() && !pq.isEmpty() && removedQ.peek() == pq.peek()){
                pq.poll();
                removedQ.poll();
            }
            if(pq.peek() == arr[k]){
                pq.poll();
            }else{
                removedQ.add(arr[k]);
            }

            // 가장 작은 값을 꺼내서 평균 구하기
            int min = pq.peek();
            double avg = (sum - min) / (N - k - 1); 
            ans = Math.max(ans, avg);

        }
        System.out.println(String.format("%.2f", ans));
        // System.out.println(Math.round(ans * 100) / 100.0);
    }
}