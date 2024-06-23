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
        C = Integer.parseInt(st.nextToken());
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
                        if(isPossible(i, j, h, w)) continue;
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
    static boolean isPossible(int r, int c, int r2, int c2){
        if( r1 != r2 ) return true;
        int cEnd = c + M - 1;
        int c2End = c2 + M - 1;
        return cEnd < c2 || c2End < c;
    }
    // r,c 에서 M 만큼의 길이 중 수집 가능한 가장 최고의 value를 return
    static int recurSelect(int k, int r, int c, int sum, int value){
        if(k == M){
           return value;
        }
        int noSelect = recurSelect(k+1, r, c, sum, value);
        int select = -1;
        if(sum + map[r][c+k] <= C) {
            select = recurSelect(k+1, r, c, sum + map[r][c+k], value + map[r][c+k] * map[r][c+k]);
        }
        return Math.max(noSelect, select);
        
    }
}