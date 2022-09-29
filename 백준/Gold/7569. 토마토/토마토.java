import java.io.*;
import java.util.*;
class Tomato{
	int x; 
	int y;
	int z;
	
	public Tomato(int x, int y, int z) {
		// TODO Auto-generated constructor stub
		this.y = y;
		this.x = x;
		this.z = z;
	}
}
public class Main {
	static int[][][] arr;
	static boolean[][][] check;
	static Queue<Tomato> q = new ArrayDeque<>();
	static int n, m, h;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		arr = new int[h][m][n];
		check = new boolean[h][m][n];
		for (int k = 0; k < h; k++) {
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					arr[k][i][j] = Integer.parseInt(st.nextToken());
					if (arr[k][i][j] == 1) {
						q.add(new Tomato(i, j, k));
						check[k][i][j] = true;
					}
				}
			}
		}
		f();
		
		int max = 0;
		boolean onoff = true;
		for (int k = 0; k < h; k++) {
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if (arr[k][i][j] == 0) {
						onoff = false;
						break;
					}
					max = Math.max(max, arr[k][i][j]);
				}
			}
		}
		if (!onoff) System.out.println(-1);
		else System.out.println(max - 1);
	}
	static int[][] dis = {{0, 0, -1}, {0, 0, 1}, {0, 1, 0}, {0, -1, 0}, {1, 0, 0}, {-1, 0, 0}};
	public static void f() {
		while (!q.isEmpty()) {
			Tomato t = q.poll();
			for (int[] d : dis) {
				int tx = t.x + d[1];
				int ty = t.y + d[2];
				int tz = t.z + d[0];
				if (tz < h && tz >= 0 && ty < n && ty >= 0 && tx < m && tx >= 0 && 
							arr[tz][tx][ty] != -1 && !check[tz][tx][ty]) {
					q.add(new Tomato(tx, ty, tz));
					check[tz][tx][ty] = true;
					arr[tz][tx][ty] = arr[t.z][t.x][t.y] + 1; 
				}
			}
		}
	}

}
