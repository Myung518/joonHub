import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr;
	static int[][] m;
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		m = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j <= i; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(dp(0, 0));
	}
	
	public static int dp(int count, int c) {
		if (count >= n) {
			return 0;
		}
		if (m[count][c] != 0)
			return m[count][c];
		int max = Integer.MIN_VALUE;
		for (int i = c; i < n && i <= c + 1; i++) {
			max = Math.max(arr[count][i] + dp(count + 1, i), max);
		}
		return m[count][c] = max;
	}

}
