import java.util.*;
import java.io.*;

public class Main {

    static int [][] block1, block2, map;
    static int N,M;

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j <M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 여기에 코드를 작성해주세요.
        initBlocks();
        System.out.println(solution());

    }
    static void initBlocks(){ // 유의점 !! 왼쪽이랑 맨위에 빈공간이 있으면 고려하지 못하는 범위 발생!!
        block1 = new int[][]{
            {1, 0},
            {1, 1}
        };
        block2 = new int[][]{
            {1,1,1},
            {0,0,0},
            {0,0,0}
        };
    }
    static int solution(){
        int maxSum = 0;
        //block 1
        for(int r = 0; r < 4; r++){ // 총 4번의 회전
            for(int i = 0; i < N; i++){
                for(int j = 0; j <M; j++){
                    maxSum = Math.max(maxSum, getSum(i, j, block1, map));
                }
            }
            block1 = roatate90(block1);
        }

        //block2
        for(int r = 0; r < 2; r++){
            for(int i = 0; i < N; i++){
                for(int j = 0; j <M; j++){
                    maxSum = Math.max(maxSum, getSum(i, j, block2, map));
                }
            }
            block2 = roatate90(block2);
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
                    if(!inArea(ni, nj)) return -1;
                    sum += map[ni][nj];
                }
            }
        }
        return sum;
    }
    static boolean inArea(int r, int c){
        return r < N && c < M;
    }
}