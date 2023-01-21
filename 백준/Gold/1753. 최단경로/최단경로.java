import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
class Point implements Comparable<Point>{
	int v, w;
	public Point(int v, int w) {
		this.v = v;
		this.w = w;
	}
	@Override
	public int compareTo(Point o) {
		// TODO Auto-generated method stub
		return Integer.compare(this.w, o.w);
	}
}

public class Main {
	static int startN;
	static int V, E;
	static ArrayList<Point>[] list;
	static int[] check;
	static boolean[] visited;
	static PriorityQueue<Point> q = new PriorityQueue<>();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		startN = Integer.parseInt(br.readLine());
		check = new int[V + 1];
		Arrays.fill(check, Integer.MAX_VALUE);
		visited = new boolean[V + 1];
		list = new ArrayList[V + 1];
		for (int i = 0; i < V + 1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			list[u].add(new Point(v, w));
		}
		f();
		for (int i = 1; i < check.length; i++) {
			if (check[i] == Integer.MAX_VALUE) {
				System.out.println("INF");
			}
			else {
				System.out.println(check[i]);
			}
		}
	}
	public static void f() {
		q.add(new Point(startN, 0));
		check[startN] = 0;
		while (!q.isEmpty()) {
			Point p = q.poll();
			if (visited[p.v]) {
				continue;
			}
			visited[p.v] = true;
			for (Point next : list[p.v]) {
				if (check[next.v] > check[p.v] + next.w) {
					check[next.v] = check[p.v] + next.w;
					q.add(new Point(next.v, check[next.v]));
				}
			}
		}
	}

}
