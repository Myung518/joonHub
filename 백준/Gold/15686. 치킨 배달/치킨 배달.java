import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

class Chicken implements Comparable<Chicken>{
	Point house;
	int weight;
	
	public Chicken(Point house, int weight) {
		super();
		this.house = house;
		this.weight = weight;
	}

	@Override
	public int compareTo(Chicken o) {
		return this.weight - o.weight;
	}
}

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
	static int n, m;
	static int houseCount;
	static Set<Point> s;
	static int[][] input;
	static List<Point> chickens;
	static Queue<Point> q = new ArrayDeque<>();
	static PriorityQueue<Chicken>[] pq; 
	static int[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		input = new int[n][n];
		chickens = new ArrayList<>();

		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
				if (input[i][j] == 2) {
					chickens.add(new Point(i, j));
				}
				else if (input[i][j] == 1) {
					houseCount++;
				}
			}
		}
		pq = new PriorityQueue[chickens.size()];
		chickenToHouse();
		choose = new int[m];
		chooseChi(0, 0);
		System.out.println(result);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	static int[] choose;
	static PriorityQueue<Chicken> sumChicken;
	static int result = Integer.MAX_VALUE;
	public static void chooseChi(int path, int next) {
		if (path == m) {
			sumChicken = new PriorityQueue<>();
			for (int i = 0; i < m; i++) {
				sumChicken.addAll(pq[choose[i]]);
			}
			int total = 0;
			s = new HashSet<>();
			while(s.size() != houseCount) {
				Chicken c = sumChicken.poll();
				if (s.contains(c.house)) continue;
				s.add(c.house);
				total += c.weight;
			}
			
			result = Math.min(result, total);
			return;
		}
		
		for (int i = next; i < chickens.size(); i++) {
			choose[path] = i;
			chooseChi(path + 1, i + 1);
		}
	}
	
	public static void chickenToHouse() {
		for (int i = 0; i < chickens.size(); i++) {
			pq[i] = new PriorityQueue<>();
			visited = new int[n][n];
			q.add(chickens.get(i));
			visited[chickens.get(i).x][chickens.get(i).y] = 1;
			bfs(i, chickens.get(i));
		}
			
	}
	
	static int[][] dis = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
	public static void bfs(int num, Point start) {
		while(!q.isEmpty()) {
			Point now = q.poll();
			for (int[] tmp : dis) {
				int nx = now.x + tmp[0];
				int ny = now.y + tmp[1];
				
				if (nx >= 0 && ny >= 0 && nx < n && ny < n && visited[nx][ny] == 0) {
					visited[nx][ny] = visited[now.x][now.y] + 1;
					Point next = new Point(nx, ny);
					if (input[nx][ny] == 1) {
						int l = Math.abs(start.x - nx) + Math.abs(start.y - ny);
						pq[num].add(new Chicken(next, l));
					}
					q.add(new Point(nx, ny));
				}
			}
		}
	}
}