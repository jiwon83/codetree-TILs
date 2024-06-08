import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int [] zenga;
    static int blockCnt;

    public static void main(String[] args)  throws Exception{
        N = toInt(br.readLine());
        zenga = new int[N+1];
        for(int i =1; i <= N; i++){
            zenga[i] = toInt(br.readLine());
        }
        for(int t = 0; t < 2; t++){
            st = new StringTokenizer(br.readLine());
            int from = toInt(st.nextToken());
            int to = toInt(st.nextToken());
            remove(from, to);
        }
        printZenga();
    }
    private static void remove(int from, int to){
        int tmp[] = new int[N+1];
        int tmpIdx = 1;
        for(int i = 1; i <=N; i++){
            if(!isInArea(i, from, to) ){
                tmp[tmpIdx++] = zenga[i];
            }
        }
        // sout(Arrays.toString(tmp));
        // for(int i = 1; i <=N; i++){
        //     arr[i] = tmp[i];
        // }
        zenga = tmp.clone();/*
        new arrrr[] = new ~;
        arr = arrrr;
        */
    }
    private static boolean isInArea(int i, int from, int to){
        return (from <= i && i <=to);
    }
    private static void printZenga(){
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <=N; i++){
            if(zenga[i] != 0) {
                blockCnt++;
                sb.append(zenga[i]).append("\n");
            }
        }
        System.out.println(blockCnt);
        System.out.println(sb);
    }
    private static int toInt(String s){
        return Integer.parseInt(s);
    }
    private static void sout(String s){
        System.out.println(s);
    }
}