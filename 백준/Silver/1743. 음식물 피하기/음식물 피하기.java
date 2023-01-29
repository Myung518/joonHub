import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
	int x, y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static int n, m;
	static boolean[][] arr;
	static Queue<Point> q = new ArrayDeque<>();
	static boolean[][] check;
	static int[][] dis = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		arr = new boolean[n + 1][m + 1];
		check = new boolean[n + 1][m + 1];
		
		
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			arr[r][c] = true;
		}
		
		int max = Integer.MIN_VALUE;
		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < m + 1; j++) {
				if(arr[i][j] && !check[i][j]) {
					int result = 0;
					q.add(new Point(i, j));
					while (!q.isEmpty()) {
						Point p = q.poll();
						for (int[] tmp : dis) {
							int x = p.x + tmp[0];
							int y = p.y + tmp[1];
							if (x > 0 && y > 0 && x <= n && y <= m && arr[x][y] && !check[x][y]) {
								q.add(new Point(x, y));
								check[x][y] = true;
								result++;
							}
						}
					}
					max = Math.max(max, result);
				}
			}
		}
		System.out.println(max);

	}

}
