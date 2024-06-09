import java.util.*;
import java.io.*;
import java.awt.*;

public class Main {
    static int M, K, idxOfWall;
    static int N = 5;
    static int turnVal;
    static int [] wallFragments;
    static int [][] map, ch;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int [][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};

    static void input() throws Exception{
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        wallFragments = new int[M];
        map = new int[5][5];
        for(int i = 0; i < 5; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0; j < 5; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){
            wallFragments[i] =  Integer.parseInt(st.nextToken());
        }

    }
    public static void main(String[] args) throws Exception {
        input();
        for(int k = 0; k < K; k++){
            // System.out.println(" k = "+ k);
            turnVal = collect();
            if(turnVal == 0) break;
            while(fillAndRemove());
            // System.out.println(" turnVal = "+ turnVal);
            sb.append(turnVal).append(" ");
       } 
       System.out.println(sb);
    }
    
    // 최대 우선순위로 획득 가능한 좌표와 회전으로 유물을 만든다.
    static int collect(){
        // System.out.println(" =====collect ======= ");
        int x=-1; int y=-1; int maxVal = 0; int angle = -1;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(impossible3by3(i, j)) continue;
                int [][] tmp = new int[N][N];
                for(int a = 1; a <= 3; a++){
                    tmp = rotate(i, j, a, map);
                    // System.out.println("====rotate After======");
                    // printArr(tmp);
                    int val = getTotalVal(tmp);
                    // System.out.println("getTotalVal " + val); // ok
                    if(val > maxVal || (val == maxVal && isBetterCond(x, y, angle, i, j, a))){
                        x = i; y = j; maxVal = val; angle = a;
                    }
                }
            }
        }
        // System.out.println(" maxVal = "+ maxVal + " roate " + angle + " x = "+ x +" y= "+ y);
        if(maxVal > 0){
            map = rotate(x, y, angle, map);
            remove(map);
            // System.out.println(" after remove ");
            // printArr(map);
        }
        return maxVal;
        
    }
    static boolean impossible3by3(int x, int y){
        return x - 2 < 0 || y - 2 < 0 || x + 2 >=N || y + 2 >= N;
    }
    static boolean isBetterCond(int x, int y, int angle, int x2, int y2, int angle2){
        if(angle2 < angle) return true;
        else if(angle2 == angle){
            if(x2 < x) return true;
            else if(x2 == x){
                if(y2 < y) return true;
                else return false;
            }
            return false;
        }
        return false;
    }


    static int getTotalVal(int [][] arr){
        int sumOfVal = 0;
        ch = new int[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                int groupCnt = bfsGetGroupCnt(i, j, arr);
                if( groupCnt >= 3 ) sumOfVal += groupCnt;
                // if( groupCnt >= 3) {
                //     System.out.println("bfsGetGroupCnt .. " + i  +" , "+ j);
                //     printArr(arr);
                //     System.out.println("groupCnt = " + groupCnt);
                // }
            }
        }
        return sumOfVal;
    }

    static int bfsGetGroupCnt(int sr, int sc, int [][] arr){
        int count = 0;
        ArrayDeque<Point> q = new ArrayDeque<>();
        q.addLast(new Point(sr, sc));
        ch[sr][sc] = 1;
        while(!q.isEmpty()){
            Point out = q.pollFirst();
            count++;
            for(int d = 0; d < 4; d++){
                int nr = out.x + dirs[d][0];
                int nc = out.y + dirs[d][1];
                if(inArea(nr, nc) && ch[nr][nc] == 0 && arr[out.x][out.y] == arr[nr][nc]){
                    ch[nr][nc] = 1;
                    q.addLast(new Point(nr, nc));
                }
            }    
        }
        return count;
    }
    static void bfsRemove(int sr, int sc, int [][] arr, int [][] chRemove){
        int typeNum = arr[sr][sc];
        ArrayDeque<Point> q = new ArrayDeque<>();
        q.add(new Point(sr, sc));
        chRemove[sr][sc] = 1;
        arr[sr][sc] = 0;
        while(!q.isEmpty()){
            Point out = q.pollFirst();
            for(int d = 0; d < 4; d++){
                int nr = out.x + dirs[d][0];
                int nc = out.y + dirs[d][1];
                if(inArea(nr, nc) && chRemove[nr][nc] == 0 && typeNum == arr[nr][nc]){
                    chRemove[nr][nc] = 1;
                    arr[nr][nc] = 0;
                    q.addLast(new Point(nr, nc));
                }
            }
            
        }
    }
    static boolean remove(int [][] arr){
        ch = new int[N][N];
        boolean isReomved = false;
        int [][] chRemove = new int [N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                int groupCnt = bfsGetGroupCnt(i, j, arr);
                if( groupCnt >= 3 ){
                    turnVal += groupCnt;
                    isReomved = true;
                    bfsRemove(i, j, arr, chRemove);
                } 
            }
        }
        return isReomved;
        
    }
    // r, c 를 중점으로 agle 만큼 회전
    static int [][] rotate(int r, int c, int angle, int [][] arr){
        // System.out.println("====rotate ======" + "angle = "+ angle);
        // System.out.println("====before ======");
        // printArr(arr);
        int [][] tmp = new int[N][N];
        for(int i = 0; i < N; i++) tmp[i] = arr[i].clone();
        for(int i = 0; i < angle; i++){
            tmp = rotate90(r, c, tmp);
        }
        return tmp;
    }
    static int [][] rotate90(int r, int c, int [][] arr){
        int [][] tmp = new int[N][N];
        for(int i = 0; i < N; i++) tmp[i] = arr[i].clone();
        int sr = r - 1;
        int sc = c - 1;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                int nr = sc + j;//sr + i;
                int nc = sr + (2 - i);//sc + j;
                tmp[nr][nc] = arr[sr+i][sc+j];
            }
        }
        return tmp;
    }
    // 빈 공간을 벽에 있는 조각들로 채운다.
    static boolean fillAndRemove(){
        // System.out.println(" ====.  fill..   ======");
        for(int c = 0; c < N; c++ ){
            for(int r = N-1; r >= 0; r--){
                if(map[r][c] == 0){
                    map[r][c] = wallFragments[idxOfWall++];
                }
            }
        }
        // System.out.println(" ====.  fill.. After  ======");
        // printArr(map);
        return remove(map);
    }
    static boolean inArea(int x, int y){
        return x >= 0 && x < N && y >=0 && y <N;
    }
    static void printArr(int [][] ar){
        for(int i = 0; i < ar.length; i++){
            System.out.println(Arrays.toString(ar[i]));
        }
    }

}