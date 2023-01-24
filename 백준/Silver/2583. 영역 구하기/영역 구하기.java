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
	static int n, m, k;
	static boolean[][] arr;
	static boolean[][] check;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		arr = new boolean[n][m];
		check = new boolean[n][m];
		
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			for (int j = y1; j < y2; j++) {
				for (int k = x1; k < x2; k++) {
					arr[j][k] = true;
				}
			}
		}
		f();
		Collections.sort(resultList);
		System.out.println(resultList.size());
		for (int result : resultList) {
			System.out.print(result + " ");
		}
	}
	public static void f() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!check[i][j] && !arr[i][j]) {
					bfs(new Point(i, j));
				}
			}
		}
	}
	static Queue<Point> q = new ArrayDeque<>();
	static int[][] dis = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
	static ArrayList<Integer> resultList = new ArrayList<>();
	public static void bfs(Point start) {
		int count = 1;
		q.add(start);
		check[start.x][start.y] = true; 
		while(!q.isEmpty()) {
			start = q.poll();
			for (int[] tmp : dis) {
				int x = start.x + tmp[0];
				int y = start.y + tmp[1];
				if (x >= 0 && y >= 0 && x < n && y < m && !check[x][y] && !arr[x][y]) {
					check[x][y] = true;
					q.add(new Point(x, y));
					count++;
				}
			}
		}
		resultList.add(count);
	}

}
