import java.io.*;
public class Main {
	public static long[] dp = new long[1001];
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		System.out.println(f(n));
	}
	public static long f(int n) {
		if (n == 1 || n == 2)
			return n;
		if (dp[n] == 0)
			dp[n] = (f(n-1) + f(n-2))%10007;
		return dp[n];
	}

}
