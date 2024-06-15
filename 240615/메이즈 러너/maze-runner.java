import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main{
	static class Person{
		int r, c, isEscaped, moveCnt;

		
		@Override
		public String toString() {
			return "Person [r=" + r + ", c=" + c + ", isEscaped=" + isEscaped + ", moveCnt=" + moveCnt + "]";
		}


		public Person(int r, int c) {
			super();
			this.r = r;
			this.c = c;
			isEscaped = 0;
			moveCnt = 0;
		}

		
	}
	static int [][] map; // 0 -> 벽, 1~9 출구
	static int exitR, exitC;
	static int K, M, N;

	static List<Person> personList = new ArrayList<>();
	

	public static void main(String[] args) throws Exception{

		input();		
	
		for(int k = 1; k <=K; k++) {
//			System.out.println("======== k : "+ k+ " ======== ");
			// 모든 참가자 이동
			allPersonMove(personList);
			
			// 미로 회전
			map = rotateMap(map);
			
			
			if(allpersonEscape(personList)) { // 모든 참가자 탈출
				break;
			}
		}
		//모든 참가자의 sum(이동거리, 출구좌표)
		int sum = 0;
		for(Person person: personList) {
			sum += person.moveCnt;
		}
		System.out.println(sum);
		System.out.println(exitR + " "+ exitC);
		
		
	}

	private static int[][] rotateMap(int[][] map) {
		
		
		int minLen = N+1; // 가장 작은 정사각형의 한변의 길이
		int minR = -1, minC = -1;
		// 가장 작은 정사각형 선정
		for(int r = 1; r <=N; r++) {
			for(int c = 1; c <=N; c++) {
				for(int len = 1; len < minLen; len++) {
					// r과 C를 시작점으로 하는 len 길이의 정사각형 탐색
					if(isSuccessCondition(r,c,len, map, personList)) {
						minLen = len;
						minR = r;
						minC = c;
					}
					
				}
				
			}
		}
//		System.out.println("가장 작은 정사각형 > minR = "+ minR + "  , minC = "+minC + " len = "+ minLen);
		
		// map 시계 방향 90도 회전 + 내구도 1씩 감소
		int [][] rotatedMap = cloneMap(map);
//		System.out.println(" 회전 전 ");
//		printMap(rotatedMap, "rotatedMap");
		
		for(int r = 0; r < minLen; r++) {
			for(int c = 0; c < minLen; c++) {
				int nr = minR + c;
				int nc = minC + (minLen - 1 - r);
				rotatedMap[nr][nc] = map[minR+r][minC+c];
				if(map[r+minR][c+minC] > 0) rotatedMap[nr][nc] -= 1;
		
			}
		}
//		System.out.println(" 회전 후 ");
//		printMap(rotatedMap, "rotatedMap");
		
		// 해당 범위내의 사람들도 90도 회전
		for(Person person : personList) {
			if(inArea(person.r, person.c, minR, minC, minR+ minLen-1, minC+minLen-1)) {
				
				Point nextPos = rotate90(person.r, person.c, minR, minC, minLen);
				person.r = nextPos.x;
				person.c = nextPos.y;
//				System.out.println("사람 "+ person);
			}
		}
		
		// exit도 90도 회전
		Point nextPos = rotate90(exitR, exitC, minR, minC, minLen);
		exitR = nextPos.x;
		exitC = nextPos.y;
		
//		System.out.println(" 회전 후 출구");
//		System.out.println(exitR+" , "+ exitC);
	
		
		return rotatedMap;
	}
	
	private static void printMap(int[][] arr, String string) {
		System.out.println(" ====== "+ string + " ====== ");
		for(int i = 1; i < arr.length; i++) {
			for(int j = 1; j < arr.length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println(" ====== ====== ");
	}

	private static int[][] cloneMap(int[][] map) {
		int [][] res = new int[map.length][map.length];
		for(int i = 0; i <map.length; i++) res[i] = map[i].clone();
		return res;
	}

	private static Point rotate90(int targetR, int targetC, int startR, int startC, int len) {
		int awayR = targetR - startR; // 시작점으로부터 떨어진 정도
		int awayC = targetC - startC;
		int nr = startR + awayC;
		int nc = startC + (len - 1 - awayR);
		return new Point(nr, nc);
	}

	private static boolean isSuccessCondition(int startR, int startC, int len, int[][] map, List<Person> personList) {
		// 참가자가 존재하고, exit이 존재해야함
		// exit이 존재하지 않으면 false
		int endR = startR+len -1;
		int endC = startC+len - 1;
		if(!inArea(exitR, exitC, startR, startC, endR, endC)) return false;
		for(int r = startR; r < startR+len; r++) {
			for(int c = startC; c < startC+len; c++) {
				for(Person person : personList) {
					if(person.isEscaped==1) continue;
					if(inArea(person.r, person.c, startR, startC, endR, endC)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	

	private static boolean inArea(int r, int c, int startR, int startC, int endR, int endC) {
		return r >= startR && r <= endR && c >= startC && c <= endC;
	}


	static int[][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};
	private static void allPersonMove(List<Main.Person> personList) {
//		System.out.println("all Person Move Befor >> ");
//		System.out.println(personList);
		for(Person person : personList) {
			if(person.isEscaped==1) continue;
			int preDist = getDist(person.r, person.c, exitR, exitC);
			int minDist = Integer.MAX_VALUE;
			int minDir = -1;
			
			for(int d = 0; d < 4; d++) {
				int nr = person.r  + dirs[d][0];
				int nc = person.c  + dirs[d][1];
				if(!inArea(nr,nc) || map[nr][nc] > 0) continue;
				int willDist = getDist(nr, nc, exitR, exitC);
				if(willDist < preDist && willDist < minDist ) {
					minDist = willDist;
					minDir = d;
				}
				
			}
			
			//출구이면
			if(minDir != -1) {
				person.r += dirs[minDir][0];
				person.c += dirs[minDir][1];
				person.moveCnt += 1;
				if(person.r == exitR && person.c == exitC) {
					person.isEscaped = 1;
				}
			}
		}
//		System.out.println("after");
//		System.out.println(personList);
//		
		
	}

	private static boolean inArea(int r, int c) {
		return r > 0 && c  > 0 && r <= N && c <=N;
	}

	private static int getDist(int r1, int c1, int r2, int c2) {
		return (int) (Math.abs(r1- r2) + Math.abs(c1 - c2));
	}

	private static void input() throws Exception{
		// TODO Auto-generated method stub
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N+1][N+1];
		
		for(int i = 1; i <=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j =1; j <=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 1; i <=M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			personList.add(new Person(r, c));
		}
		st = new StringTokenizer(br.readLine());
		exitR = Integer.parseInt(st.nextToken());
		exitC = Integer.parseInt(st.nextToken());
		
	}

	private static boolean allpersonEscape(List<Main.Person> personList) {
		for(Person person : personList) {
			if(person.isEscaped == 0) return false;
		}
		return true;
	}
	
}