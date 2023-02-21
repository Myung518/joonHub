import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int r, c;
	static int[][] input;
	static boolean[][] visit;
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		input = new int[r][c];
		visit = new boolean[r][c];
		
		for (int i = 0; i < r; i++) {
			String s = br.readLine();
			for (int j = 0; j < c; j++) {
				input[i][j] = s.charAt(j) == '.' ? 1 : 0; // 0 : 벽
			}
		}
		int result = 0;
		for (int i = 0; i < r; i++) {
			result += f(i, 0, i + 2) ? 1 : 0;
		}
		
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static int[][] dis = {{-1, 1}, {0, 1}, {1, 1}};
	static int max = Integer.MIN_VALUE;
	
	public static boolean f(int x, int y, int num) {
		if (visit[x][y]) return false;
		if (y == c - 1) {
			return true;
		}
		for (int[] tmp : dis) {
			if (x + tmp[0] >= 0 && y + tmp[1] >= 0 && x + tmp[0] < r && y + tmp[1] < c && input[x + tmp[0]][y + tmp[1]] == 1) {
				input[x + tmp[0]][y + tmp[1]] = num;
				if (f(x + tmp[0], y + tmp[1], num))
					return true;
				visit[x + tmp[0]][y + tmp[1]] = true;
				input[x + tmp[0]][y + tmp[1]] = 1;
			}
		}
		return false;
	}
}