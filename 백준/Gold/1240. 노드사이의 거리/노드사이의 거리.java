import java.io.*;
import java.util.*;
public class Main {

	static int n, m;
	static int[][] arr;
	static boolean[] check;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n + 1][n + 1];
		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[a][b] = arr[b][a] = Integer.parseInt(st.nextToken());
		}
		while (m --> 0) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			check = new boolean[n + 1];
			System.out.println(dfs(start, end));
		}
	}

	public static int dfs(int start, int end) {
		int length = 0;
		check[start] = true;
		for (int i = 0; i <= n; i++) {
			if (arr[start][i] != 0 && !check[i]) {
				length = 0;
//				System.out.println(start + " " + i + " " + end);
				if (i == end) {
					length += arr[start][end];
					return length;
				}
				if((length = dfs(i, end)) != 0) {
					length += arr[start][i];
					return length;
				}
			}
		}
		return length;
	}
}
