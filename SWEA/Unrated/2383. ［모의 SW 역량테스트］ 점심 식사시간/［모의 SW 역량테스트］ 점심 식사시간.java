
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 아래층으로 이동하는 시간 = 계단 입구까지 이동 시간 + 계단을 내려가는 시간
 * 계단 입구까지 이동 시간 = |사람 P의 세로위치 - 계단 입구 S의 세로위치| + |사람 P의 가로위치 - 계단 입구 S의 가로위치|
 * 계단을 내려가는 시간
        - 계단을 내려가는 시간은 계단 입구에 도착한 후부터 계단을 완전히 내려갈 때까지의 시간이다.
        - 계단 입구에 도착하면, 1분 후 아래칸으로 내려 갈 수 있다.
        - 계단 위에는 동시에 최대 3명까지만 올라가 있을 수 있다.
        - 이미 계단을 3명이 내려가고 있는 경우, 그 중 한 명이 계단을 완전히 내려갈 때까지 계단 입구에서 대기해야 한다.
        - 계단마다 길이 K가 주어지며, 계단에 올라간 후 완전히 내려가는데 K 분이 걸린다.
 */
public class Solution {
	static int n;
	static int[][] map;
	static List<Point> steps;
	static List<Point> persons;
	static List<Integer>[] combiCheck;
	static int[][] memoList;
	static int min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			min = Integer.MAX_VALUE;
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			steps = new ArrayList<>();
			persons = new ArrayList<>();
			int personCount = -1;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] > 1) {
						steps.add(new Point(i, j, map[i][j]));
					}
					if (map[i][j] == 1) {
						map[i][j] = personCount;
						persons.add(new Point(i, j));
						personCount -= 1;
					}
				}
			}
			memoList = new int[steps.size()][persons.size()];
			combiCheck = new ArrayList[steps.size()];
			for (int i = 0; i < steps.size(); i++) {
				combiCheck[i] = new ArrayList<>();
			}
			for (int i = 0; i < steps.size(); i++) {
				stepBfs(i);
			}
			f(0);
			
			sb.append((String.format("#%d %d\n", t, min)));
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void stepBfs(int index) {
		Point start = steps.get(index);
		Queue<Point> q = new ArrayDeque<>();
		boolean[][] check = new boolean[n][n];
		int[][] dis = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
		q.add(new Point(start.x, start.y, 0));
		check[start.x][start.y] = true;
		while (!q.isEmpty()) {
			Point now = q.poll();
			for (int[] d : dis) {
				int x = now.x + d[0];
				int y = now.y + d[1];
				if (x < 0 || y < 0 || x >= n || y >= n || check[x][y]) continue;
				check[x][y] = true;
				if (map[x][y] < 0) {
					memoList[index][Math.abs(map[x][y]) - 1] = now.l + 1;
				}
				q.add(new Point(x, y, now.l + 1));
			}
		}
		
	}
	
	//계단과 사람 조합
	public static void f(int personNum) { 
		if (personNum == persons.size()) {
			PriorityQueue<Time2> pq2 = new PriorityQueue<>();
			for (int i = 0; i < combiCheck.length; i++) {
				for (int person : combiCheck[i]) {
					pq2.add(new Time2(i, memoList[i][person]));
				}
			}
			min = Math.min(min, TimeCalc(pq2));
			return;
		}
		for (int i = 0; i < steps.size(); i++) {
			combiCheck[i].add(personNum);
			f(personNum + 1);
			combiCheck[i].remove(Integer.valueOf(personNum));
		}
	}
	
	public static int TimeCalc(PriorityQueue<Time2> pq) {
		Queue<Integer>[] q = new ArrayDeque[steps.size()];
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < steps.size(); i++) {
			q[i] = new ArrayDeque<>();
		}
		while (!pq.isEmpty()) {
			Time2 now = pq.poll();
			if (q[now.stepNum].size() == 3) {
				if (q[now.stepNum].peek() >= now.time + 1) { //대기자
					q[now.stepNum].add(q[now.stepNum].peek() + steps.get(now.stepNum).l);
				} else {
					q[now.stepNum].add(now.time + 1 + steps.get(now.stepNum).l);
				}
				max = Math.max(max, q[now.stepNum].poll());
				continue;
			}
			q[now.stepNum].add(now.time + 1 + steps.get(now.stepNum).l); //계단 입구 대기 시간 추가, 계단 걸리는 시간 추가
		}
		for (int i = 0; i < steps.size(); i++) {
			while (!q[i].isEmpty()) {
				max = Math.max(max, q[i].poll());				
			}
		}
		return max;
	}
	
	static class Time2 implements Comparable<Time2>{
		int stepNum, time;

		public Time2(int stepNum, int time) {
			super();
			this.stepNum = stepNum;
			this.time = time;
		}
		
		@Override
		public String toString() {
			return "Time2 [stepNum=" + stepNum + ", time=" + time + "]";
		}

		@Override
		public int compareTo(Time2 o) {
			return this.time - o.time;
		}
	}

	static class Point {
		int x, y, l;

		public Point(int x, int y, int l) {
			super();
			this.x = x;
			this.y = y;
			this.l = l;
		}

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}
}
