import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int n, m;
	static int[][] input;
	static boolean[][] check;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		input = new int[n][m];
		check = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			input[i] = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		}
		
		sb.append(f(0, 0));
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static int f(int x, int y) {
		if (x >= n) {
			if (y == m - 1) {
				return 0;
			}
			return f(0, y + 1);
		}
		if (check[x][y]) {
			return f(x + 1, y);
		}
		int max = -1;
		int result = 0;
		int i = 0;
		for (; x + i < n; i++) {
			if (check[x + i][y]) break;
			result *= 10;
			result += input[x + i][y];
			check[x + i][y] = true;
			max = Math.max(max, f(x + 1, y) + result);
		}
		i--;
		for (; i > 0; i--) {
			check[x + i][y] = false;
		}
		result = input[x][y];
		i = 1;
		for (; y + i < m; i++) {
			if (check[x][y + i]) break;
			result *= 10;
			result += input[x][y + i];
			check[x][y + i] = true;
			max = Math.max(max, f(x + 1, y) + result);
		}
		i--;
		for (; i >= 0; i--) {
			check[x][y + i] = false;
		}
		return max;
	}
}
