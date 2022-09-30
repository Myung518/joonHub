import java.io.*;
import java.util.*;
class Room{
	int x, y;
	public Room(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class Main {
	
	static int[][] arr;
	static int n, m;
	static int countV = 0, countW = 0;
	static boolean[][] checkV;
	static int max = 0;
	static Queue<Room> q = new ArrayDeque<>();
	static Set<Room> v = new HashSet<>();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		checkW = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 2) {
					v.add(new Room(i, j));
				}
				if (arr[i][j] == 1) {
					countW++;
				}
			}
		}
		wall(0);
		System.out.println(n * m - max - countW - 3);
	}
	static boolean[][] checkW;
	public static void wall(int w) {
		if (w == 3) {
			for (Room o : v) {
				q.add(o);
			}
			virus();
			if (max == 0) max = countV;
			else max = Math.min(max, countV);
			return;
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == 0 && !checkW[i][j]) {
					arr[i][j] = 1;
					if (w == 0) {
						checkW[i][j] = true;
					}
					wall(w + 1);
					arr[i][j] = 0;
				}
			}
		}
	}
	
	static int[][] dis = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
	public static void virus() {
		checkV = new boolean[n][m];
		countV = 0;
		while (!q.isEmpty()) {
			Room r = q.poll();
			countV++;
			for (int[] tmp : dis) {
				int tx = r.x + tmp[0];
				int ty = r.y + tmp[1];
				if (tx < n && tx >= 0 && ty < m && ty >= 0 && arr[tx][ty] == 0 && !checkV[tx][ty]) {
					q.add(new Room(tx, ty));
					checkV[tx][ty] = true;
				}
			}
		}
	}

}
