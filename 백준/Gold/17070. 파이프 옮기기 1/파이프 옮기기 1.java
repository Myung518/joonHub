import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
	static int n, result;
	static int[][] map;
	static int[][] dis = {{0, 1}, {1, 0}, {1, 1}};
	static int[][][] memo;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		memo = new int[n][n][3];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				Arrays.fill(memo[i][j], -1);
			}
		}
		for (int i = 0; i < n; i++) {
			map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		f(0, 1, 0);
		
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++) {
//				System.out.print(Arrays.toString(memo[i][j]) + " || ");
//			}
//			System.out.println();
//		}
		sb.append(memo[0][1][0]);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static int f(int x, int y, int k) { //k:모양 0 : 가로 1 : 세로 2: 대각선
		if (memo[x][y][k] != -1) return memo[x][y][k];
		int tmp = 0;
		for (int i = 0; i < 3; i++) {
			if (k == 0 && i == 1) continue; 
			if (k == 1 && i == 0) continue; 
			if (x == n - 1 && y == n - 1) {
				result++;
				return 1;
			}
			int nx = x + dis[i][0];
			int ny = y + dis[i][1];
			if (nx >= n || ny >= n || map[nx][ny] == 1) continue;
			if (i == 2 && (map[nx][ny - 1] == 1 || map[nx - 1][ny] == 1)) continue;
			tmp += f(nx, ny, i);
		}
		memo[x][y][k] = tmp;
		return memo[x][y][k];
		
	}

}