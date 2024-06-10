import java.util.*;
import java.io.*;
import java.awt.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int [][] map;
    static int N, R, C;
    static int [][] dirs ={{-1,0}, {1,0}, {0,-1}, {0,1}};

    public static void main(String[] args) throws Exception{
        input();
        bfs(R, C, map, sb);
        sout(sb.toString());

    }
    // 더이상 방문할 수 없을 때가지 이동, 그 과정에서 방문 숫자를 저장
    static void bfs(int sr, int sc, int [][] map, StringBuilder sb){
        ArrayDeque<Point> q = new ArrayDeque<>();
        q.addLast(new Point(sr,sc));
        while(!q.isEmpty()){
            Point out = q.pollFirst();
            sb.append(map[out.x][out.y]).append(" ");
            for(int d = 0; d < 4; d++){
                int nx = out.x + dirs[d][0];
                int ny = out.y + dirs[d][1];
                if(!inArea(nx,ny)) continue;
                if(map[nx][ny] > map[out.x][out.y]){
                    q.addLast(new Point(nx, ny));
                    break;
                }
            }
        }
    }

    static boolean inArea(int x, int y){
        return x >=0 && y >= 0 && x <N && y < N;
    }

    static void input() throws Exception{
        st = new StringTokenizer (br.readLine());
        N = toInt(st.nextToken());
        R = toInt(st.nextToken())-1;
        C = toInt(st.nextToken())-1;
        map = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0; j < N; j++){
                map[i][j] = toInt(st.nextToken());
            }
        }

    }
    static int toInt(String s){
        return Integer.parseInt(s);
    }
    static void sout(String s){
        System.out.println(s);
    }
}