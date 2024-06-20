import java.io.*;
import java.util.*;

public class Main {
    // need struct sample code
    static int maxSum, N; 
    static int [][] map;
    static int [][] dirs = {{-1,1}, {-1,-1}, {1,-1}, {1,1}};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws Exception{
        // 여기에 코드를 작성해주세요.
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0; j < N ;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        solution();
        System.out.println(maxSum);

    }
    private static void solution(){
        // 모든 시작점에서
        for(int i = 0; i < N; i++){
            for(int j =0; j < N ;j++){
                // 모든 직사각형 width, height를 갖는 직사각형을 만들어본다.
                for(int w = 1; w < N; w++){
                    for(int h = 1; h < N; h++){
                        maxSum = Math.max(maxSum, makeSquare(i,j, w,h));
                    }
                }
            }
        }
    }

    private static int makeSquare(int r, int c, int w, int h){
        int sum = 0;
        for(int i = 0; i < w; i++){
            r += dirs[0][0];
            c += dirs[0][1];
            if(!inArea(r, c)) return 0;
            sum += map[r][c];
        }
        for(int i = 0; i < h; i++){
            r += dirs[1][0];
            c += dirs[1][1];
            if(!inArea(r, c)) return 0;
            sum += map[r][c];
        }
        for(int i = 0; i < w; i++){
            r += dirs[2][0];
            c += dirs[2][1];
            if(!inArea(r, c)) return 0;
            sum += map[r][c];
        }
        for(int i = 0; i < h; i++){
            r += dirs[3][0];
            c += dirs[3][1];
            if(!inArea(r, c)) return 0;
            sum += map[r][c];
        }
        return sum;
    }
    static boolean inArea(int r, int c){
        return r >=0 && c >=0 && r <N && c <N;
    }
}