import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int R, C;
	static char[][] arr;
	static boolean[] c;
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[R][];
		c = new boolean[26];
		
		for (int i = 0; i < R; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		c[arr[0][0] - 'A'] = true;
		f(1, 0, 0);
		System.out.println(max);
	}
	static int dis[][] = { {-1, 0}, {0, -1}, {1, 0}, {0, 1} };
	public static void f(int count, int x, int y) {
		max = Math.max(max, count);
		for (int[] tmp : dis) {
			int tx = x + tmp[0];
			int ty = y + tmp[1];
			if (tx >= 0 && ty >= 0 && tx < R && ty < C && !c[arr[tx][ty] - 'A']) {
				c[arr[tx][ty] - 'A'] = true;
				f(count + 1, tx, ty);
				c[arr[tx][ty] - 'A'] = false;
			}
		}
	}

}
