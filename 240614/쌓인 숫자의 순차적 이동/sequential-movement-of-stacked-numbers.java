import java.util.Scanner;
import java.util.ArrayList;

class Pair { 
    int x, y;
    public Pair(int x, int y) { 
        this.x = x; 
        this.y = y; 
    } 
}

public class Main {
    public static final Pair OUT_OF_GRID = new Pair(-1, -1);
    public static final int DIR_NUM = 8;
    public static final int MAX_N = 20;
    
    public static int n, m;
    public static ArrayList<Integer>[][] grid = new ArrayList[MAX_N][MAX_N];
    
    public static Pair GetPos(int num) {
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                for(int k = 0; k < (int) grid[i][j].size(); k++)
                    if(grid[i][j].get(k) == num)
                        return new Pair(i, j);
        
        return new Pair(0, 0);
    }
    
    public static boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }
    
    // 그 다음 위치를 찾아 반환합니다.
    public static Pair NextPos(Pair pos) {
        int[] dx = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};
        
        int x = pos.x;
        int y = pos.y;
        
        // 인접한 8개의 칸 중 가장 값이 큰 위치를 찾아 반환합니다.
        int maxVal = -1;
        Pair maxPos = OUT_OF_GRID;
        for(int i = 0; i < 8; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            if(inRange(nx, ny)) {
                for(int j = 0; j < (int) grid[nx][ny].size(); j++) {
                    if(grid[nx][ny].get(j) > maxVal) {
                        maxVal = grid[nx][ny].get(j);
                        maxPos = new Pair(nx, ny);
                    }
                }
            }
        }
        
        return maxPos;
    }
    
    public static void move(Pair pos, Pair nextPos, int moveNum) {
        int x = pos.x;
        int y = pos.y;
        
        int nx = nextPos.x;
        int ny = nextPos.y;
        
        // Step1. (x, y) 위치에 있던 숫자들 중
        // moveNum 위에 있는 숫자들을 전부 옆 위치로 옮겨줍니다.
        boolean toMove = false;
        for(int i = 0; i < (int) grid[x][y].size(); i++) {
            if(grid[x][y].get(i) == moveNum)
                toMove = true;
            
            if(toMove)
                grid[nx][ny].add(grid[x][y].get(i));
        }
        
        // Step2. (x, y) 위치에 있던 숫자들 중
        // 움직인 숫자들을 전부 비워줍니다.
        while(grid[x][y].get(grid[x][y].size() - 1) != moveNum)
            grid[x][y].remove(grid[x][y].size() - 1);
        grid[x][y].remove(grid[x][y].size() - 1);
    }
    
    public static void simulate(int moveNum) {
        // 그 다음으로 나아가야할 위치를 구해
        // 해당 위치로 숫자들을 옮겨줍니다.
        Pair pos = GetPos(moveNum);
        Pair nextPos = NextPos(pos);
        if(nextPos != OUT_OF_GRID)
            move(pos, nextPos, moveNum);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                grid[i][j] = new ArrayList<>();
            
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++) {
                int num = sc.nextInt(); 
                grid[i][j].add(num);
            }
        
        // m번 시뮬레이션을 진행합니다.
        while(m-- > 0) {
            int moveNum = sc.nextInt();
            simulate(moveNum);
        }
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if((int) grid[i][j].size() == 0)
                    System.out.print("None");
                else {
                    for(int k = (int) grid[i][j].size() - 1; k >= 0; k--)
                        System.out.print(grid[i][j].get(k) + " ");
                }
                System.out.println();
            }
        }
    }
}