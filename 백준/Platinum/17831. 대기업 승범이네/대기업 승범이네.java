import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static List<Integer>[] link;
	static int[] p;
	static int[][] memo;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		p = new int[n + 1];
		memo = new int[n + 1][2];
		for (int i = 0; i <= n; i++) {
			Arrays.fill(memo[i], -1);
		}
		link = new ArrayList[n + 1];
		for (int i = 0; i <= n; i++) {
			link[i] = new ArrayList<>();
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 2; i <= n; i++) {
			link[Integer.parseInt(st.nextToken())].add(i);
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			p[i] = Integer.parseInt(st.nextToken());
		}
		int result = find(1, -1, 0);
		sb.append(result == -1 ? 0 : result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static int find(int path, int past, int check) {
		if (memo[path][check] != -1) return memo[path][check];
		int result = 0;
		int sum = 0;
		for (int node : link[path]) {
			if (node == past) continue;
			sum += find(node, path, 0);
		}
		result = Math.max(sum, result);
		if (check == 0) {
			for (int node : link[path]) {
				if (node == past) continue;
				int sum2 = sum;
				sum2 -= find(node, path, 0);
				sum2 += find(node, path, 1);
				sum2 += p[path] * p[node];
				result = Math.max(sum2, result);
			}
		}
		return memo[path][check] = result;
	}
}