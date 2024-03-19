import java.util.*;
import java.awt.*;
import java.io.*;

public class Main {
    static class Info{
        Point p; int num;
        public Info(Point p, int num){
            this.p = p;
            this.num = num;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static ArrayList<Info> coins = new ArrayList<>();
    static int [] selected = new int[3]; //coins 인덱스 번호를 저장
    static int [] ch;
    static int MAX;
    static int ans;
    static Point S,E;
    static int N;
 
    public static void main(String[] args) throws IOException {

       
        // 여기에 코드를 작성해주세요.
        N = Integer.parseInt(br.readLine());
        MAX = 3 * 2 * N;
        ans = MAX;

        for(int i=0; i <N; i++){
            String s = br.readLine();
            for(int j = 0; j < N; j++){
                if(s.charAt(j)=='S') S = new Point(i,j);
                if(s.charAt(j)=='E') E = new Point(i,j);
                if(s.charAt(j) >='1' && s.charAt(j) <='9') coins.add(new Info(new Point(i,j), s.charAt(j)-'0' ));
            }
        }
        coins.sort((o1, o2)-> o1.num - o2.num);
        ch = new int[coins.size()];
        // System.out.println("입력 완료 : "+ coins.get(0).num + " ~"+ coins.get(coins.size()-1).num);
        //  System.out.println("S = "+ S + " E = "+ E);
        System.out.println(sol());
    }
    public static int sol(){
        if(coins.size() == 2 ) return -1;
        recur(0, -1);
        return ans;
    }
    public static void recur(int k, int pre){
        
        if(k == 3){
            // 해당 경로에 대해서 최소 이동거리 구하기
            // System.out.println("compelete.."+Arrays.toString(selected));
        
            int sumDist = 0;
            sumDist += getDist(S, coins.get(selected[0]).p);
            // System.out.println("S -> 0 : "+sumDist);
            for(int i = 1; i < 3; i++){
                sumDist += getDist(coins.get(selected[i-1]).p, coins.get(selected[i]).p);
            }
            sumDist += getDist( coins.get(selected[2]).p, E);
            // sumDist -= 4;
            // System.out.println("total = "+ sumDist);
            ans = Math.min(ans, sumDist);
            return;
        }
        // 코인 선택
        for(int i = pre+1; i < coins.size(); i++){
            // if(ch[i]==1) continue;
            selected[k] = i;
            // ch[i] = 1;
            recur(k+1, i);
            // ch[i] = 0;
        }

    }
    private static int getDist(Point start, Point end){
        // System.out.println("getDist "+ start + " ~ "+ end);
        // System.out.println(Math.abs(start.x - end.x) + 1 + Math.abs(start.y - end.y) + 1 - 1);
        return Math.abs(start.x - end.x) + 1 + Math.abs(start.y - end.y)- 1;
    }
}