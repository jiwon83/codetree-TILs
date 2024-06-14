import java.util.*;
import java.io.*;
public class Main {
    static final int DIR_CLOCK = 1;
    static final int DIR_REV_CLOCK = 0;
    static class Node{
        int r, c, num;
        public Node(int r, int c, int num){
            this.r = r;
            this.c = c;
            this.num= num;
        }
        public String toString(){
            return "( "+r+" , "+c+" ) : "+num;
        }
    }
    static class Map{

        int [][] innerMap;

        public Map(int N){
            this.innerMap = new int[N+1][N+1];
        }

        public void roate(int r, int c, int m1, int m2, int m3, int m4, int dir){
            // innerMap을 회전시킨다.
            // 직사각형을 만든다.
            List<Node> square = makeSquare(r, c, m1, m2, m3, m4);
            // System.out.println(square);

            // 직사각형 내부의 값들을 회전시킨다.
            List<Node> rotatedSquare = moveNumber(square, dir);

            // innerMap에 반영
            for(Node node: rotatedSquare){
                innerMap[node.r][node.c] = node.num;
            }

        }
        private List<Node> moveNumber(List<Node> square, int dir){
            if(dir == DIR_CLOCK){
                int preNum = square.get(square.size()-1).num;
                for(int to = square.size()-2; to >=0; to--){
                    int tmp = square.get(to).num;
                    int from = to - 1;
                    square.get(to).num = preNum;
                    preNum = tmp;
                }
                square.get(square.size()-1).num = preNum;
                
            }else if(dir == DIR_REV_CLOCK) {
                int preNum = square.get(0).num;
                for(int to = 1; to < square.size(); to++){
                    int tmp = square.get(to).num;
                    int from = to - 1;
                    square.get(to).num = preNum;
                    preNum = tmp;
                }
                square.get(0).num = preNum;

            }
            return square;
        }
        private List<Node> makeSquare(int r, int c, int m1, int m2, int m3, int m4){

            int [][] dirs ={{-1,1}, {-1,-1}, {1,-1}, {1,1}}; // 이 순서대로 탐색
            int [] moveNums = {m1, m2, m3, m4}; // 움직일 거리
            List<Node> square = new ArrayList<>();

            for(int d = 0; d < 4; d++){
                for(int num = 0; num < moveNums[d]; num++){
                    square.add(new Node(r, c, innerMap[r][c]));
                    r += dirs[d][0];
                    c += dirs[d][1];
                }
            }
            // for(int i = 0; i < m1; i++){
            //     square.add(new Node(r, c, innerMap[r][c]));
            //     r += dirs[0][0];
            //     c += dirs[0][1];
            // } 
            // for(int i = 0; i <m2; i++){
            //     square.add(new Node(r, c, innerMap[r][c]));
            //     r += dirs[1][0];
            //     c += dirs[1][1];
            // }
            // for(int i = 0; i <m3; i++){
            //     square.add(new Node(r, c, innerMap[r][c]));
            //     r += dirs[2][0];
            //     c += dirs[2][1];
            // }
            // for(int i = 0; i <m4; i++){
            //     square.add(new Node(r, c, innerMap[r][c]));
            //     r += dirs[3][0];
            //     c += dirs[3][1];
            // }
            return square;
        }
        public void printMap(){

            StringBuilder sb = new StringBuilder();
            for(int i = 1; i <innerMap.length; i++){
                for(int j = 1; j <innerMap.length; j++){
                    sb.append(innerMap[i][j]+" ");
                }
                sb.append("\n");
            }
            System.out.println(sb);

        }

    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        //input
        int N = Integer.parseInt(br.readLine());
        Map map = new Map(N);
        for(int i = 1; i <=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <=N; j++){
                map.innerMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        // sol
        int r =  Integer.parseInt(st.nextToken());
        int c =  Integer.parseInt(st.nextToken());
        int m1 =  Integer.parseInt(st.nextToken());
        int m2 =  Integer.parseInt(st.nextToken());
        int m3 =  Integer.parseInt(st.nextToken());
        int m4 =  Integer.parseInt(st.nextToken());
        int dir =  Integer.parseInt(st.nextToken());
        map.roate(r,c,m1,m2,m3,m4,dir);
        map.printMap();

    }
}