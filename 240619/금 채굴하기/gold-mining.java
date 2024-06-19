import java.io.*;
import java.util.*;
public class Main {
    static int N,M;
    static int [][] map;
    static int maxCount;
    static int [][] dirs = {{-1,0}, {1,0}, {0,1}, {0,-1}};
    static class Info{
        int r, c, dist;
        public Info(int r, int c, int dist){
            this.r =r ;
            this.c = c;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for(int r = 0; r < N; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < N; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        solution();
        System.out.println(maxCount);

    }
    // 최대 금 갯수 반환
    private static void solution(){
        // 모든 중점에 대해
        for(int r = 0; r < N; r++){
            for(int c = 0; c < N; c++){
                // 모든 K를 가정
                for(int k = 0; k < N; k++){
                    // 증점과 k 로 마름모를 만들고 금의 갯수를 계산
                    int count = makeRhombus(r, c, k);
                    // System.out.println(r + " , "+ c + ". k ="+ k);
                    // System.out.println("금의 갯수 = "+ count);
                    int cost = getCost(k);
                    // System.out.println("비용 = "+ cost);
                    if(isBeneficial(count, M, cost)){
                        maxCount = Math.max(maxCount, count);
                    }
                }

            }
        }
    }
    static int getCost(int K){
        return K * K + (K+1) * (K+1);
    }
    static int makeRhombus(int r, int c, int k){
        int count = 0;
        int [][] ch = new int[N][N];
        ArrayDeque<Info> q = new ArrayDeque<>();
        q.addLast(new Info(r,c,0));
        ch[r][c] = 1;

        while(!q.isEmpty()){
            Info info = q.pollFirst();
            if(map[info.r][info.c] == 1) count += 1;
            if(info.dist == k) continue;
            for(int d = 0; d < 4; d ++){
                int nr = info.r + dirs[d][0];
                int nc = info.c + dirs[d][1];
                if(!inArea(nr,nc)) continue;
                if(ch[nr][nc] ==1 ) continue;
                ch[nr][nc] = 1;
                q.addLast(new Info(nr,nc, info.dist+1));
            }
        }
        return count;
    }
    static boolean isBeneficial(int goldCnt, int profit, int cost){
        return goldCnt * profit >= cost;
    }
    static boolean inArea(int r, int c){
        return r >=0 && c >=0 && r <N && c <N;
    }
}