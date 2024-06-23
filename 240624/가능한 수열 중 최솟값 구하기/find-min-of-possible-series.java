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
            if(existPattern(num+i)) continue; // 패턴이 존재하면 만들지 않는다.
            recur(k+1, num+i); // 패턴이 존재하지 않을 경우에만
        }
    }
    private static boolean existPattern(String number){
        int end = number.length()-1; // R 이전에는 패턴이 존재하지 않음.

        for(int end2 = number.length()-2; end2 >=0 ; end2--){
            
            if(number.charAt(end2) == number.charAt(end)){ // ~ last number 형태의 패턴이 성사 가능한 지점을 찾는다.
                int len = end2 - end1;
                int start2 = end2 - len + 1;
                int start1 = end2 + 1;
                if( start2 < 0) return false;
                isSameWord(len, start2, start1, number) return true;
            }
        
        }
        return false;
    }
    private boolean isSameWord(int len, int s1, int s2, String word){
        for(int i = 0; i < len; i++){
            if(word.charAt(s1+i) != word.charAt(s2+i)) return false;
        }
        return true;
    }

}