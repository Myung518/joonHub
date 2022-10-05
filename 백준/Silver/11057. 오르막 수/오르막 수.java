import java.io.*;
public class Main {
	static int[][] num;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		num = new int[n + 1][10];
		System.out.println(dp(n, 0));
		
	}
	public static int dp(int n, int a) {
		int result = 0;
		if (n == 0)
			return 1;
		if (num[n][a] != 0)
			return num[n][a];
		for (int i = a; i < 10; i++) {
			result += dp(n-1, i) % 10007;
		}
		if (num[n][a] == 0) {
			num[n][a] = result;
		}
		return result % 10007;
	}
}
