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

        // 여기에 코드를 작성해주세요.
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
            int cnt = 1;
            while(j + 1 < N && (map[i] == map[j+1] || map[j+1] == 0)){
                ++j;
                if(map[j] != 0) cnt++;
            }
            
            if(cnt >= M){
                isBumb = true;
                for(int z = i; z <=j; z++){
                    map[z] = 0;
                }
            }
            i = j+1;

        }
        return isBumb;

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