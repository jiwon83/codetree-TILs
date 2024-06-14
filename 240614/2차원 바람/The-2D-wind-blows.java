import java.io.*;
import java.util.*;

public class Main {
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int [][] map;
    static int N, M, Q;
    static int [][] dirs = {{-1,0}, {1, 0}, {0,-1}, {0, 1}};
    public static void main(String[] args) throws Exception{
        assert (int)1.8 == 1;
        // input Area
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];
        for(int i =1; i <=N; i++){
            st= new StringTokenizer(br.readLine());
            for(int j = 1; j <=M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // sol Area
        // printMap(map);

        for(int q = 1; q<= Q; q++){
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            map = wind(r1, c1, r2, c2, map);
        }

        printMap(map);
    }
    static void printMap(int [][] arr){
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < arr.length; i++){
            for(int j  =1; j < arr[1].length; j++){
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");

        }
        System.out.println(sb);
    }
    static boolean inArea(int r, int c){
        return r > 0 && c > 0 && r <=N && c <=M;
    }
    static void wind(int r1, int c1, int r2, int c2, int [][] arr){
        arr = roatateEgde(arr);
        // System.out.println("경계 회전 후");
        // printMap(arr);
        arr = modify(r1,c1,r2,c2, arr);
    }
    static int [][] roatateEgde(int r1, int c1, int r2, int c2, int [][] arr){
        int tmp = arr[r1][c1];
        // up
        for(int r = r1; r < r2; r++){
            arr[r][c1] = arr[r+1][c1]; 
        }
        // left
        for(int c = c1; c < c2; c++){
            arr[r2][c] = arr[r2][c+1];
        }
        // down
        for(int r = r2; r > r1; r--){
            arr[r][c2] = arr[r-1][c2];
        }
        // right
        for(int c = c2; c > c1+1; c--){
            arr[r1][c] = arr[r1][c-1];
        }
        arr[r1][c1+1] = tmp;
        return arr;
    }
    // 동시에 일어나야 하므로 배열을 복사해서 사용해야 함.
    static int [][] modify(int r1, int c1, int r2, int c2, int [][] arr){
        int [][] tmp = cloneMap(arr);
        for(int r = r1; r <=r2; r++){
            for(int c = c1; c <= c2; c++){
                int sum = arr[r][c];
                int cnt = 1;
                for(int d = 0; d < 4; d++){
                    int nr = r + dirs[d][0];
                    int nc = c + dirs[d][1];
                    if( !inArea(nr,nc) ) continue;
                    sum += arr[nr][nc];
                    cnt += 1;
                }
                tmp[r][c] = sum / cnt;
            }
        }
        return tmp;
    }
    static int [][] cloneMap(int [][] arr){
        int [][] res = new int[N+1][M+1];
        for(int i = 1; i <=N; i++){
            res[i] = arr[i].clone();
        }
        return res;

    }
    

}