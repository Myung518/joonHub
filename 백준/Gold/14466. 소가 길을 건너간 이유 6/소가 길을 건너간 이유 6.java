import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

class Point {
	int x, y;

	public Point(int x, int y) {
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

class Road {
	Point p1;
	Point p2;

	public Road(int r1, int r2, int r3, int r4) {
		this.p1 = new Point(r1, r2);
		this.p2 = new Point(r3, r4);
	}

	public Road(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((p1 == null) ? 0 : p1.hashCode());
		result = prime * result + ((p2 == null) ? 0 : p2.hashCode());
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
		Road other = (Road) obj;
		if (p1 == null) {
			if (other.p1 != null)
				return false;
		} else if (!p1.equals(other.p1))
			return false;
		if (p2 == null) {
			if (other.p2 != null)
				return false;
		} else if (!p2.equals(other.p2))
			return false;
		return true;
	}
}

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		Set<Road> roads = new HashSet<>();
		List<Point> cows = new ArrayList<>();
		Queue<Point> q = new ArrayDeque<>();

		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			roads.add(new Road(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			cows.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}

		int[][] dis = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
		int result = 0;
		
		for (int i = 0; i < k - 1; i++) {
			boolean[][] check = new boolean[n + 1][n + 1];
			q.add(cows.get(i));
			Point p = q.peek();
			check[p.x][p.y] = true;
			while (!q.isEmpty()) {
				Point now = q.poll();
				for (int[] tmp : dis) {
					int x = now.x + tmp[0];
					int y = now.y + tmp[1];
					if (x > 0 && y > 0 && x <= n && y <= n && !check[x][y]) {
						Point next = new Point(x, y);
						if (!roads.contains(new Road(now, next)) && !roads.contains(new Road(next, now))) {
							q.add(next);
							check[x][y] = true;
						}
					}
				}
			}
			for (int j = i; j < k; j++) {
				Point isCow = cows.get(j);
				if (!check[isCow.x][isCow.y]) {
					result++;
				}
			}
		}
		System.out.println(result);
	}

}
