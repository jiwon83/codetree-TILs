import java.util.*;
import java.io.*;
public class Main {
    static int [][] map;
    static int N=4;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        input();
        char dir = br.readLine().charAt(0);
        practiceOne(dir);
        printMap();

    }
    private static void input() throws IOException{
        map = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j <N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void practiceOne(char dir){
        switch(dir){
            case 'D':
                for(int c = 0; c < N; c++){
                    ArrayDeque<Integer> stack = new ArrayDeque<>();
                    boolean isJustCombined = false;

                    //1. 중력 후의 값을 stack에 저장
                    for(int r = N-1; r >= 0; r--){
                        int num = map[r][c];
                        if(num == 0 ) continue;
                        if(!stack.isEmpty() && !isJustCombined && num == stack.peekLast()){
                            stack.pollLast();
                            stack.addLast(num*2);
                            isJustCombined = true;
                        }else{
                            stack.addLast(num);
                            isJustCombined = false;
                        }

                    }
                    // 2. stack을 map에 반영
                    for(int r = N-1; r >=0; r--){
                        if(!stack.isEmpty()){
                            map[r][c] = stack.pollFirst();
                        }else{
                            map[r][c] = 0;
                        }
                    }
                }
            break;
            case 'R':
                for(int r = 0; r < N; r++){ 
                    ArrayDeque<Integer> stack = new ArrayDeque<>();
                    boolean isJustCombined = false;

                    //1. 중력 후의 값을 stack에 저장
                    for(int c = N-1; c >= 0; c--){
                        int num = map[r][c];
                        if(num == 0 ) continue;
                        if(!stack.isEmpty() && !isJustCombined && num == stack.peekLast()){
                            stack.pollLast();
                            stack.addLast(num*2);
                            isJustCombined = true;
                        }else{
                            stack.addLast(num);
                            isJustCombined = false;
                        }

                    }
                    // 2. stack을 map에 반영
                    for(int c = N-1; c >= 0; c--){
                        if(!stack.isEmpty()){
                            map[r][c] = stack.pollFirst();
                        }else{
                            map[r][c] = 0;
                        }
                    }
                }
            break;
            case 'U':
                for(int c = 0; c < N; c++){ 
                    ArrayDeque<Integer> stack = new ArrayDeque<>();
                    boolean isJustCombined = false;

                    //1. 중력 후의 값을 stack에 저장
                    for(int r = 0; r < N; r++){
                        int num = map[r][c];
                        if(num == 0 ) continue;
                        if(!stack.isEmpty() && !isJustCombined && num == stack.peekLast()){
                            stack.pollLast();
                            stack.addLast(num*2);
                            isJustCombined = true;
                        }else{
                            stack.addLast(num);
                            isJustCombined = false;
                        }

                    }
                    // 2. stack을 map에 반영
                    for(int r = 0; r < N; r++){
                        if(!stack.isEmpty()){
                            map[r][c] = stack.pollFirst();
                        }else{
                            map[r][c] = 0;
                        }
                    }
                }
            break;
            case 'L':
                for(int r = 0; r < N; r++){ 
                    ArrayDeque<Integer> stack = new ArrayDeque<>();
                    boolean isJustCombined = false;

                    //1. 중력 후의 값을 stack에 저장
                    for(int c = 0; c < N; c++){
                        int num = map[r][c];
                        if(num == 0 ) continue;
                        if(!stack.isEmpty() && !isJustCombined && num == stack.peekLast()){
                            stack.pollLast();
                            stack.addLast(num*2);
                            isJustCombined = true;
                        }else{
                            stack.addLast(num);
                            isJustCombined = false;
                        }

                    }
                    // 2. stack을 map에 반영
                    for(int c = 0; c < N; c++){
                        if(!stack.isEmpty()){
                            map[r][c] = stack.pollFirst();
                        }else{
                            map[r][c] = 0;
                        }
                    }
                }
            break;
        }
        
    }
    
    private static void printMap(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}