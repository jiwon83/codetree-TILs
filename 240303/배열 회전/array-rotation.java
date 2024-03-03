import java.util.*;
import java.io.*;

public class Main {
    static List<Integer> [] layers;
    static int N,M,K, depthSize;
    static StringTokenizer st ;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] dirs = {{1,0}, {0,1}, {-1,0}, {0,-1}};
    static int [][] map;


    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        

        depthSize = Math.min(N, M) / 2;
        layers = new ArrayList[depthSize];
        for(int i = 0; i <depthSize; i++) layers[i]= new ArrayList<>();
        map = new int[N][M];
        for(int i = 0; i< N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j <M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for(int d = 0; d <depthSize; d++){

            int h = d != 0 ? N / (2 * d) : N;
            // if( d != 0 &&  (N & 1) == 1) h += 1;
            int w = d != 0 ? M / (2 * d): M;
            // if( d != 0 &&  (M & 1) == 1) w += 1;
            // System.out.println("h = "+ h + " w = "+ w);

            for(int down = 0; down < h -1 ; down++){
                int r = d + dirs[0][0] * down;
                int c = d + dirs[0][1] * down;
                layers[d].add(map[r][c]);
            }
            for(int right = 0; right < w -1 ; right++){
                int r = d + h -1 + dirs[1][0] * right;
                int c = d + dirs[1][1] * right;
                layers[d].add(map[r][c]);
            }
            for(int up = 0; up < h -1 ; up++){
                int r = d + h - 1 + dirs[2][0] * up;
                int c = d + w - 1 + dirs[2][1] * up;
                layers[d].add(map[r][c]);
            }
            for(int left = 0; left < w -1 ; left++){
                int r = d + dirs[3][0] * left;
                int c = d + w - 1 + dirs[3][1] * left;
                layers[d].add(map[r][c]);
            }
        }
        // System.out.println(Arrays.toString(layers));
        
    }
    public static void sol() {
        
        int [][] res = new int[N][M];
        for(int i = 0; i< N; i++) res[i] = map[i].clone();

        for(int d = 0; d <depthSize; d++){
            if(layers[d].size() == 0) break;
            int idxL = layers[d].size() - 1 - (K % layers[d].size());
            // System.out.println("idxL = "+ idxL);

            int h =  h = d != 0 ? N / (2 * d): N;
            // if( d != 0 &&  (N & 1) == 1) h += 1;
            int w =  w = d != 0 ? M / (2 * d) : M;
            // if( d != 0 &&  (M & 1) == 1) w += 1;

            for(int down = 0; down < h -1 ; down++){
                int r = d + dirs[0][0] * down;
                int c = d + dirs[0][1] * down;
                idxL = nextPos(idxL, layers[d].size());
                res[r][c] = layers[d].get(idxL);
            }
            for(int right = 0; right < w -1 ; right++){
                int r = d + h -1 + dirs[1][0] * right;
                int c = d + dirs[1][1] * right;
                idxL = nextPos(idxL, layers[d].size());
                res[r][c] = layers[d].get(idxL);
            }
            for(int up = 0; up < h -1 ; up++){
                int r = d + h - 1 + dirs[2][0] * up;
                int c = d + w - 1 + dirs[2][1] * up;
                idxL = nextPos(idxL, layers[d].size());
                res[r][c] = layers[d].get(idxL);
            }
            for(int left = 0; left < w -1 ; left++){
                int r = d + dirs[3][0] * left;
                int c = d + w - 1 + dirs[3][1] * left;
                idxL = nextPos(idxL, layers[d].size());
                res[r][c] = layers[d].get(idxL);
            }
        }
        printMap(res);
    }
    static int nextPos(int k, int len){
        return k + 1 < len ? k+1 : 0;
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
        input();
        sol();

    }
}