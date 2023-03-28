import java.util.*;
import java.io.*;

public class Main {
	static int[][] m;
	static int[][] colorcost;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		colorcost = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			colorcost[i][0] = Integer.parseInt(st.nextToken());
			colorcost[i][1] = Integer.parseInt(st.nextToken());
			colorcost[i][2] = Integer.parseInt(st.nextToken());
		}
		m = new int[n][n];
		System.out.println(f(0, 0));
	}

	public static int f(int hn, int c) {
		if (m[hn][c] != 0) {
			return m[hn][c];
		}
		if (colorcost.length - 1 == hn) {
			return colorcost[hn][c];
		}
		if (c == 0) {
			m[hn][c] = Math.min(colorcost[hn][0] + f(hn + 1, 1),
					colorcost[hn][0] + f(hn + 1, 2));
			if (hn == 0)
				c++;
		}
		if (c == 1) {
			m[hn][c] = Math.min(colorcost[hn][1] + f(hn + 1, 0),
					colorcost[hn][1] + f(hn + 1, 2));
			if (hn == 0)
				c++;
		}
		if (c == 2) {
			m[hn][c] = Math.min(colorcost[hn][2] + f(hn + 1, 0),
					colorcost[hn][2] + f(hn + 1, 1));
		}
		if (hn == 0)
			return Math.min(m[0][0], Math.min(m[0][1], m[0][2]));
		return m[hn][c];
	}

}