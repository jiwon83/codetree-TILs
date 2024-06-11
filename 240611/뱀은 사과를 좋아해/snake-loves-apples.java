import java.util.*;
import java.io.*;
import java.awt.*;
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M, K;
    static int [][] map; // 사과의 정보
    static ArrayDeque<Point> q = new ArrayDeque<>();//뱀의 정보

    static void input() throws Exception{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());  
        map = new int[N+1][N+1];
        for(int i = 0; i < M ;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = 1;
        }
        
    }

    public static void main(String[] args) throws Exception{
        input();
        q.addLast(new Point(1,1));
        int time = 0;

        // sout("start");
        // printSnake();
        // sout(" ----");

        L1: for(int k = 0; k < K; k++){
            st = new StringTokenizer(br.readLine());
            char dir = st.nextToken().charAt(0);
            int dist = Integer.parseInt(st.nextToken());
            // sout(" dir = "+ dir + " dist = "+ dist);

            for(int i = 1; i <=dist; i++){               
                time += 1;
                if(!moveSnake(dir)) break L1;
            }
            // printSnake();
        }

        sout(time+"");

    }
    static void sout(String s){
        System.out.println(s);
    }
    static boolean moveSnake(char dir){

        Point head = q.peekFirst();
        Point headNextPos = nextPos(head, dir);

        if(!inArea(headNextPos.x, headNextPos.y)) return false; // 이동할 수 없음
        if( reachSnake(headNextPos) ){
            return false; // 머리가 닿을 때

        } 

        if( map[headNextPos.x][headNextPos.y] == 1){ //사과라면
            map[headNextPos.x][headNextPos.y] = 0;
            q.addFirst(headNextPos);
        }else{
            q.addFirst(headNextPos);
            q.pollLast();
        }
        return true;
    }

    static boolean inArea(int x, int y){
        return x > 0 && y > 0 && x <=N && y <=N;
    }

    static boolean reachSnake(Point p){
        Iterator<Point> iter = q.iterator();
        while(iter.hasNext()){
            Point next = iter.next();
            if(next.x == p.x && next.y == p.y) return true;
        }
        return false; 
    }
    static int [][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    static Point nextPos(Point p, char dir){
        if(dir == 'U'){
            return new Point(p.x + dirs[0][0], p.y + dirs[0][1]);
        }
        else if(dir == 'D'){
            return new Point(p.x + dirs[1][0], p.y + dirs[1][1]);
        }
        else if(dir =='L'){
            return new Point(p.x + dirs[2][0], p.y + dirs[2][1]);
        }
        else{
            return new Point(p.x + dirs[3][0], p.y + dirs[3][1]);
        }

    }
    static void printSnake(){
        Iterator<Point> iter = q.iterator();
        while(iter.hasNext()){
            Point next = iter.next();
            sout(next.toString());
        }

    }


}