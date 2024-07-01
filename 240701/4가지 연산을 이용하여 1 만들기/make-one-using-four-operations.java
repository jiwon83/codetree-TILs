import java.util.*;
import java.io.*;
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