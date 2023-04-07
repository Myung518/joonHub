import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int n;
	static int[] p;
	static int[][] input;
	static PriorityQueue<Flow> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		pq = new PriorityQueue<>();
		n = Integer.parseInt(br.readLine());
		p = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			p[i] = i;
		}
		input = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int l = Integer.parseInt(st.nextToken());
				if (l == 0) continue;
				pq.add(new Flow(i, j, l));
			}
		}
		sb.append(mst());
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static long mst() {
		long result = 0;
		while (!pq.isEmpty()) {
			Flow now = pq.poll();
			if (getParent(now.a) != getParent(now.b)) {
				union(now.a, now.b);
				result += now.l;
			}
		}
		return result;
	}
	
	public static void union(int a, int b) {
		int x = getParent(a);
		int y = getParent(b);
		if (x < y) p[y] = x;
		else p[x] = y;
	}
	
	public static int getParent(int a) {
		if (p[a] == a) return a;
		return p[a] = getParent(p[a]);
	}
	
	static class Flow implements Comparable<Flow>{
		int a, b, l;

		public Flow(int a, int b, int l) {
			super();
			this.a = a;
			this.b = b;
			this.l = l;
		}

		@Override
		public int compareTo(Flow o) {
			return this.l - o.l;
		}
	}

}