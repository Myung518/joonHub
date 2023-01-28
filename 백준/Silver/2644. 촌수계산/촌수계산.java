import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static boolean[][] arr;
	static Queue<Integer> q = new ArrayDeque<>();
	static int[] check;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int r1 = Integer.parseInt(st.nextToken());
		int r2 = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(br.readLine());
		arr = new boolean[n + 1][n + 1];
		check = new int[n + 1];
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			arr[x][y] = arr[y][x] = true;
		}
		System.out.println(f(r1, r2));
	}
	
	public static int f(int r1, int r2) {
		q.add(r1);
		check[r1] = 1;
		while (!q.isEmpty()) {
			int p = q.poll();
			for (int i = 0; i < n + 1; i++) {
				if (arr[p][i] && check[i] == 0) {
					q.add(i);
					check[i] = check[p] + 1;
				}
				if (check[r2] > 0) {
					return check[r2] - 1;
				}
			}
		}
		return -1;
	}

}
