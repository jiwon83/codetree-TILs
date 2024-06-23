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
        // System.out.println(num);
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
        // ch = new int [3];
        // int totalCnt = 0;
        // System.out.println(number);
        int R = number.length()-1;

        loop : for(int L = number.length()-2; L >=0 ; L--){
            
            if(number.charAt(L) == number.charAt(R)){
                int len = R - L;
                for(int i = 0; i < len; i++){
                    if( L - i < 0) return false;
                    if(number.charAt(R-i) != number.charAt(L-i)) continue loop;
                }
                return true;
            }
        
        }
        return false;
    }

    private static boolean existPattern2(String number){
        // ch = new int [3];
        // int totalCnt = 0;
        System.out.println(number);
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        for(int i = number.length()-1; i >=0 ; i--){
            int num = number.charAt(i) - '4';

            if(!deque.isEmpty() && deque.peekLast() == num){
                deque.pollLast();
            }else{
                deque.addFirst(num);
            }
            if(deque.isEmpty()) return true;

            // if(ch[idx]==1){
            //     ch[idx] = 0;
            //     totalCnt -= 1;
            // }else{
            //     ch[idx] = 1;
            //     totalCnt += 1;
            // }
            // if( totalCnt == 0) return true;

        }
        return false;
    }
}