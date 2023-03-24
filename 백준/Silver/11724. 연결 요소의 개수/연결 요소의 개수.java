import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static int[] p;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		p = new int[n + 1];
		
		for (int i = 1; i < n + 1; i++) {
			p[i] = i;
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Set<Integer> s = new HashSet<>();
		for (int i = 1; i <= n; i++) {
			find(i);
			s.add(p[i]);
		}
		
//		System.out.println(Arrays.toString(p));
		sb.append(s.size());
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void union(int x, int y) {
		int a = find(x);
		int b = find(y);
		if(a <= b) p[b] = a;
		else p[a] = b;
	}
	
	public static int find(int x) {
		if (p[x] == x) return x;
		return p[x] = find(p[x]);
	}

}