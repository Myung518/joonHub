import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] input = new int[n][m];
		boolean[][] visited = new boolean[n][m];
		Queue<Point> q = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
				if (input[i][j] == 2) {
					q.add(new Point(i, j));
					visited[i][j] = true;
					input[i][j] = 0;
				}
			}
		}
		
		int[][] dis = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
		while(!q.isEmpty()) {
			Point now = q.poll();
			for (int[] tmp : dis) {
				int x = now.x + tmp[0];
				int y = now.y + tmp[1];
				
				if (x >= 0 && y >= 0 && x < n && y < m && !visited[x][y] && input[x][y] == 1) {
					q.add(new Point(x, y));
					visited[x][y] = true;
					input[x][y] = input[now.x][now.y] + 1;
				}
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!visited[i][j] && input[i][j] == 1) {
					sb.append(-1).append(" ");
					continue;
				}
				sb.append(input[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}
