import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static List<Integer> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            list.add(Integer.parseInt(st.nextToken()));
        }

        for(int i = 0; i < M; i++){
            int num = Integer.parseInt(br.readLine());
            sb.append(binary_search(num, list)).append("\n");
        }
        System.out.println(sb);

    }
    private static int binary_search(int num, List<Integer> list){
        int L = 0;
        int R = list.size()-1;
        while(L <= R){
            int mid = (L + R) / 2;
            if(list.get(mid) == num) return mid+1;
            if(list.get(mid) < num) L = mid + 1;
            else R = mid - 1;
        }
        return -1;
        
    }
}