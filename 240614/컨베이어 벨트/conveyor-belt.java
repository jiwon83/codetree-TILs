/*
컨베이어 벨트의 이동
// 1 2 3 6 5 1 -> 1 1 2 3 6 5
*/
import java.io.*;
import java.util.*;
public class Main {
    static int [] arr;
    static int N,T;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static void input() throws Exception{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        arr = new int[2*N];
        for(int j = 0; j < 2; j++){
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++){
                int idx = j * 3 + i;
                arr[idx] = Integer.parseInt(st.nextToken());
            }
        }
    }
    public static void main(String[] args) throws Exception{
        input();
        for(int t = 1; t <=T; t++){
            arr = move(arr, N);
        }
        printArr(arr);
    }
    static void printArr(int [] arr){
        for(int j = 0; j < 2; j++){
            for(int i = 0; i < N; i++){
                int idx = j * 3 + i;
                System.out.print(arr[idx]+ " ");
            }
            System.out.println();
        }

    }
    // 오른쪽으로 이동
    // static void move(int [] arr, int N){
    //     int tmp = arr[N-1];
    //     for(int i = N-1; i >=1; i--){ // 맨 오른쪽부터 순차 이동
    //         arr[i] = arr[i-1];
    //     }
    //     arr[0] = tmp;
    // }

    static int [] move(int [] arr, int N){
        int [] tmp = new int[N*2];
        tmp[0] = arr[N*2-1];
        for(int i = 1; i < N*2; i++){
            tmp[i] = arr[i-1];
        }
        return tmp;
    }
}