import java.io.*;
public class Main {

	static int[] b;
	static int[][] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		b = new int[n + 1];
		dp = new int[n + 1][2];
		for (int i = 1; i <= n; i++) {
			b[i] = Integer.parseInt(br.readLine());
		}
		System.out.println(f(0, 0));
	}
	public static int f(int a, int c) {
		if (a >= b.length)
			return 0;
		if (dp[a][c] != 0)
			return dp[a][c];
		if (a == b.length - 1) {
			return b[a];
		}
		if (a == 0)
			return Math.max(f(a + 1, 0), f(a + 2, 0));
		if (a == b.length - 3)
			return b[a] + b[a + 2];
		if (a == b.length - 2) {
			if (c == 1) {
				return 0;
			}
			return b[a] + b[a + 1];
		}
		if (c == 1) {
			return b[a] + f(a + 2, 0);
		}
		int max = b[a] + Math.max(f(a + 1, 1), f(a + 2, 0));
		dp[a][c] = max;
		return max;
	}

}
