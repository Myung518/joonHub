import java.util.*;
import java.io.*;

class Point1 implements Comparable<Point1> {
	int v, w;
	public Point1(int v, int w) {
		this.v = v;
		this.w = w;
	}
	
	public int compareTo(Point1 p) {
		// TODO Auto-generated method stub
		return Integer.compare(this.w, p.w);
	}
}
public class Main {
	static int N, M;
	static int sCity, eCity;
	static int[] check;
	static boolean[] visited;
	static ArrayList<Point1>[] list;
	static PriorityQueue<Point1> q = new PriorityQueue<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		visited = new boolean[N + 1];
		check = new int[N + 1];
		Arrays.fill(check, Integer.MAX_VALUE);
		list = new ArrayList[N + 1];
		for(int i = 1; i < N + 1; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			list[u].add(new Point1(v, w));
		}
		st = new StringTokenizer(br.readLine());
		sCity= Integer.parseInt(st.nextToken());
		eCity = Integer.parseInt(st.nextToken());
		f();
		System.out.println(check[eCity]);
	}
	public static void f() {
		q.add(new Point1(sCity, 0));
		check[sCity] = 0;
		while (!q.isEmpty()) {
			Point1 p = q.poll();
			if(visited[p.v]) continue;
			visited[p.v] = true;
			for (Point1 next : list[p.v]) {
				if (check[next.v] > check[p.v] + next.w) {
					check[next.v] = check[p.v] + next.w;
					q.add(new Point1(next.v, check[next.v]));
				}
			}
		}
	}

}
