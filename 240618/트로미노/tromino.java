import java.util.*;
import java.io.*;

public class Main {

    static int [][] block1,block2,block3, map;

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j <m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 여기에 코드를 작성해주세요.
        initBlocks();
        System.out.println(solution(n,  m));

    }
    static void initBlocks(){
        block1 = new int[][]{
            {1, 0},
            {1, 1}
        };
        block2 = new int[][]{
            {1,1,1}
        };
        block3 = new int[][]{
            {1},
            {1},
            {1}
        };
    }
    static int solution(int N, int M){
        int maxSum = 0;
        //block 1
        for(int r = 0; r < 4; r++){ // 총 4번의 회전
            for(int i = 0; i < N; i++){
                for(int j = 0; j <M; j++){
                    if(!inArea(i, j, block1.length, block1[0].length, N, M)) continue;
                    maxSum = Math.max(maxSum, getSum(i, j, block1, map));
                }
            }
            block1 = roatate90(block1);
        }

        //block2
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j <M; j++){
                if(!inArea(i, j, block2.length, block2[0].length, N, M)) continue;
                maxSum = Math.max(maxSum, getSum(i, j, block2, map));
            }
        }
       
        for(int i = 0; i < N; i++){
            for(int j = 0; j <M; j++){
                if(!inArea(i, j, block3.length, block3[0].length, N, M)) continue;
                maxSum = Math.max(maxSum, getSum(i, j, block3, map));
            }
        }
    
        return maxSum;

    }
    static int [][] roatate90(int [][] arr){
        
        int len = arr.length;
        int [][] tmp = new int[len][len];
        for(int r = 0; r < len; r++){
            for(int c = 0; c < len; c++){
                int nr = c;
                int nc = len - 1 - r;
                tmp[nr][nc] = arr[r][c];
            }   
        }
        return tmp;
    }
    static int getSum(int sr, int sc, int [][] block, int [][] map){
        int sum = 0;
        int len = block.length;
        for(int i = 0; i < len; i++){
            for(int j = 0; j < block[0].length; j++){
                int ni = sr + i;
                int nj = sc + j;
                if(block[i][j] > 0){
                    sum += map[ni][nj];
                }
            }
        }
        return sum;
    }
    static boolean inArea(int sr, int sc, int lenR, int lenC, int N, int M){
        return sr + lenR - 1 < N && sc + lenC - 1 < M;
    }
}