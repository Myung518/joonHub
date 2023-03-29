import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int k, w, h;
	static int[][] map;
	static int[][] disM = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static int[][] disH = { { 1, 2 }, { -1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 }, { -1, -2 }, { -2, -1 }, { -2, 1 } };
	static Queue<Point> q;
	static int[][][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		k = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		map = new int[w][h];
		for (int i = 0; i < w; i++) {
			map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		sb.append(bfs());
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static int bfs() {
		q = new ArrayDeque<>();
		visited = new int[w][h][k + 1];
		q.add(new Point(0, 0, 0));
		visited[0][0][0] = 1;
		while (!q.isEmpty()) {
			Point now = q.poll();
			if (now.x == w - 1 && now.y == h - 1) return visited[now.x][now.y][now.c] - 1;
			makeDis(now, disM);
			if (now.c < k) {
				makeDis(now, disH);
			}
		}
		return -1;
	}
	
	public static void makeDis(Point now, int[][] dis) {
		for (int[] d : dis) {
			int x = now.x + d[0];
			int y = now.y + d[1];
			int c = now.c + (dis.length == disM.length ? 0 : 1);
			
			if (x < 0 || y < 0 || x >= w || y >= h || map[x][y] == 1) continue;
			if (visited[x][y][c] == 0) {
				visited[x][y][c] = visited[now.x][now.y][now.c] + 1;
				q.add(new Point(x, y, c));
			}
		}
	}
	
	static class Point {
		int x, y, c;

		public Point(int x, int y, int c) {
			super();
			this.x = x;
			this.y = y;
			this.c = c;
		}
		
	}
}