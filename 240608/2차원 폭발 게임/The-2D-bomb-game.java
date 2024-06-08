import java.util.*;
import java.io.*;
/*
step 1: 
Explodes Bomb continuous number (cnt >= M, standard = ROW)
Gravitiy Down 

steo 2:
Rotate 90
Gravitiy Down 

return : cnt of left bombs
*/

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M, K, cntOfDeldeted;
    static int [][] map;

    public static void main(String[] args) throws Exception{
        // 여기에 코드를 작성해주세요.
        input();
        for(int k = 0; k < K; k++){
            while(explodes());
            gravityDown();
            rotate90();
            gravityDown();
        }
        while(explodes());
        printCnt();
        // printMap();
    }
    static void input() throws Exception{
        st = new StringTokenizer(br.readLine());
        N = toInt(st.nextToken());
        M = toInt(st.nextToken());
        K = toInt(st.nextToken());
        map = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = toInt(st.nextToken());
            }
        }

    }
    static void printCnt(){
        int cnt = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j] > 0) cnt++;
            }
        }
        System.out.println(cnt);
    }
    static void printMap(){
       
        System.out.println("----------");
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                System.out.print(map[i][j]);
                
            }
            System.out.println();
        }

        System.out.println("----------");
    }
    // explode
    static boolean explodes(){
        // System.out.println("explodes !");
        // printMap();
        // explodes sequential bombs
        boolean isExploded = false;
        for(int c = 0; c < N; c++){
            int i = 0;
            for(;i < N;){
                if(map[i][c] == 0) {
                    i++;
                    continue;
                }
                int j = i;
                while(j+1 < N && map[i][c] == map[j+1][c]){
                    ++j;
                }
                int cnt = j - i +1;
                if(cnt >= M){
                    isExploded = true;
                    for(int r = i; r <= j; r++) map[r][c] = 0;
                }
                i = j+1;
            }
        }
        return isExploded;
        // System.out.println("after !");
        // printMap();
    }
    // rotate
    static void rotate90(){
        // System.out.println("rotate90 !");
        int [][] tmp = new int[N][N];
        for(int i = 0; i < N; i++) tmp[i] = map[i].clone();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                int ni = j;
                int nj = (N-1) - i;
                tmp[ni][nj] = map[i][j];
            }
        }
        for(int i = 0; i < N; i++) map[i] = tmp[i].clone();
        // System.out.println(" rotate90 after !");
        // printMap();
    }

    static void gravityDown(){
        for(int c = 0; c < N; c++){
            int [] tmp = new int[N];
            int endOfTmp = N-1;
            for(int r = N-1; r >= 0; r--){
                if(map[r][c] > 0) tmp[endOfTmp--] = map[r][c];
            }
            for(int r = N-1; r >=0; r--){
                map[r][c] = tmp[r];
            }
        }
    }

    static int toInt(String s){
        return Integer.parseInt(s);
    }
}