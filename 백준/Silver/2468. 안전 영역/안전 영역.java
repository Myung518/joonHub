import java.util.*;
import java.io.*;

class Point {
	int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static int n;
	static int[][] arr;
	static Queue<Point> q = new ArrayDeque<>();
	static boolean[][] visited;
	static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		int max = Integer.MIN_VALUE;
		result = Integer.MIN_VALUE;
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				max = Math.max(max, arr[i][j]);
			}
		}
		height(max);
		if (result == 0) result = 1;
		System.out.println(result);
	}
	public static void height(int max) {
		for (int i = 1; i <= max; i++) {
			visited = new boolean[n][n];
			f(i);
		}
	}
	
	public static void f(int h) {
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j] && arr[i][j] > h) {
					visited[i][j] = true;
					bfs(new Point(i, j), h);
					count++;
				}
			}
		}
		result = Math.max(result, count);
	}
	
	static int[][] dis = { {-1, 0}, {0, -1}, {1, 0}, {0, 1} };
	public static void bfs(Point start, int h) {
		q.add(start);
		while(!q.isEmpty()) {
			start = q.poll();
			for (int[] tmp : dis) {
				int x = start.x + tmp[0];
				int y = start.y + tmp[1];
				if (x >= 0 && y >= 0 && x < n && y < n && !visited[x][y] && arr[x][y] > h) {
					q.add(new Point(x, y));
					visited[x][y] = true;
				}
			}
		}
		
	}

}
