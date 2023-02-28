import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
	int x, y;

	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
}

public class Main {
	static int w, h;
	static int min;
	static char[][] room;
	static List<Point> dirtyList;
	static int[][] len;
	static Point start;
	static int[][] visited;
	static boolean[] check;
	static int[][] dis = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		
		while (!(w == 0 && h == 0)) {
			room = new char[h][w];
			dirtyList = new ArrayList<>();
			
			for (int i = 0; i < h; i++) {
				String s = br.readLine();
				for (int j = 0; j < w; j++) {
					room[i][j] = s.charAt(j);
					if (room[i][j] == '*') {
						dirtyList.add(new Point(i, j));
					}
					if (room[i][j] == 'o') {
						start = new Point(i, j);
					}
				}
			}
			cleaning();
			sb.append(min == Integer.MAX_VALUE ? -1 : min).append("\n");
			
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
		}
		

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void cleaning() {
		len = new int[dirtyList.size() + 1][dirtyList.size() + 1];
		visited = new int[h][w];
		
		bfs(start, 0);
		for (int i = 0; i < dirtyList.size(); i++) {
			visited = new int[h][w];
			bfs(dirtyList.get(i), i + 1);
		}
		check = new boolean[dirtyList.size() + 1];
		min = Integer.MAX_VALUE;
		
		find(0, 0, 0);
	}
	
	public static void find(int path, int total, int count) {
		if (count == dirtyList.size()) {
			min = Math.min(min, total);
		}
		for (int i = 1; i < dirtyList.size() + 1; i++) {
			if (check[i]) continue;
			if (len[path][i] == 0) continue;
			check[i] = true;
			find(i, total + len[path][i], count + 1);
			check[i] = false;
		}
	}
	
	public static void bfs(Point s, int num) {
		Queue<Point> q = new ArrayDeque<>();
		q.add(s);
		visited[s.x][s.y] = 1;
		
		while (!q.isEmpty()) {
			Point now = q.poll();
			
			for (int[] d : dis) {
				int x = now.x + d[0];
				int y = now.y + d[1];
				
				if (x >= 0 && y >= 0 && x < h && y < w && visited[x][y] == 0 && room[x][y] != 'x') {
					Point next = new Point(x, y);
					q.add(next);
					visited[x][y] = visited[now.x][now.y] + 1;
					if (dirtyList.contains(next)) {
						len[num][dirtyList.indexOf(next) + 1] = visited[x][y] - 1;
					}
				}
			}
		}
	}

}