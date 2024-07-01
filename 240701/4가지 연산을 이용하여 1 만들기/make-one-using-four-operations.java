import java.util.*;
import java.io.*;
/*
배운점 : 현재 MAX+n 이 10 ^ 6으로 여기서 /2가 무조건 이득이지만, 
나우어 떨어지지 않을 경우 MAX_n + 여러번이 더 유리할 수도 있으므로 간편하게 N * 2를 가능한 숫자의 최댓값으로 잡으면 될 것 같다. 
*/
public class Main {
    static class Info{
        int num, cnt;
        public Info(int num, int cnt){
            this.num = num;
            this.cnt = cnt;
        }
    }
    static int MAX_N = 1000000;
    static int [] ch = new int[MAX_N * 2 -1];
    static int [] operNum = {1, 1, 3, 2};
    static char [] oper = {'+', '-', '/', '/' };
    
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        System.out.println(bfs(number));
    }
    public static int bfs(int number){
        ArrayDeque<Info> q = new ArrayDeque<>();
        q.add(new Info(number, 0));
        ch[number] = 1;

        while(!q.isEmpty()){

            Info out = q.pollFirst();
            if(out.num == 1) return out.cnt;

            for(int c = 0; c < 4; c++){
                int nextNumber = calculate(out.num, oper[c], operNum[c]);
                if(nextNumber <= MAX_N && ch[nextNumber]==0){
                    ch[nextNumber] =  1;
                    q.addLast(new Info(nextNumber, out.cnt+1));
                }
            }
        }
        return number;
    }
    static int calculate(int num, char oper, int operNum){
        if(oper == '+') return num + operNum;
        if(oper == '-') return num - operNum;
        if(oper == '/' && (num % operNum == 0)) return num / operNum;
        return MAX_N+1;
    }
}