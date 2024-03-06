import java.util.*;
import java.io.*;

public class Main {
    static int MAX = 100001;
    static int [] arr;
    static int N, K;
    static HashMap<Integer, Integer> cntMap = new HashMap<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException{
        String [] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        arr = new int[N];
        input = br.readLine().split(" ");
        int ans = 0;
        for(int i = 0; i < N; i++){
            int num = Integer.parseInt(input[i]);
            int pair = K - num;
            if(cntMap.containsKey(pair)) ans += cntMap.get(pair);
            cntMap.put(num, cntMap.getOrDefault(num, 0)+1);
           
        }
        System.out.println(ans);

    }
}