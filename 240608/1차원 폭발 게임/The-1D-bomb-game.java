import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M;
    static int [] map;

    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        N = toInt(st.nextToken());
        M = toInt(st.nextToken());
        map = new int[N];
        for(int i = 0; i < N; i++){
            map[i] = Integer.parseInt(br.readLine());
        }
        while(true){
            if(!bumb()) break;
        }
        printMap();
    }
    private static boolean bumb(){
        boolean isBumb = false;
        int i = 0;

        for(; i < N;){
            if(map[i] == 0){
                ++i;
                continue;
            } 
            int j = i;
            while(j + 1 < N && map[i] == map[j+1]){
                ++j;
            }

            int cnt = j - i + 1;
            if(cnt >= M){
                isBumb = true;
                for(int z = i; z <=j; z++){
                    map[z] = 0;
                }
            }
            i = j+1;

        }
        down();
        return isBumb;

    }
    private static void down(){
        // System.out.println("before === " + Arrays.toString(map));
        int [] tmp = new int[N];
        int idxTmpEnd = N-1;
        for(int i = N-1; i >=0; i--){
            if(map[i] != 0) tmp[idxTmpEnd--] = map[i];
        }
        map = tmp;
        // System.out.println("after === " + Arrays.toString(map));

    }
    private static void printMap(){
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for(int i = 0; i < N; i++){
            if(map[i] > 0 ){
                cnt++;
                sb.append(map[i]).append("\n");
            }
        }

        System.out.println(cnt);
        System.out.println(sb);

    }
    private static int toInt(String s){
        return Integer.parseInt(s);
    }
}