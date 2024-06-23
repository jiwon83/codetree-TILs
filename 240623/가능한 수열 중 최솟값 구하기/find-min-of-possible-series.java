import java.io.*;
import java.util.*;
public class Main {
    static int N;
    static String ans;
    static boolean find;
    static int [] ch = new int[3];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        recur(0, "");
        System.out.println(ans);

    }
    private static void recur(int k, String num){
        if(find) return;
        if(k == N){
            ans = num;
            find = true;
            return;
        }
        for(int i = 4; i <=6; i++){
            if(existPattern(num+i)) continue;
            recur(k+1, num+i);
        }
    }
    private static boolean existPattern(String number){
        ch = new int [3];
        int totalCnt = 0;

        for(int i = number.length()-1; i >=0 ; i--){
            int idx = number.charAt(i) - '4';
            if(ch[idx]==1){
                ch[idx] = 0;
                totalCnt -= 1;
            }else{
                ch[idx] = 1;
                totalCnt += 1;
            }
            if( totalCnt == 0) return true;

        }
        return false;
    }
}