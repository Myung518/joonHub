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
	static int[][] memo;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		memo = new int[n + 1][2];
		link = new ArrayList[n + 1];
		for (int i = 0; i <= n; i++) {
			link[i] = new ArrayList<>();
		}
		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			link[u].add(v);
			link[v].add(u);
		}

		sb.append(find(0, 1, 1));
//		for (int i = 1; i <= n; i++) {
//			System.out.println(Arrays.toString(memo[i]));
//		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	public static int find(int past, int path, int isPastPolice) {
		boolean check = false;
//		System.out.println(path + " " + isPastPolice);
		if (memo[path][isPastPolice] != 0)
			return memo[path][isPastPolice];
		if (isPastPolice == 0) {
			for (int next : link[path]) {
				if (next == past)
					continue;
				check = true;
				memo[path][isPastPolice] += find(path, next, 1);
			}
			memo[path][isPastPolice] += 1;
		} else {
			int tmp1 = 0, tmp2 = 1;
			for (int next : link[path]) {
				if (next == past)
					continue;
				check = true;
				tmp1 += find(path, next, 0);
				tmp2 += find(path, next, 1);
			}
			memo[path][isPastPolice] = Math.min(tmp1, tmp2);
		}
		if (!check)
			return isPastPolice == 0 ? 1 : 0;
		return memo[path][isPastPolice];
	}

}
