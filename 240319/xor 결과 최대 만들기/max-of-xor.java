import java.util.*;
import java.io.*;
public class Main {

    public static final int MAX_NUM = 20;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    private static int [] ch = new int[MAX_NUM];
    private static int [] arr = new int[MAX_NUM];
    private static int ans, N,M;

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        Main main = new Main();
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
 
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        main.recur(0, N, M, 0);
        System.out.println(ans);
        
    }
    
    private void recur(int k, int n, int m, int cnt){
        
        if(cnt == m){
            ans = Math.max(ans, calXOR(ch, arr));
            // System.out.println("complelte "+ Arrays.toString(ch));
        }
        if(k==n){
            return;
        }
        
        ch[k] = 0;
        recur(k+1,n,m,cnt);

        ch[k] = 1;
        recur(k+1,n,m,cnt+1);
        // ch[k] = 0;
        
    }
    private int calXOR(int [] ch, int [] arr){
        int res = 0;
        for(int i = 0; i < N; i++ ){
            if(ch[i]==1){
                res = (res ^ arr[i]); 
            }
        }
        return res;

    }
}