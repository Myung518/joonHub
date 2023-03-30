import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.stream.Stream;

public class Main {
	static final int SIZE = 9;
	static int[][] map;
	static int[][] memo;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		map = new int[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			map[i] = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		}
		memo();
		f(0, 0);
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static boolean f(int x, int y) {
		if (x == SIZE - 1 && y == SIZE) {
			return true;
		}
		if (y == SIZE) {
			if (f(x + 1, 0)) return true;
			return false;
		}
		if (map[x][y] != 0) {
			if (f(x, y + 1)) return true;
			return false;
		}
		for (int i = 1; i <= SIZE; i++) {
			if (validate(x, y, i)) continue;
			memo[x / 3 * 3 + y / 3][0] |= 1 << i;
			memo[x][1] |= 1 << i;
			memo[y][2] |= 1 << i;
			map[x][y] = i;
			
			if (f(x, y + 1)) return true;
			map[x][y] = 0;
			memo[x / 3 * 3 + y / 3][0] &= ~(1 << i);
			memo[x][1] &= ~(1 << i);
			memo[y][2] &= ~(1 << i);
		}
		return false;
	}
	
	public static boolean validate(int x, int y, int i) {
		if ((memo[x / 3 * 3 + y / 3][0] & 1 << i) > 0) return true; //사각 체크
		if ((memo[x][1] & 1 << i) > 0) return true;
		if ((memo[y][2] & 1 << i) > 0) return true;
		return false;
	}
	
	public static void memo() {
		memo = new int[SIZE][3];
		//사각형
		int k = 0;
		for (int startx = 0; startx < 3; startx++) {
			for (int starty = 0; starty < 3; starty++) {
				for (int i = startx * 3; i < startx * 3 + 3; i++) {
					for (int j = starty * 3; j < starty * 3 + 3; j++) {
						if (map[i][j] == 0) continue;
						memo[k][0] |= 1 << map[i][j];
					}
				}
				k++;
			}
		}
		
		//가로 세로
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (map[i][j] == 0) continue;
				memo[i][1] |= 1 << map[i][j];
				memo[j][2] |= 1 << map[i][j];
			}
		}
	}
}