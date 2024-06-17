import java.util.*;
import java.io.*;
import java.awt.*;
public class Main {

    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception{
        // 여기에 코드를 작성해주세요.
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int [][] arr = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j <N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solution(arr, N, M));
    }
    static int solution(int [][] arr, int N, int M){
        int answer = 0;
        int [] array = new int[N];
        // row
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                array[j] = arr[j][i];
            }
            if(sequentialCount(array , N, M)) answer += 1;
        }
        //col
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                array[j] = arr[i][j];
            }
            if(sequentialCount(array , N, M)) answer += 1;
        }
        return answer;
    }

    static boolean sequentialCount(int [] arr, int N, int M){
        int maxCount = 1;
        int sequenceCnt = 1;
        for(int j = 1; j < N; j++){
            if(arr[j-1] == arr[j]) sequenceCnt+=1;
            else sequenceCnt = 1;
            maxCount = Math.max(maxCount, sequenceCnt);
        }
        return maxCount >= M;
    }
}