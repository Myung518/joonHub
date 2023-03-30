import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int n;
	static int[][] map;
	static int[][] visited;
	static int[][] dis = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int t = 1;
		while ((n = Integer.parseInt(br.readLine())) != 0) {
			map = new int[n][n];
			visited = new int[n][n];
			for (int i = 0; i < n; i++) {
				Arrays.fill(visited[i], -1);
			}
			for (int i = 0; i < n; i++) {
				map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			sb.append(String.format("Problem %d: %d\n", t++, f()));
		}
		

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static int f() {
		Queue<Point> pq = new ArrayDeque<>();
		pq.add(new Point(0, 0));
		visited[0][0] = map[0][0];
		while (!pq.isEmpty()) {
			Point now = pq.poll();
			for (int[] d : dis) {
				int x = now.x + d[0];
				int y = now.y + d[1];
				if (x < 0 || y < 0 || x >= n || y >= n) continue;
				if (visited[x][y] != -1 && visited[x][y] <= visited[now.x][now.y] + map[x][y]) continue;
				visited[x][y] = visited[now.x][now.y] + map[x][y];
				pq.add(new Point(x, y));
			}
		}
		
		return visited[n-1][n-1];
	}
	
	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}