import java.util.*;
import java.io.*;
/*
이 문제에서는 List<Marble> [][] map 으로 관리하는 게 더 편함.
*/
public class Main {
    static class Marble implements Comparable<Marble>{
        int r, c, num, speed, dir;
        public Marble(int r, int c, int num, int speed, int dir){
            this.r = r;
            this.c = c;
            this.num = num;
            this.speed = speed;
            this.dir = dir;
        }
        public int compareTo(Marble other){
            if(this.speed == other.speed) return other.num - this.num;
            return Integer.compare(other.speed, this.speed);
        }
        public String toString(){
            return "(num = "+num+" , speed = "+speed+", dir = "+dir+")";
        }
    }
    static int MAX_N = 50;
    static List<Marble> [][] map;
    static int N, M, T, K;
    static int [][] dirs = {{-1,0}, {0, 1}, {0,-1}, {1,0}}; // u, r, l, d
    static int [] mapper = new int[128];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        // input
        mapper['U'] = 0;
        mapper['D'] = 3;
        mapper['R'] = 1;
        mapper['L'] = 2;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = initMap();
        for(int i = 1; i <=M; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            char d = st.nextToken().charAt(0);
            int v = Integer.parseInt(st.nextToken());
            map[r][c].add(new Marble(r, c, i, v, mapper[d]));
        }
        
      
        // main logic
        for(int t = 1; t <=T; t++){
            
            List<Marble> [][] nextMap = initMap(); // 다음의 map을 저장할 배열 초기화

            for(int i = 1; i <=N ; i++){
                for(int j = 1; j <=N; j++) {
                    for(Marble marble : map[i][j]){ // 모든 구슬의 이동
                        move(marble, nextMap);
                    }
                }
            }
            map = nextMap; // map을 바꿔준다.
        }
        printMarblesCnt();
    }
    private static void printMarblesCnt() {
    	int cnt = 0;
    	for(int i = 1; i <=N ; i++){
            for(int j = 1; j <=N; j++) {
                cnt += map[i][j].size();
            }
        }
    	System.out.println(cnt);
		
	}
	static void move(Marble marble, List<Marble> [][] nextMap){
        int speed = marble.speed;
        int num = marble.num;
        for(int i = 1; i <= speed; i++){

            if(nextIsWall(marble.r, marble.c, marble.dir)){
                marble.dir = 3 - marble.dir;
            }
            // if 다음이 벽이면 바로 방향을 바꾸고 이동
            marble.r += dirs[marble.dir][0];
            marble.c += dirs[marble.dir][1];
        }
        nextMap[marble.r][marble.c].add(marble); // next위치로 옮긴다.
        if(nextMap[marble.r][marble.c].size() > K){
            List<Marble> list = nextMap[marble.r][marble.c];
            Collections.sort(list);
            list.remove(list.size()-1);
        }

    }
    static boolean nextIsWall(int r, int c, int dir){
        int nextR = r + dirs[dir][0];
        int nextC = c + dirs[dir][1];
        return nextR <= 0 || nextC <=0 || nextR > N || nextC > N;
    }
    static ArrayList<Marble>[][] initMap(){
        ArrayList<Marble>[][] tmp = new ArrayList[MAX_N+1][MAX_N+1];
        for(int i = 1; i <=N ; i++){
            for(int j = 1; j <=N; j++) {
                tmp[i][j] = new ArrayList<Marble>();
            }
        }
        return tmp;
    }
}