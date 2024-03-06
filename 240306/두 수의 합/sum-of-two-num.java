import java.util.*;
import java.io.*;

public class Main {
    static int MAX = 100001;
    static HashMap<Integer, Boolean> visit = new HashMap<>();
    static int [] arr = new int[MAX];
    static int N, K;
    static HashMap<Integer, Integer> cntMap = new HashMap<>();
    static HashMap<Integer, Integer> pairMap = new HashMap<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Set<Integer> set = new HashSet<>();

    private static void input() throws IOException{
        String [] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        input = br.readLine().split(" ");
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(input[i]);
        }

    }
    public static void main(String[] args) throws IOException{
        input();
        int ans = 0;
        for(int i = 0; i < N; i++){
            cntMap.put(arr[i], cntMap.getOrDefault(arr[i], 0)+1);
            pairMap.put(arr[i], K-arr[i]);
            visit.put(arr[i], false);
            visit.put(K-arr[i], false);
   
        }
        // System.out.println(cntMap);
        // System.out.println(pairMap);
        for(int num : cntMap.keySet()){
            if(visit.get(num)) continue;
            int pair = pairMap.get(num);
            if(num == pair){
                ans += cntMap.get(num) * (cntMap.get(num)-1) / 2;
            }else if(cntMap.containsKey(pair)){
                ans += cntMap.get(num) * cntMap.get(pair);
            }
            visit.put(num, true);
            visit.put(pair, true);
        }
        System.out.println(ans);
        
    }
}