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
	static int N, M;
	static int[][] arr;
	static int[][] change;
	static boolean[][] visited;
	static Queue<Point> q = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int result = 0;
		int count = 1;
		do {
			oneYear();
			result++;
			count = count();
			if (count == 0) {
				result = 0;
				break;
			}
		} while(count < 2);
		System.out.println(result);
	}

	public static int count() {
		int count = 0;
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 0) {
					continue;
				}
				if (!visited[i][j]) {
					bfs(new Point(i, j));
					count++;
				}
			}
		}
		return count;
	}

	static int[][] dis = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

	public static void bfs(Point start) {
		q.add(start);
		while (!q.isEmpty()) {
			start = q.poll();
			visited[start.x][start.y] = true;

			for (int[] tmp : dis) {
				int x = start.x + tmp[0];
				int y = start.y + tmp[1];
				if (x >= 0 && y >= 0 && x < N && y < M && arr[x][y] > 0 && !visited[x][y]) {
					q.add(new Point(x, y));
					visited[x][y] = true;
				}
			}
		}
	}

	public static void oneYear() {
		change = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 0) {
					continue;
				}
				int value = arr[i][j];
				for (int[] tmp : dis) {
					int x = i + tmp[0];
					int y = j + tmp[1];
					if (x >= 0 && y >= 0 && x < N && y < M) {
						if (arr[x][y] == 0) {
							value--;
						}
					}
					if (value == 0) {
						break;
					}
				}
				change[i][j] = value;
			}
		}
		for (int i = 0; i < change.length; i++) {
			System.arraycopy(change[i], 0, arr[i], 0, change[i].length);
		}
	}

}
