import java.io.*;
import java.util.*;

public class Main {
    static int select[] = new int[3]; // 선택한 위치
    static int arr[];
    static int K, N, ans;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static HashMap<Integer, Integer> freq = new HashMap<>();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = toInt(st.nextToken());
        K = toInt(st.nextToken());
        arr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            arr[i] = toInt(st.nextToken());
            freq.put(arr[i], freq.getOrDefault(arr[i], 0) + 1);
        }
        System.out.println(freq);
        //for문을 통해 조합 구하기
        for(int i = 1; i <= N-2; i++){
            freq.put(arr[i], freq.getOrDefault(arr[i], 0) - 1);
            for(int j =i+1; j <= N-1; j++){
                int seek = K - (arr[i] + arr[j]);
                // System.out.println(i + " , "+ j + " , seek = "+ seek);
                freq.put(arr[j], freq.getOrDefault(arr[j], 0) - 1);
                if(freq.containsKey(seek)){
                    ans += freq.get(seek) > 0 ? freq.get(seek) : 0;
                    // System.out.println(" ans = "+ ans);
                }
            }
                
            for(int j =i+1; j <= N-1; j++){
                freq.put(arr[j], freq.getOrDefault(arr[j], 0) + 1);
            }
        }

        System.out.println(ans);
    }
    static int toInt(String s ){
        return Integer.parseInt(s);
    }

 

}