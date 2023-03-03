import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Solution {
	static int N, M, C;
	static int[][] map;
	static int[][] m;
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			m = new int[N][N - M + 1];
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			f();
			max = Integer.MIN_VALUE;
			f2(0, 0, 0, 0);
			sb.append(String.format("#%d ", t));
			sb.append(max).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void f() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N - M + 1; j++) {
				tmp = new boolean[M];
				tmp2 = Integer.MIN_VALUE;
				f3(i, j, 0, 0);
				m[i][j] = tmp2;
			}
		}
	}
	static boolean[] tmp;
	static int tmp2;
	public static void f3(int i, int j, int path, int total) {
		if (total > C) {
			return;
		} 
		if (path > M) {
			return;
		}
		if (path >= 1) {
			int r = 0;
			for (int k = 0; k < M; k++) {
				if (tmp[k]) {
					r += map[i][j + k] * map[i][j + k];
				}
			}
			tmp2 = Math.max(tmp2, r);
		}
		for (int k = 0; k < M; k++) {
			if (tmp[k]) continue;
			tmp[k] = true;
			f3(i, j, path + 1, total + map[i][j + k]);
			tmp[k] = false;
		}
	}
	
	public static void f2(int path, int x, int y, int total) {
		if(path == 2) {
			max = Math.max(max, total);
			return;
		}
		for (int i = 0; i < N; i++) {
			OUT : for (int j = 0; j < m[i].length; j++) {
				if (path != 0) {
					for (int k = 0; k < M; k++) {
						if (i == x && (y == j + k || y + k == j)) continue OUT;
					}
				}
				f2(path + 1, i, j, total + m[i][j]);
			}
		}
	}

}