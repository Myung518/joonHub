import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int H, W, O, F, sx, sy, ex, ey;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			O = Integer.parseInt(st.nextToken()); //장애물 개수
			F = Integer.parseInt(st.nextToken()); //초기힘
			sx = Integer.parseInt(st.nextToken());
			sy = Integer.parseInt(st.nextToken());
			ex = Integer.parseInt(st.nextToken());
			ey = Integer.parseInt(st.nextToken());
			map = new int[101][101];
			for (int i = 0; i < O; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int h = Integer.parseInt(st.nextToken());
				map[x][y] = h;
			}
//			for (int i = 0; i < H + 1; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
			sb.append(bfs()).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static String bfs() {
		Queue<Point> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[101][101];
		int[][] dis = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
		q.add(new Point(sx, sy, F));
		visited[sx][sy] = true;
		
		while (!q.isEmpty()) {
			Point now = q.poll();
			if (now.f == 0) break;
			for(int[] d : dis) {
				int x = now.x + d[0];
				int y = now.y + d[1];
				if (x <= 0 || y <= 0 || x > H || y > W || visited[x][y]) continue;
				if(map[x][y] != 0 && now.f < map[x][y] - map[now.x][now.y]) continue;
				visited[x][y] = true;
				q.add(new Point(x, y, now.f - 1));
				if (x == ex && y == ey) {
					return "잘했어!!";
				}
			}
		}
		return "인성 문제있어??";
	}
	
	static class Point {
		int x, y, f;

		public Point(int x, int y, int f) {
			super();
			this.x = x;
			this.y = y;
			this.f = f;
		}

	}
}