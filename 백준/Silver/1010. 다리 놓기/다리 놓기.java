import java.io.*;
import java.util.*;
public class Main {
	public static int[][] dp = new int[31][31];
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());
		dp[2][3] = 3;
		
		while (t --> 0) {
			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			System.out.println(f(n, m));
		}
	}
	public static int f(int n, int m) {
		if (n == m) {
			return 1;
		}
		if (n == 1) {
			return m;
		}
		if (dp[n][m] == 0)
			dp[n][m] = f(n-1, m-1) + f(n, m-1);
		return dp[n][m];
	}

}