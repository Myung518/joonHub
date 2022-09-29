import java.util.*;
import java.io.*;
class Xy{
	int y; 
	int x;
	
	public Xy(int y, int x) {
		// TODO Auto-generated constructor stub
		this.y = y;
		this.x = x;
	}
}
public class Main {
	static int[][] arr;
	static boolean[][] check;
	static int r, c;
	static Queue<Xy> q = new ArrayDeque<>();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		arr = new int[r][c];
		check = new boolean[r][c];
		for (int i = 0; i < r; i++) {
			String s = br.readLine();
			for (int j = 0; j < c; j++) {
				arr[i][j] = s.charAt(j) - '0';
			}
		}
		st = new StringTokenizer(br.readLine(), " ");
		int y = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		f(y, x, k, arr[y][x]);
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				sb.append(arr[i][j]);
			}
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	static int[][] dis = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
	public static void f(int y, int x, int k, int m) { // bfs 시간초과
		arr[y][x] = k;
		check[y][x] = true;
		q.add(new Xy(y, x));
		while (!q.isEmpty()) {
			Xy start = q.poll();
			int starty = start.y;
			int startx = start.x;
			for (int[] tmp : dis) {
				int ty = starty + tmp[0];
				int tx = startx + tmp[1];
				if (ty < r && ty >= 0 && tx < c && tx >= 0 && arr[ty][tx] == m && !check[ty][tx]) {
					check[ty][tx] = true;
					arr[ty][tx] = k;
					q.add(new Xy(ty, tx));
				}
			}
		}
	}
	public static void f2(int y, int x, int k, int m) {//dfs
		arr[y][x] = k;
		check[y][x] = true;
		for (int[] tmp : dis) {
			int ty = y + tmp[0];
			int tx = x + tmp[1];
			if (ty < r && ty >= 0 && tx < c && tx >= 0 && arr[ty][tx] == m && !check[ty][tx]) {
				check[ty][tx] = true;
				f2(ty, tx, k, m);
			}
		}
	}

}
