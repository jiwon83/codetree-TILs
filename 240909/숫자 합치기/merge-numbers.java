import java.util.*;
import java.io.*;

public class Main {

    static PriorityQueue<Integer> heap = new PriorityQueue<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;

    public static void main(String[] args) throws Exception {
        
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++){
            heap.add(Integer.parseInt(st.nextToken()));
        }
        System.out.println(sol());

    }
    private static int sol(){
        int sum = 0;
  
        for(int i = 1; i < N; i++){
            int a = heap.poll();
            int b = heap.poll();
            int nSum = a + b;
            sum += nSum;
            heap.add(nSum);
        }
        return sum;
    }
}