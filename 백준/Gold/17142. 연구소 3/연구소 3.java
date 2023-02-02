import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
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
	static int n, m;
	static Queue<Point> q;
	static List<Point> l;
	static List<Point> virus;
	static int[][] arr;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new int[n][n];
		q = new ArrayDeque<>();
		l = new ArrayList<>();
		virus = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken()) - 2;
				// -2 빈칸 -1 벽 0 바이러스 위치
				if (arr[i][j] == 0) {
					l.add(new Point(i, j));
				}
			}
		}
		
		change(0);
		if (min == Integer.MAX_VALUE) {
			System.out.println(min = -1);
		} else {
			System.out.println(min - 1);
		}
	}

	public static void change(int i) {
		if (virus.size() == m) {
			f();
			int tmp = check();
			if (tmp != -1) {
				min = Math.min(min, tmp);
			}
			if (tmp == Integer.MIN_VALUE) {
				min = 1;
			}
			return;
		}
		for (int j = i; j < l.size(); j++) {
			virus.add(l.get(j));
			change(j + 1);
			virus.remove(l.get(j));
		}
	}
	
	static int[][] dis = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
	static int min = Integer.MAX_VALUE;
	static int[][] newarr;
	public static void f() {
		newarr = new int[n][n]; 
		visited = new boolean[n][n];
		for (Point p : virus) {
			q.add(p);
			visited[p.x][p.y] = true;
			newarr[p.x][p.y] = 1;
		}
		while(!q.isEmpty()) {
			Point p = q.poll();
			for (int[] tmp : dis) {
				int x = p.x + tmp[0];
				int y = p.y + tmp[1];
				if (x >= 0 && y >= 0 && x < n && y < n && !visited[x][y] && arr[x][y] != -1) {
					q.add(new Point(x, y));
					newarr[x][y] = newarr[p.x][p.y] + 1;
					visited[x][y] = true;
				}
			}
		}
	}
	
	public static int check() {
		int max = Integer.MIN_VALUE;
		OUT : for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (arr[i][j] == -2) {
					if (newarr[i][j] == 0) {
						max = -1;
						break OUT;
					}
					max = Math.max(max, newarr[i][j]);
				}
			}
		}
		return max;
	}

}
