import java.util.*;
import java.io.*;
import java.awt.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M;
    static ArrayDeque<Integer> [][] map;
    static HashMap<Integer, Point> hm = new HashMap<>();
    static int [][] dirs = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}, {1, 0}, {-1,0}, {0,1}, {0,-1}};
    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new ArrayDeque [N+1][N+1];

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <=N; j++){
                int num = Integer.parseInt(st.nextToken());
                hm.put(num, new Point(i, j));
                map[i][j] = new ArrayDeque<>();
                map[i][j].addLast(num);
            }
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i <M; i++){
            int num = Integer.parseInt(st.nextToken());
            move(num);
        }
        printMap(map);
    }
    static void printMap(ArrayDeque<Integer> [][] map){
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <=N; j++){
                if(map[i][j].isEmpty()) sb.append("None");
                while(!map[i][j].isEmpty()){
                    sb.append(map[i][j].pollLast()).append(" ");
                }
                sb.append("\n");
            }
            
        }
        System.out.println(sb);
    }

    static void printMap(ArrayDeque<Integer> [][] map, String msg){
        System.out.println(" ==== "+ msg + " =====");
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <=N; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(" =================== ");
    }
    static void move(int num){

        Point pos = hm.get(num);
        int maxPosDir = -1, maxNum = -1, targetX = -1, targetY = -1;
        
        for(int d = 0; d < 8; d++){
            int nx = pos.x + dirs[d][0];
            int ny = pos.y + dirs[d][1];
            if( !inArea(nx, ny)) continue;

            // 해당 위치 가장 큰 숫자 확인
            ArrayDeque<Integer> queue = map[nx][ny];
            int nowMaxNum = getMaxNumInQueue(queue);
            if( nowMaxNum > maxNum){
                maxPosDir = d;
                maxNum = nowMaxNum;
                targetX = nx;
                targetY = ny;
            }
        }

        // System.out.println(" 목적 위치 "+ targetX + " , " + targetY + " 찾은 숫자 : "+ maxNum);

        // printMap(map, "before");
        // 숫자를 옮긴다.
        if(maxNum != -1){
            // 숫자위의 것까지 가져와서
            ArrayDeque<Integer> willMoveQ = getAndRemoveNumsQ(num, pos.x, pos.y, map);
            // 목적지의 맨위에 옮긴다.
            while(!willMoveQ.isEmpty()){
                int number = willMoveQ.pollFirst();
                map[targetX][targetY].addLast(number);
                hm.put(number, new Point(targetX, targetY));
            }
             
        }

        // printMap(map, "after");
    }
    static ArrayDeque<Integer> getAndRemoveNumsQ(int num, int r, int c, ArrayDeque<Integer> [][] map){
        ArrayDeque<Integer> result = new ArrayDeque<>();
        ArrayDeque<Integer> q = map[r][c];
        while(q.peekLast() != num){
            result.addFirst(q.pollLast());
        }
        result.addFirst(q.pollLast());
        return result;
    }
    static int getMaxNumInQueue(ArrayDeque<Integer> queue){
        Iterator<Integer> iter = queue.iterator();
        int maxNum = -1;
        while(iter.hasNext()){
            maxNum = Math.max(maxNum, iter.next());
        }
        return maxNum;
    }
    static boolean inArea(int r, int c){
        return r > 0 && c > 0 && r <= N && c <=N;
    }
}