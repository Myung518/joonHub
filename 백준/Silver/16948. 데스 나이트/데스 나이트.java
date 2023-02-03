import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
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

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n][n];
		st = new StringTokenizer(br.readLine());
		int r1 = Integer.parseInt(st.nextToken());
		int c1 = Integer.parseInt(st.nextToken());
		int r2 = Integer.parseInt(st.nextToken());
		int c2 = Integer.parseInt(st.nextToken());
		
		int[][] dis = {{-2, -1}, {-2, 1}, {0, -2}, {0, 2}, {2, -1}, {2, 1}};
		Queue<Point> q = new ArrayDeque<>();
		int[][] check = new int[n][n];
		q.add(new Point(r1, c1));
		check[r1][c1] = 1;
		OUT : while(!q.isEmpty()) {
			Point p = q.poll();
			for (int[] tmp : dis) {
				int x = p.x + tmp[0];
				int y = p.y + tmp[1];
				if (x >= 0 && y >= 0 && x < n && y < n && (check[x][y] == 0)) {
					q.add(new Point(x, y));
					check[x][y] = check[p.x][p.y] + 1;
					if (x == r2 && y == c2) {
						System.out.println(check[x][y] - 1);
						break OUT;
					}
				}
			}
		}
		if (check[r2][c2] == 0) {
			System.out.println(-1);
		}
	}

}
