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
        for(int i = 0; i < N; i++){
            if(sequentialCount(arr, i, 1, N, M)) answer += 1;
            if(sequentialCount(arr, i, 0, N, M)) answer += 1;
        }
        return answer;
    }
    static boolean sequentialCount(int [][] arr, int idx, int isRow, int N, int M){
        // System.out.println(" idx = "+ idx + " isRow = "+ isRow);
        int number = -1;
        int count = 0;
        for(int j = 0; j < N; j++){
            if(isRow == 1){
                if(number == arr[j][idx]) count+=1;
                else {
                    number = arr[j][idx];
                    count = 1;
                }
            }else{
                if(number == arr[idx][j]) count+=1;
                else {
                    number = arr[idx][j];
                    count = 1;
                }
            }
            if(count >= M) return true;
        }
        return false;
    }
}