import java.io.*;
import java.util.*;
class Tomato{
	int x; 
	int y;
	
	public Tomato(int x, int y) {
		// TODO Auto-generated constructor stub
		this.y = y;
		this.x = x;
	}
}
public class Main {
	static int[][] arr;
	static boolean[][] check;
	static Queue<Tomato> q = new ArrayDeque<>();
	static int n, m;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[m][n];
		check = new boolean[m][n];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 1) {
					q.add(new Tomato(i, j));
					check[i][j] = true;
				}
			}
		}
		f();
		int max = 0;
		boolean onoff = true;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (arr[i][j] == 0) {
					onoff = false;
					break;
				}
				max = Math.max(max, arr[i][j]);
			}
		}
		if (!onoff) System.out.println(-1);
		else System.out.println(max - 1);
	}
	static int[][] dis = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
	public static void f() {
		while (!q.isEmpty()) {
			Tomato t = q.poll();
			for (int[] d : dis) {
				int tx = t.x + d[0];
				int ty = t.y + d[1];
				if (ty < n && ty >= 0 && tx < m && tx >= 0 && arr[tx][ty] != -1 && !check[tx][ty]) {
					q.add(new Tomato(tx, ty));
					check[tx][ty] = true;
					arr[tx][ty] = arr[t.x][t.y] + 1; 
				}
			}
		}
	}
}
