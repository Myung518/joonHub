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
	static int n, m;
	static int[][] arr;
	static Queue<Point> q;
	static boolean[][] check;
	static int[][] dis = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}, {-1, -1}, {1, 1}, {-1, 1}, {1, -1}};
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		while(!(n == 0 && m == 0)) {
			q = new ArrayDeque<>();
			arr = new int[m][n];
			check = new boolean[m][n];
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int result = 0;
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if(arr[i][j] == 1 && !check[i][j]) {
						result++;
						q.add(new Point(i, j));
						while (!q.isEmpty()) {
							Point p = q.poll();
							for (int[] tmp : dis) {
								int x = p.x + tmp[0];
								int y = p.y + tmp[1];
								if (x >= 0 && y >= 0 && x < m && y < n && arr[x][y] == 1 && !check[x][y]) {
									q.add(new Point(x, y));
									check[x][y] = true;
								}
							}
						}
					}
				}
			}
			System.out.println(result);
			
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
		}
	}
	

}
