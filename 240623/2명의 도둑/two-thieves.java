import java.util.*;
import java.io.*;
public class Main {
    static int N, M, C, ans;
    static int [][] map;
    static int maxEarn;
    static StringTokenizer st; 
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        //input
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C= Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for(int i= 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 여기에 코드를 작성해주세요.
        // 1번째 위치 선택 + 물건을 고르고
        for(int i = 0; i < N; i++){
            for(int j = 0; j + M -1 < N; j++){
                for(int h = 0; h < N; h++){
                    for(int w = 0; w + M -1 < N; w++){
                        if(!isNotOverlap(i, j, h, w)) continue;
                        int value1 = recurSelect(0, i, j, M, C, 0, 0);
                        int value2 = recurSelect(0, h, w, M, C, 0, 0);
                        ans = Math.max(ans, value1 + value2);
                    }
                }
                
            }
        }
        System.out.println(ans);
        // 이어서 2번째 선택 + 물건 고름
    }
    static boolean isNotOverlap(int i, int j, int i2, int j2){
        return j < i2 || j2 < i;
    }
    static int recurSelect(int k, int r, int c, int len, int limit, int sum, int value){
        if(k == len){
            return value;
        }
        // select Or not select
        int result0 = recurSelect(k+1, r, c, len, limit, sum, value);
        int result1 = -1;
        if( c + k < N){
            int willSum = sum + map[r][c+k];
            if(willSum <= limit) result1 = recurSelect(k+1, r, c, len, limit, willSum, value + map[r][c+k] * map[r][c+k]);
        }
        return Math.max(result0, result1);
        
    }
}