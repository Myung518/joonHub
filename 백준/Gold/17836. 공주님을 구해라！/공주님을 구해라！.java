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
	static int n, m, t;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		bw.write(f());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static String f() {
		int[][] dis = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
		Queue<Point> q = new ArrayDeque<>();
		int[][][] visited = new int[n][m][2];
		q.add(new Point(0, 0, 0));
		visited[0][0][0] = 1;
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			for (int[] d : dis) {
				int x = now.x + d[0];
				int y = now.y + d[1];
				int k = now.k;
				if (x < 0 || y < 0 || x >= n || y >= m || visited[x][y][now.k] != 0) continue;
				if (now.k == 0) {
					if (map[x][y] == 1) continue;
					if (map[x][y] == 2) k = 1;
				}
				q.add(new Point(x, y, k));
				visited[x][y][k] = visited[now.x][now.y][now.k] + 1;
				if (visited[x][y][k] - 1 > t) return "Fail";
				if (x == n - 1 && y == m - 1) return Integer.toString(visited[x][y][k] - 1);
			}
		}
		return "Fail";
	}
	
	static class Point {
		int x, y, k;

		public Point(int x, int y, int k) {
			super();
			this.x = x;
			this.y = y;
			this.k = k;
		}
		
		
	}

}