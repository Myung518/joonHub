import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int v, e;
	static PriorityQueue<Link> pq;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		pq = new PriorityQueue<>();
		parents = new int[v + 1];
		for (int i = 1; i < v + 1; i++) {
			parents[i] = i;
		}
		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			pq.add(new Link(n1, n2, w));
		}
		
		sb.append(mst());
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static int mst() {
		int total = 0;
		while (!pq.isEmpty()) {
			Link link = pq.poll();
			if (!isSameParents(link.n1, link.n2)) {
				union(link.n1, link.n2);
				total += link.w;
			}
		}
		
		return total;
	}
	
	public static boolean isSameParents(int x, int y) {
		int a = getParents(parents[x]);
		int b = getParents(parents[y]);
		return a == b;
	}
	
	public static void union(int x, int y) {
		int a = getParents(parents[x]);
		int b = getParents(parents[y]);
		
		if (a < b) parents[b] = parents[a];
		else parents[a] = parents[b];
	}
	
	public static int getParents(int x) {
		if (parents[x] == x) return x;
		return parents[x] = getParents(parents[x]);
	}
	
	
	
	static class Link implements Comparable<Link>{
		int n1, n2, w;

		public Link(int n1, int n2, int w) {
			super();
			this.n1 = n1;
			this.n2 = n2;
			this.w = w;
		}

		@Override
		public int compareTo(Link o) {
			return this.w - o.w;
		}
		
	}
}