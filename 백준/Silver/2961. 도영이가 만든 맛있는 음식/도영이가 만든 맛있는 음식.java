import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[][] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n][2];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		f(0, 1, 0);
		System.out.println(min);
	}
	static int t1 = 1, t2 = 0;
	static int min = Integer.MAX_VALUE;
	public static void f(int now, int t1, int t2) {
		if (now == n) {
			if (t2 != 0) min = Math.min(min, Math.abs(t1 - t2));
			return;
		}
		
		f(now + 1, t1 * arr[now][0], t2 + arr[now][1]);
		f(now + 1, t1, t2);
	}

}
