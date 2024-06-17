import java.util.*;
import java.io.*;

public class Main{
	static class Marble implements Comparable<Marble>{
		int num, x, y, w, d;
		boolean isDead;
		public Marble(int num, int x, int y, int w, int d, boolean isDead) {
			super();
			this.num = num;
			this.x = x;
			this.y = y;
			this.w = w;
			this.d = d;
			this.isDead = isDead;
		}
		public int compareTo(Marble other) {
			if(this.w == other.w) {
				return Integer.compare(other.num, this.num);
			}
			return Integer.compare(other.w, this.w);
		}

		
	}
	
	static int N, T, lastCrashTime;
	static int OFFSET = 2000;
	static int MAX_LEN = 4000;
	static HashMap<Integer, Marble> marbleHmap = new HashMap<>();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int [][] map = new int [MAX_LEN+1][MAX_LEN+1];
	static int [][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
	static StringBuilder sbRes = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <=T; tc++) {
			lastCrashTime = -1;
			N = Integer.parseInt(br.readLine());
			for(int i = 1; i <=N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken()) * 2 + OFFSET;
				int y = Integer.parseInt(st.nextToken()) * 2 + OFFSET;
				int w = Integer.parseInt(st.nextToken());
				int d = getDir(st.nextToken().charAt(0));
				
				marbleHmap.put(i, new Marble(i, x, y, w, d, false));
			}
			// pro
			int maxTime = 2000;
			for(int time =1; time <=maxTime; time++) {
				pro(time);
				if(allmarbleIsDead(marbleHmap)) break;
				map = new int [MAX_LEN+1][MAX_LEN+1];
				
			}
			sbRes.append(lastCrashTime).append("\n");
		
			
			
		}
		System.out.println(sbRes);
		
	}
	private static void printMap(int from, int to, int [][] map) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		for(int x = from; x <=to; x++) {
			
			for(int y = from; y <=to; y++) {
				sb.append(map[x][y]+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
		
	}
	private static boolean allmarbleIsDead(HashMap<Integer, Marble> marbleHmap) {
		for(Map.Entry<Integer, Marble> entry : marbleHmap.entrySet()) {
			if(entry.getValue().isDead == false) return false;
		}
		return true;
	}
	static void pro(int time) {
		// 모든 구슬을 이동
		boolean isCrash = false;
		
		for(int num = 1; num <=N; num++) {
			Marble marble = marbleHmap.get(num);
			if(marble.isDead) continue;
			int nx = marble.x + dirs[marble.d][0];
			int ny = marble.y + dirs[marble.d][1];
			if(!inArea(nx, ny)) {
				marble.isDead = true;
				continue;
			}
			if(map[nx][ny] > 0) { //이미 다른 구슬이 있었다면
				isCrash = true;
				int otherNum = map[nx][ny];
				Marble other = marbleHmap.get(otherNum);
				if(marble.compareTo(other) < 0) {
					map[nx][ny] = marble.num;
					other.isDead = true;
				}else {
					marble.isDead = true;
				}	
			}else {
				map[nx][ny] = marble.num;
			}
			marble.x = nx;
			marble.y = ny;
		}
//		printMap(2000-4, 2000+4, map);
		if(isCrash) lastCrashTime = time;
//		System.out.println(time + " 초 후 ..lastCrashTime =" +lastCrashTime );

	}
	
	private static boolean inArea(int nx, int ny) {
		return nx >= 0 && ny >=0 && nx <= MAX_LEN && ny <= MAX_LEN;
	}

	static int getDir(char c) {
		if(c == 'U') return 0;
		if(c == 'D') return 1;
		if(c == 'R') return 2;
		if(c == 'L') return 3;
		return -1;
	}
	
}