import java.util.*;
import java.io.*;
import java.awt.Point;

public class Main {
    static int [][] ladder;
    static int [][] active;
    static int N, M;
    static final int ROW = 15;
    static int [] ch; // idx 번째 사다리의 사용 유무
    static int [] result; // 맨 처음 결과
    static List<Point> ladderInfos = new ArrayList<>();
    static int minLadderCount;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ladder = new int[ROW+1][N+1];
        active = new int[ROW+1][N+1];
        for(int i = 1; i <=M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladder[b][a] = 1;
            ladderInfos.add(new Point(b,a));
        }
        // 맨처음 결과를 저장
        ch = new int[M];
        Arrays.fill(ch, 1); // 처음에는 모든 사다리 활성화
        activateLadders(ch); 
        result = new int[N+1];
        for(int person = 1; person <= N; person++){
            int number = climbing(person);
            result[number] = person;
        }
        minLadderCount = M; // 최대 수는 M

        // System.out.println(" 맨 처음 결과 : "+ Arrays.toString(result));

        System.out.println(solution());

    }
    //주어진 정답을 만드는 데 필요한 최소 가로줄 수
    private static int solution(){
        // 모든 경우의 수의 사다리를 선택하고
        ch = new int[M];
        recur(0, 0);
        // 그때의 사다리로 얻는 결과를 갱신
        return minLadderCount;
    }
    // m 개의 가로줄 중에 선택
    private static void recur(int k, int chooseCnt){
        if(k == M){
            // System.out.println(" 선택한 사다리 ");
            // System.out.println(Arrays.toString(ch));
            // 가로줄에 대해서만 active [] 표시
            activateLadders(ch);
            // 모든 사람을 시뮬레이션
            int [] simulResult = new int[N+1];
            for(int person = 1; person <= N; person++){
                int number = climbing(person);
                simulResult[number] = person;
            }
            // System.out.println(" 예상 결과");
            // System.out.println(Arrays.toString(simulResult));

            if(isSameResult(result, simulResult)){
                minLadderCount = Math.min(minLadderCount, chooseCnt);
            }
            return;
        }
        ch[k] = 0;
        recur(k+1, chooseCnt);
        ch[k] = 1;
        recur(k+1, chooseCnt+1);
    }
    private static boolean isSameResult(int [] a, int [] b){
        for(int i = 1; i <=N; i++){
            if(a[i] != b[i]) return false; 
        }
        return true;
    }
    private static void activateLadders(int [] ch){
        for(int i = 0; i < M; i++){
            Point pos = ladderInfos.get(i);
            if(ch[i] == 1){
                active[pos.x][pos.y] = 1;
            }else{
                active[pos.x][pos.y] = 0;
            }
        }

    }
    private static int climbing(int col){ // 시작위치
        // 세로줄을 한칸씩 이동하면서
        for(int r = 1; r <= ROW; r ++){
            if( active[r][col-1] ==1 && ladder[r][col - 1] > 0){ // 왼쪽 연결사다리가 있다면
                col -= 1;
            }
            else if(active[r][col] ==1 && ladder[r][col] > 0){ // 오른쪽 사다리가 있다면
                col += 1;
            }
        }
        return col; // 도착 위치를 반환
    }
}