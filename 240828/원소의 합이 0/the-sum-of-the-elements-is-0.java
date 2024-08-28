import java.util.*;
import java.io.*;
public class Main {

    static HashMap<Integer, Integer> freqAandB = new HashMap<>();
    static HashSet<Integer> aSet = new HashSet<>();
    static HashSet<Integer> bSet = new HashSet<>();
    static HashSet<Integer> cSet = new HashSet<>();
    static HashSet<Integer> dSet = new HashSet<>();
 
    static int ans, N;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i <N; i++){
            aSet.add(Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i <N; i++){
            bSet.add(Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i <N; i++){
            cSet.add(Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i <N; i++){
            dSet.add(Integer.parseInt(st.nextToken()));
        }

        // A B C D sum = 0
        // A에 같은 수가 있다면 같은 경우
        // 단 1 + 9 = 10, 2 + 8 = 10 다른 경우
        // 각각에서 중복을 제거하고
        // a + b의 모든 조합을 -> hashmap에 넣기
        for(int num : aSet){
            for(int num2 : bSet){
                int midSum = num + num2;
                freqAandB.put(midSum, freqAandB.getOrDefault(midSum, 0) + 1);

            }
        }

        for(int num3 : cSet){
            for(int num4 : dSet){
                int midSum = num3 + num4;
                int seek = -midSum;
                if(freqAandB.containsKey(seek)){
                    ans += freqAandB.get(seek);
                }
            }
        }
        // c + d의 모든 조합을 구하고 0이 되는 a+b가 있다면 넣기

        System.out.println(ans);
        
    }

}