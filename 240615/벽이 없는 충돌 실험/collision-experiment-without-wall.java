/*
위치를 넘는 경우는 없는 가?
String compare 방법
*/
import java.util.*;
import java.io.*;
public class Main {

    static int [][] dirs = {{0,1}, {0,-1}, {-1,0}, {1,0}}; // up , down , l,  r

    static class Point{
        int x, y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        public boolean equals(Point p){
            return this.x == p.x && this.y == p.y;
        }
        public String toString(){
            return "("+x+","+ y+")";
        }
        
    }

    static class Ball implements Comparable<Ball>{
        Point pos, willPos;
        int num, weight, dir;

        public Ball(int num, int x, int y, int weight, int dir){
            this.pos = new Point(x, y);
            this.num = num;
            this.weight = weight;
            this.dir = dir;
            willPos = null;
            setWillPos(this.pos, this.dir);
        }
        public void setWillPos(Point pos, int dir){
            // 2초 뒤 위치를 셋팅
            int nx = pos.x + dirs[dir][0];
            int ny = pos.y + dirs[dir][1];
            willPos = new Point(nx, ny);
        }
        public void updateWillPos(Point pos, int dir){
            // 2초 뒤 위치를 셋팅
            int nx = pos.x + dirs[dir][0];
            int ny = pos.y + dirs[dir][1];
            willPos = new Point(nx, ny);
        }
        public int compareTo(Ball other){
            if(this.weight == other.weight){
                return Integer.compare(other.num, this.num);
            }
            return Integer.compare(other.weight, this.weight);
        }
        public String toString(){
            return num + "번: w=" + weight +" d= "+ dir +" "+ pos.toString() +  " "+ willPos.toString();
        }
    }
    static int N, lastTime;
    static List<Ball> balls;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {

        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <=T; t++){

            //input
            N = Integer.parseInt(br.readLine());
            balls = new ArrayList<Ball>();
            for(int i = 1; i <= N; i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                int d = getNumDir(st.nextToken());
                balls.add(new Ball(i, x, y, w, d));
            }

            // process
            lastTime = 0;
            int time = 0;
            for(int s = -1000; s <= 1000; s++){
                doProcess(N, time);
                time += 2;
            }

            // boolean isCrashed = false;
            // do{
            //     isCrashed = doProcess(N, lastTime);
            // }while(isCrashed);
            if(lastTime == 0) lastTime = -1;
            sb.append(lastTime).append("\n");
        }
        System.out.println(sb);
    }
    static void sout(String s){
        System.out.println(s);
    }
    static boolean doProcess(int ballCount, int startTime){
        // sout("doProcess");
        // sout(balls.toString());
        boolean isCrashed = false;
        
        List<Ball> tmpBalls = new ArrayList<>();// balls
        boolean [] die = new boolean[ballCount+1];
        // 모든 구슬의 이동
        
        // 1초 충돌 조건을 살핀다.
        for(Ball ball : balls){
            for(Ball ball2 : balls){
                if(die[ball2.num]) continue; // 이미 사라진 구슬은 제외
                if(ball.num == ball2.num) continue;
                if( ball.pos.equals(ball2.willPos) && ball2.pos.equals(ball.willPos) ){
                    isCrashed = true;
                    lastTime = startTime + 1;
                    if(ball.compareTo(ball2) < 0){
                        die[ball2.num] = true;
                    }else{
                        die[ball.num] = true;
                    }
                }
            }
        }
        // sout(" 1 초 후  die = ");
        // for(int i = 1; i <= N; i++){
        //     if( die[i] ) sout(i+" 번");
        // }
        // 살아남은 구슬에 대해 2초 충돌 조건을 살핀다.
        for(Ball ball : balls){
            if(die[ball.num]) continue;
            for(Ball ball2 : balls){
                if(die[ball2.num]) continue;
                if(ball.num == ball2.num) continue;
                if( ball.willPos.equals(ball2.willPos)){ // 최종 목적지가 같다면
                    isCrashed = true;
                    lastTime = startTime + 2;
                    if(ball.compareTo(ball2) < 0){
                        die[ball2.num] = true;
                    }else{
                        die[ball.num] = true;
                    }
                }

            }
        }
        // sout(" 2 초 후  die = ");
        // for(int i = 1; i <= N; i++){
        //     if( die[i] ) sout(i+" 번");
        // }

        // 살아남은 구슬들 확인
        for(Ball ball : balls){
            if(die[ball.num]) continue;
            tmpBalls.add(
                    new Ball(ball.num, ball.willPos.x, ball.willPos.y, ball.weight, ball.dir)
                ); //int num, int x, int y, int weight, int dir
        }
     
        balls = tmpBalls;
        
        return isCrashed;
    }
    static int getNumDir(String s){
        //{0,1}, {0,-1}, {-1,0}, {1,0}}; u, d, l, r
        if( s.equals("U")) return 0;
        if( s.equals("D")) return 1;
        if( s.equals("L")) return 2;
        if( s.equals("R")) return 3;
        return -1;
    }

}