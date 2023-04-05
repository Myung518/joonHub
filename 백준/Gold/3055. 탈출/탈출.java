import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int r, c;
	static char[][] map;
	static Queue<Point> q;
	static Queue<Point> no;
	static boolean[][] visited;
	static int[][] dis = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];
		q = new ArrayDeque<>();
		no = new ArrayDeque<>();
		visited = new boolean[r][c];
		for (int i = 0; i < r; i++) {
			String s = br.readLine();
			for (int j = 0; j < c; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'S') {
					map[i][j] = '.';
					visited[i][j] = true;
					q.add(new Point(i, j));
				} else if (map[i][j] == '*') {
					no.add(new Point(i, j));
				}
			}
		}
		int result = move();
		sb.append(result == -1 ? "KAKTUS" : result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void flood(int num) {
		while (!no.isEmpty() && no.peek().c == num) {
			Point now = no.poll();
			
			for (int[] d : dis) {
				int x = now.x + d[0];
				int y = now.y + d[1];
				
				if (x < 0 || y < 0 || x >= r || y >= c || map[x][y] != '.') continue;
				no.add(new Point(x, y, now.c + 1));
				map[x][y] = '*';
			}
		}
	}
	
	public static int move() {
		int count = -1;

		while (!q.isEmpty()) {
			Point now = q.poll();
			
			if (count != now.c) {
				count = now.c;
				flood(count);
			}
			
			for (int[] d : dis) {
				int x = now.x + d[0];
				int y = now.y + d[1];
				
				if (x < 0 || y < 0 || x >= r || y >= c) continue;
				if (map[x][y] == 'D') return now.c + 1;
				if (visited[x][y]) continue;
				if (map[x][y] != '.') continue;
				visited[x][y] = true;
				q.add(new Point(x, y, now.c + 1));
			}
		}
		return -1;
	}
	
	
	static class Point {
		int x, y, c;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
			this.c = 0;
		}

		public Point(int x, int y, int c) {
			super();
			this.x = x;
			this.y = y;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", c=" + c + "]";
		}
		
		
	}
}