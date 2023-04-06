import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.stream.Stream;

public class Main {
	static int n;
	static int[][] link;
	static int[][] memo;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(br.readLine());
		link = new int[n][n];
		memo = new int[n][(int) Math.pow(2, n)];
		
		for (int i = 0; i < n; i++) {
			link[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		sb.append(f(0, 1));
//		System.out.println(Arrays.toString(memo));
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static int f(int city, int b) {
		if (memo[city][b] != 0) return memo[city][b];
		if (b == Math.pow(2, n) - 1) {
			return link[city][0] == 0 ? Integer.MAX_VALUE : link[city][0];
		}
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			if (city == i) continue;
			if (link[city][i] == 0) continue;
			if ((b & (1 << i)) > 0) continue;
			int tmp = f(i, b | (1 << i));
			if (tmp == Integer.MAX_VALUE) continue;
			min = Math.min(tmp + link[city][i], min);
		}
//		System.out.println(city + " " + min);
		return memo[city][b] = min;
	}

}