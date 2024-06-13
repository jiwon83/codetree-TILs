import java.util.*;
import java.io.*;

/**
 * 전체 시간복잡도 ( M * L * L )
 *
 * 이 문제에서 유의할 점
 * 연쇄적으로 밀리는 함수를 구현할 때, 이동하려는 방향의 끝에 벽이 있다면 모든 기사는 이동할 수 없게 된다
 * -> 이 부분을 BFS로 구현하기 위해 int [][] 배열에 기사의 영역에 대해 표시했다.
 * -> 또한, 명령이 기사의 번호로 주어지고, 죽음 여부를 빠르게 확인하게 할 수 있도록 HashMap으로 관리했다.
 * -> 명령을 받은 기사는 피해를 입지 않는 것에 유의
 *
 * Variables 참고
 * Person = 기사
 *
 */
public class Main {
   static class Person{
      int num, r, c, h, w, k, damage; // k = 체력
      boolean isDead;
      public Person(int num, int r, int c, int h, int w, int k){
         this.num = num;
         this.r = r;
         this.c = c;
         this.h = h;
         this.w = w;
         this.k = k;
         this.damage = 0;
         this.isDead = false;
      }
      public void move(int dir){
         this.r += dirs[dir][0];
         this.c += dirs[dir][1];
      }
   }
   static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   static StringTokenizer st;
   static int L, N, Q;
   static int [][] map; // 맵 정보
   static int [][] mapPersonNum; // 기사들의 번호 정보
   static HashMap<Integer, Person> personHMap = new HashMap<>(); // 기사들의 해쉬맵 정보
   static int [][] dirs = {{-1,0}, {0,1}, {1,0}, {0, -1}}; // up, right, down, left

   public static void main(String[] args) throws Exception{
      // input
      st = new StringTokenizer(br.readLine());
      L = Integer.parseInt(st.nextToken());
      N = Integer.parseInt(st.nextToken());
      Q = Integer.parseInt(st.nextToken());
      map = new int[L+1][L+1];
      mapPersonNum = new int[L+1][L+1];
      for(int i = 1; i <=L; i++){
         st = new StringTokenizer(br.readLine());
         for(int j = 1; j <= L; j++){
            map[i][j] = Integer.parseInt(st.nextToken());
         }
      }
      for(int i = 0; i < N; i++){
         int num = i+1;
         st = new StringTokenizer(br.readLine());
         int r = Integer.parseInt(st.nextToken());
         int c = Integer.parseInt(st.nextToken());
         int h = Integer.parseInt(st.nextToken());
         int w = Integer.parseInt(st.nextToken());
         int k = Integer.parseInt(st.nextToken());
         personHMap.put(num, new Person(num, r, c, h, w, k));
         markPersonInMap(personHMap.get(num), mapPersonNum);
      }

      // main process
      for(int q = 1; q <= Q; q++){
         st = new StringTokenizer(br.readLine());
         int personNum = Integer.parseInt(st.nextToken());
         int dir = Integer.parseInt(st.nextToken());
         if( personHMap.get(personNum).isDead ) continue; // 사라진 기사에 대한 명령에는 아무런 반응도 없다.
         sol(personNum, dir);
      }
      // 생존한 기사들의 총 damage 합 출력
      printTotalDamageALive();
   }
   static void printTotalDamageALive(){
      int sum = 0;
      for(Map.Entry<Integer, Person> entry : personHMap.entrySet()){
         if(entry.getValue().isDead) continue;
         sum += entry.getValue().damage;
      }
      sout(sum+"");
   }

   // 시간복잡도 O(맵의 크기^2)
   static void sol(int personNum, int dir){
      if(canMove(personNum, dir, map)){
         mapPersonNum = move(personNum, dir);
      }
   }
   static int [][] move(int startPersonNum, int dir){

      // 이동해야하는 기사들의 목록
      List<Integer> haveToMovePeople = getHaveToMove(startPersonNum, dir);

      // 이동
      for (int num : haveToMovePeople){
         Person person = personHMap.get(num);
         person.move(dir);
      }

      // 데미지 측정
      for (int num : haveToMovePeople){
         Person person = personHMap.get(num);
         if(person.num == startPersonNum) continue;
         int willDamage = 0;
         int endR = person.r + person.h - 1;
         int endC = person.c + person.w - 1;
         for(int i = person.r; i <= endR; i++){
            for(int j = person.c; j <=endC; j++){
               if(map[i][j]  == 1 ) willDamage += 1;
            }
         }
         person.damage += willDamage;
         person.k -= willDamage;
         if(person.k <= 0){ // 체스판에서 사라진다.
            person.isDead = true;
         }
      }

      //모든 기사들에 대해 배열 초기화
      return initMapByPeople(personHMap);

   }

   /////////////////// 여기서부터는 Util 및 내부 함수 ///////////////

   static int [][] initMapByPeople(HashMap<Integer, Person> personHMap){
      int tmp[][] = new int[L+1][L+1];
      for (int num = 1; num <=N; num++){
         Person person = personHMap.get(num);
         if(person.isDead) continue;
         markPersonInMap(person, tmp);
      }
      return tmp;
   }

   // 움직여야하는 기사 리스트를 받는다. BFS 인접 탐색
   static List<Integer> getHaveToMove(int startNum, int dir){
      List<Integer> result = new ArrayList<>(); // 움직여야하는 기사 리스트
      ArrayDeque<Integer> q = new ArrayDeque<>(); // 탐색에 사용할 큐
      int [] ch = new int[N+1]; // 고려한 기사 체크
      ch[startNum] = 1;
      q.addLast(startNum);
      result.add(startNum);
      while(!q.isEmpty()){
         int num =  q.pollFirst();
         Person out = personHMap.get(num);
         int endR = out.r + out.h - 1;
         int endC = out.c + out.w - 1;
         for(int r = out.r; r <= endR; r++){
            for(int c = out.c; c <= endC; c++){
               int nr = r + dirs[dir][0];
               int nc = c + dirs[dir][1];
               if(mapPersonNum[nr][nc] > 0 && ch[mapPersonNum[nr][nc]]==0 ){
                  ch[mapPersonNum[nr][nc]] = 1;
                  q.addLast(mapPersonNum[nr][nc]);
                  result.add(mapPersonNum[nr][nc]);
               }
            }
         }
      }
      return result;
   }

   // 이동 방향 끝 벽 존재 여부
   static boolean canMove(int startPersonNum, int dir, int[][] map){
      ArrayDeque<Integer> q = new ArrayDeque<>();
      int [] ch = new int[N+1]; // 고려한 기사 체크
      ch[startPersonNum] = 1;
      q.addLast(startPersonNum);
      while(!q.isEmpty()){
         int num =  q.pollFirst();
         Person out = personHMap.get(num);
         int endR = out.r + out.h - 1;
         int endC = out.c + out.w - 1;
         for(int r = out.r; r <= endR; r++){
            for(int c = out.c; c <= endC; c++){
               int nr = r + dirs[dir][0];
               int nc = c + dirs[dir][1];
               if(!inArea(nr, nc) || map[nr][nc] == 2){
                  return false;
               }
               if(mapPersonNum[nr][nc] > 0 && ch[mapPersonNum[nr][nc]]==0 ){
                  ch[mapPersonNum[nr][nc]] = 1;
                  q.addLast(mapPersonNum[nr][nc]);
               }
            }
         }
      }
      return true;
   }

   //map에 Person의 영역을 표시해주는 함수
   static void markPersonInMap(Person person, int [][] map){
      int endR = person.r + person.h - 1;
      int endC = person.c + person.w - 1;
      for(int r = person.r; r <= endR; r++){
         for(int c = person.c; c <= endC; c++){
            map[r][c] = person.num;
         }
      }
   }

   static boolean inArea(int r, int c){
      return r > 0 && c > 0 && r <= L && c <=L;
   }

   static void sout(String s){
      System.out.println(s);
   }

}