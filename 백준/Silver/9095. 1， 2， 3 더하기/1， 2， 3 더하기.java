import java.io.*;
public class Main {
	public static int[] dp = new int[12];
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		while (t --> 0) {
			int n = Integer.parseInt(br.readLine());
			System.out.println(f(n));
		}
	}
	public static int f(int n) {
		if (n == 1 || n == 2)
			return n;
		if (n == 3)
			return 4;
		if (dp[n] == 0) dp[n] = f(n - 3) + f(n - 2) + f(n - 1);
		return dp[n];
	}

}