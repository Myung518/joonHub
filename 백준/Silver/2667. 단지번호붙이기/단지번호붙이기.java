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
	static boolean[][] check;
	static Queue<Point> q = new ArrayDeque<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		check = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			int j = 0;
			for (char c : br.readLine().toCharArray()) {
				arr[i][j++] = c - '0';
			}
		}
		f();
		Collections.sort(list);
		System.out.println(list.size());
		for (int s : list) {
			System.out.println(s);
		}
	}
	
	public static void f() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (arr[i][j] == 1 && !check[i][j]) {
					bfs(new Point(i, j));
				}
			}
		}
	}
	
	public static ArrayList<Integer> list = new ArrayList<>();
	public static int[][] dis = { {-1, 0}, {0, -1}, {1, 0}, {0, 1} };
	public static void bfs(Point start) {
		int count = 1;
		q.add(start);
		check[start.x][start.y] = true;
		while (!q.isEmpty()) {
			start = q.poll();
			for (int[] tmp : dis) {
				int x = start.x + tmp[0];
				int y = start.y + tmp[1];
				if (x >= 0 && y >= 0 && x < n && y < n && !check[x][y] && arr[x][y] == 1) {
					q.add(new Point(x, y));
					check[x][y] = true;
					count++;
				}
			}
		}
		list.add(count);
	}

}
