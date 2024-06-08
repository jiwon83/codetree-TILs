import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int [][] map;
    static int N;


    public static void main(String[] args)  throws Exception{
        N = toInt(br.readLine());
        map = new int[N+1][N+1];
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <=N; j++){
                map[i][j] = toInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        int x = toInt(st.nextToken());
        int y = toInt(st.nextToken());
        bump(x, y, map[x][y]);
        down(map);
        printMap();

    }
    static int [][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    // bump baaaam!
    private static void bomb(int x, int y, int size){
        map[x][y] = 0;
        for(int d = 0; d < 4; d++){
            for(int s =1; s < size; s++){
                int nx = x + dirs[d][0]*s;
                int ny = y + dirs[d][1]*s;
                if(inArea(nx, ny)){
                    map[nx][ny] = 0;
                }
            }
        }

    }
    // gravity down
    private static void down(int [][] map){
        for(int c = 1; c <= N; c++){
            int [] tmp = new int[N+1];
            int endTmpIdx = N;
            for(int r = N; r > 0; r--){
                if( map[r][c] > 0 ){
                    tmp[endTmpIdx--] = map[r][c];
                }
            }
            for(int r = N; r > 0; r--){
                map[r][c] = tmp[r];
            }
        }
    }

    private static void printMap(){
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <=N; i++){
            for(int j = 1; j <=N; j++){
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
   
    private static boolean inArea(int x, int y){
        return x > 0 && y > 0 && x <=N && y <=N;
    }
    
    private static int toInt(String s){
        return Integer.parseInt(s);
    }
    
}