import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

class Point {
	int x, y;

	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}
public class Main {
	static int n;
	static char[][] input;
	static int[][] dis = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		input = new char[n][n];
		
		for (int i = 0; i < n; i++) {
			input[i] = br.readLine().toCharArray();
		}
		
		visited = new boolean[n][n];
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(visited[i][j]) continue;
				f(new Point(i, j));
				count++;
			}
		}
		sb.append(count).append(" ");
		
		visited = new boolean[n][n];
		count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(visited[i][j]) continue;
				f2(new Point(i, j));
				count++;
			}
		}
		sb.append(count);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	//일반인
	public static void f(Point start) {
		Queue<Point> q = new ArrayDeque<>();
		q.add(start);
		visited[start.x][start.y] = true;
		char key = input[start.x][start.y];
		while(!q.isEmpty()) {
			Point now = q.poll();
			for (int[] d : dis) {
				int nx = now.x + d[0];
				int ny = now.y + d[1];
				
				if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visited[nx][ny] && key == input[nx][ny]) {
					q.add(new Point(nx, ny));
					visited[nx][ny] = true;
				}
			}
		}
	}
	//적록색약
	public static void f2(Point start) {
		Queue<Point> q = new ArrayDeque<>();
		q.add(start);
		visited[start.x][start.y] = true;
		char key = input[start.x][start.y];
		while(!q.isEmpty()) {
			Point now = q.poll();
			for (int[] d : dis) {
				int nx = now.x + d[0];
				int ny = now.y + d[1];
				
				if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visited[nx][ny] &&
					(key == input[nx][ny] || ((key == 'R' || key == 'G') && (input[nx][ny] == 'R' || input[nx][ny] == 'G')))) {
					q.add(new Point(nx, ny));
					visited[nx][ny] = true;
				}
			}
		}
	}
}