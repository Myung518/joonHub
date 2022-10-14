import java.util.*;
import java.io.*;
class Point{
	int x, y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class Main {
	static int n;
	static int[][] arr;
	static Queue<Point> q;
	static int[][] check;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		Point start = null;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 9) {
					start = new Point(i, j);
				}
			}
		}
		int result = 0;
		for (int i = 2; start != null; i++) {
			for (int j = 0; j < i; j++) {
				check = new int[n][n];
				q = new ArrayDeque<>();
				start = bfs(start, i);
				if (start == null)
					break;
				else {
					arr[start.x][start.y] = 9;
					result += check[start.x][start.y] - 1;
				}
			}
		}
		System.out.println(result);
	}
	static int[][] dis = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
	public static Point bfs(Point start, int size) {
		Point minp = null;
		q.add(start);
		check[start.x][start.y] = 1;
		arr[start.x][start.y] = 0;
		while (!q.isEmpty()) {
			start = q.poll();
			for (int[] tmp : dis) {
				int tx = start.x + tmp[0];
				int ty = start.y + tmp[1];
				if (tx >= 0 && tx < n && ty >= 0 && ty < n && check[tx][ty] == 0) {
					if (arr[tx][ty] <= size) {
						q.add(new Point(tx, ty));
						check[tx][ty] = check[start.x][start.y] + 1;
					}
					if (arr[tx][ty] < size && arr[tx][ty] != 0) {
						if (minp == null)
							minp = new Point(tx, ty);
						else if (check[minp.x][minp.y] > check[tx][ty])
							minp = new Point(tx, ty);
						else if (check[minp.x][minp.y] == check[tx][ty]) {
							if (minp.x > tx) {
								minp = new Point(tx, ty);
							}
							else if(minp.x == tx) {
								if (minp.y > ty) {
									minp = new Point(tx, ty);
								}
							}
						}
					}
				}
			}
		}
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++) {
//				System.out.print(check[i][j] + " ");
//			}
//			System.out.print("-----");
//			for (int j = 0; j < n; j++) {
//				System.out.print(arr[i][j] + " ");
//			}
//			System.out.println();
//		}
		return minp;
	}

}
