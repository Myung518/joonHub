import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.stream.Stream;

class Point implements Comparable<Point>{
	int x, y, c;

	public Point(int x, int y, int c) {
		super();
		this.x = x;
		this.y = y;
		this.c = c;
	}
	
	@Override
	public int compareTo(Point o) {
		return this.c - o.c;
	}
}

public class Solution {
	static int N;
	static int[][] map;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				map[i] = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
			}
			sb.append(String.format("#%d ", t));
			bfs2();
			sb.append(result).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void bfs2() {
		int[][] dis = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };
		PriorityQueue<Point> q = new PriorityQueue<>();
		boolean[][] visited = new boolean[N][N];
		q.add(new Point(0, 0, 0));
		visited[0][0] = true;
		while (!q.isEmpty()) {
			Point now = q.poll();
			for (int[] d : dis) {
				int x = now.x + d[0];
				int y = now.y + d[1];
				if (x >= 0 && y >= 0 && x < N && y < N && !visited[x][y]) {
					visited[x][y] = true;
					q.add(new Point(x, y, now.c + map[x][y]));
					if (x == N-1 && y == N-1) {
						result = now.c + map[x][y];
						return;
					}
				}
			}
		}
	}

}