import java.util.*;
import java.io.*;

public class Main {
    static int maxMoveCnt, N;
    static int [][] map, dirInfo;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int [][] dirs = {{}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
    public static void main(String[] args) throws Exception{
        // 여기에 코드를 작성해주세요.
        // input
        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        dirInfo = new int[N+1][N+1];
        for(int i = 1; i <=N ; i++ ){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <=N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 1; i <=N ; i++ ){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <=N; j++){
                dirInfo[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        int sr = Integer.parseInt(st.nextToken());
        int sc = Integer.parseInt(st.nextToken());

        recurMove(sr, sc, dirInfo[sr][sc], 0);

        System.out.println(maxMoveCnt);
    }
    private static void recurMove(int r, int c, int d, int move){
        maxMoveCnt = Math.max(maxMoveCnt, move);
        for(int dist = 1; dist < N; dist++){
            int nr = r + dirs[d][0] * dist;
            int nc = c + dirs[d][1] * dist;
            if(!inArea(nr, nc)) continue;
            if(map[r][c] < map[nr][nc]){
                recurMove(nr, nc, dirInfo[nr][nc], move + 1);
            }
        }
    }
    static boolean inArea(int r, int c){
        return r > 0 && c > 0 && r <=N && c <=N;
    }
}