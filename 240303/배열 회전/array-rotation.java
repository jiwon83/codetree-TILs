import java.util.*;
import java.io.*;

public class Main {
    static List<Integer> [] layers;
    static int N,M,K, depthSize;
    static StringTokenizer st ;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] dirs = {{1,0}, {0,1}, {-1,0}, {0,-1}};
    static int [][] map;

    public static void rotate() {
        depthSize = Math.min(N, M) / 2;

        for(int dpt = 0; dpt < depthSize; dpt++){
            int n_max = N - dpt - 1;
            int m_max = M - dpt - 1;

            //레이어 위쪽 상단 값 임시 저장
            int tmp = map[dpt][dpt];

            //  << 이동
            for(int c = dpt+1; c <= m_max; c++){
                map[dpt][c-1] = map[dpt][c];
            }

            // 위쪽 이동
            for(int r = dpt + 1; r <=n_max; r++){
                map[r-1][m_max] = map[r][m_max];
            }

            // >> 이동
            for(int c = m_max -1; c >= dpt; c--){
                map[n_max][c+1] = map[n_max][c];
            }

            // 아래쪽 이동
            for(int r = n_max-1; r >= dpt; r--){
                map[r+1][dpt] = map[r][dpt];
            }

            map[dpt+1][dpt] = tmp;

        }
        // printMap(map);
    }

    static void printMap(int [][] map){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i< map.length; i++){
            for(int j=0; j <map[0].length; j++){
                sb.append(map[i][j]+" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i = 0; i< N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j <M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < K; i++) rotate();
       
        printMap(map);

    }
}