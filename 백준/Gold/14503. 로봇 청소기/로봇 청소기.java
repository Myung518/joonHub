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
	static boolean[][] check;
	static int n, m;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		check = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		robot(new Room(r, c), d);
		System.out.println(result);
	}
	static int[][] dis = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int result = 0;
	public static void robot(Room start, int d) {
		int tx = 0, ty = 0, i;
		if (!check[start.x][start.y] && arr[start.x][start.y] == 0) {
			check[start.x][start.y] = true;
			result++;
		}
		int point = 0;
		for (i = 3; i >= 0; i--) {
			point = i + d;
			if (d + i >= 4) {
				point -= 4;
			}
			tx = start.x + dis[point][0];
			ty = start.y + dis[point][1];
			if (tx < n && tx >= 0 && ty < m && ty >= 0 && !check[tx][ty] && arr[tx][ty] == 0) {
				break;
			}
		}
		if (i == -1) {
			point = d + 2;
			if (point >= 4) point -= 4;
			tx = start.x + dis[point][0];
			ty = start.y + dis[point][1];
			if (tx < n && tx >= 0 && ty < m && ty >= 0 && arr[tx][ty] == 0) {
				robot(new Room(tx, ty), d);
			}
			else return;
		}
		else robot(new Room(tx, ty), point);
		return;
		
	}
}