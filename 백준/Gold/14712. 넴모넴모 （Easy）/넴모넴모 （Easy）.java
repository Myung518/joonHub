import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static boolean[][] map;
	static int a, b;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		
		map = new boolean[a][b];
		f(0, 0);
		sb.append(count);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	static int count = 0;
	public static void f(int x, int y) {
		if (y == b) {
			f(x + 1, 0);
			return;
		}
		if (x == a) {
			for (int i = 0; i < a - 1; i++) {
				for (int j = 0; j < b - 1; j++) {
					if (map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1]) return;
				}
			}
			count++;
			return;
		}
		
		map[x][y] = true;
		f(x, y + 1);
		
		map[x][y] = false;
		f(x, y + 1);
	}

}