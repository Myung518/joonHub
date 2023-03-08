import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
.: 빈 칸
*: 벽
C: 레이저로 연결해야 하는 칸
*/
class Point implements Comparable<Point>{
	int x, y, d, l;

	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		this.d = -1;
	}

	public Point(int x, int y, int d, int l) {
		super();
		this.x = x;
		this.y = y;
		this.d = d;
		this.l = l;
	}
	
	@Override
	public int compareTo(Point o) {
		return this.l - o.l;
	}
	
}
public class Main {
	static int w, h;
	static char[][] input;
	static int[][] dis = {{-1, 0}, {0, 1}, {0, -1}, {1, 0}};
	static List<Point> c;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		input = new char[h][w];
		c = new ArrayList<>();
		for (int i = 0; i < h; i++) {
			input[i] = br.readLine().toCharArray();
			for (int j = 0; j < w; j++) {
				if (input[i][j] == 'C' || input[i][j] == 'c') {
					c.add(new Point(i, j));
				}
			}
		}
		sb.append(f() - 1);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static int f() {
//		Queue<Point> q = new ArrayDeque<>();
		PriorityQueue<Point> q = new PriorityQueue<>();
		int[][][] visited = new int[h][w][4];
		Point start = c.get(0);
		Point end = c.get(1);
		q.add(start);
		while(!q.isEmpty()) {
			Point now = q.poll();
			int x = now.x;
			int y = now.y;
			int d = 0;
			if (now.d == -1) { //시작점 일때 4방향 탐색
				for (; d < dis.length; d++) {
					x = now.x + dis[d][0];
					y = now.y + dis[d][1];
					if (x >= 0 && y >= 0 && x < h && y < w && input[x][y] != '*') {
						q.add(new Point(x, y, d, 1));
						visited[now.x][now.y][d] = 1;
						visited[x][y][d] = 1;
					}
				}
			} else {
				for (int i = 0; i <= 2; i++) {
					d = now.d ^ i;
					x = now.x + dis[d][0];
					y = now.y + dis[d][1];
					if (x >= 0 && y >= 0 && x < h && y < w && input[x][y] != '*') {
						if (i == 0 && (visited[x][y][d] == 0 || visited[x][y][d] > visited[now.x][now.y][now.d])) {
							visited[x][y][d] = visited[now.x][now.y][now.d];
							q.add(new Point(x, y, d, visited[x][y][d]));
						}
						else if (i != 0 && (visited[x][y][d] == 0 || visited[x][y][d] > visited[now.x][now.y][now.d] + 1)) {
							visited[x][y][d] = visited[now.x][now.y][now.d] + 1;
							q.add(new Point(x, y, d, visited[x][y][d]));
						}
					}
				}
			}
		}
		
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < 4; i++) {
			if (visited[end.x][end.y][i] == 0) continue;
			result = Math.min(result, visited[end.x][end.y][i]);
		}
		return result;
	}

}