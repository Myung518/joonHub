import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static boolean[] check;
	static int[][] memo;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		check = new boolean[n + 1];
		memo = new int[n + 1][5000];
		for (int i = 0; i < m; i++) {
			check[Integer.parseInt(br.readLine())] = true;
		}
		int result = jump(1, 1);
		sb.append(result == Integer.MAX_VALUE ? -1 : result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static int jump(int now, int x) {
		if (now == n) {
			return 0;
		}
		if (now > n) {
			return Integer.MAX_VALUE;
		}
		if (check[now]) return Integer.MAX_VALUE;
		if (memo[now][x] != 0) return memo[now][x];
		int min = Integer.MAX_VALUE;
		if (x != 1) {
			min = Math.min(jump(now+(x - 1), x - 1), min);
		}
		min = Math.min(jump(now + x, x), min);
		if (now != 1)
			min = Math.min(jump(now + x + 1, x + 1), min);
		if (min == Integer.MAX_VALUE) {
			return memo[now][x] = Integer.MAX_VALUE;
		}
		return memo[now][x] = min + 1;
	}

}