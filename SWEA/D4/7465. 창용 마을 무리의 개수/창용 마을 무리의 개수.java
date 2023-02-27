import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	static int n, m;
	static int[] b;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			b = new int[n + 1];
			for (int i = 1; i <= n; i++) {
				b[i] = i;
			}
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int n1 = Integer.parseInt(st.nextToken());
				int n2 = Integer.parseInt(st.nextToken());
				union(n1, n2);
			}
			Set<Integer> s = new HashSet<>();
			for (int i = 1; i <= n; i++) {
				find(i);
				s.add(b[i]);
			}
			sb.append(String.format("#%d ", t));
			sb.append(s.size()).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	public static boolean getfind(int x, int y) {
		return find(x) == find(y) ? true : false;
	}
	
	public static void union(int x, int y) {
		if (find(y) == find(x)) return;
		else b[find(y)] = find(x);
	}
	
	public static int find(int x) {
		return x == b[x] ? x : (b[x] = find(b[x]));
	}
}