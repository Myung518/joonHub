import java.util.*;
import java.io.*;
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int[][] colorcost = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			colorcost[i][0] = Integer.parseInt(st.nextToken());
			colorcost[i][1] = Integer.parseInt(st.nextToken());
			colorcost[i][2] = Integer.parseInt(st.nextToken());
		}
		int[][] m = new int[n][n];
		System.out.println(f(colorcost, 0, 0, m));
	}
	public static int f(int[][] colorcost, int hn, int c, int[][] m) {
		if (m[hn][c] != 0) {
			return m[hn][c];
		}
		if (colorcost.length - 1 == hn) {
			return colorcost[hn][c];
		}
		if (c == 0) {
			m[hn][c] = Math.min(colorcost[hn][0] + f(colorcost, hn + 1, 1, m), 
					colorcost[hn][0] + f(colorcost, hn + 1, 2, m));
			if (hn == 0) c++;
		}
		if (c == 1) {
			m[hn][c] = Math.min(colorcost[hn][1] + f(colorcost, hn + 1, 0, m), 
					colorcost[hn][1] + f(colorcost, hn + 1, 2, m));
			if (hn == 0) c++;
		}
		if (c == 2) {
			m[hn][c] = Math.min(colorcost[hn][2] + f(colorcost, hn + 1, 0, m), 
					colorcost[hn][2] + f(colorcost, hn + 1, 1, m));
		}
		if (hn == 0)
			return Math.min(m[0][0], Math.min(m[0][1], m[0][2]));
		return m[hn][c];
	}

}