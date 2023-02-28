import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int n, l;
	static int[][] input;
	static int[][] dis = { { 0, 1 }, { 1, 0 } }; // 오른쪽, 아래

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		input = new int[n][n];
		for (int i = 0; i < n; i++) {
			input[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		sb.append(start());
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	public static int start() {
		int result = 0;
		for (int i = 0; i < n; i++) {
			result += f(0, i, 1) ? 1 : 0;
			result += f(i, 0, 0) ? 1 : 0;
		}
		return result;
	}

	public static boolean f(int x, int y, int d) {
		int past = input[x][y];
		int count = 1; // L의 개수를 맞추기 위한 카운트
		int upDown = 0; // 내리막길 1 오르막길 2 체크
		x += dis[d][0];
		y += dis[d][1];
		for (; x < n && y < n; x += dis[d][0], y += dis[d][1]) {
			// 높이의 차가 1보다 크면
			if (Math.abs(past - input[x][y]) > 1) {
				return false;
			}
			// 그 전과 같은 높이라면
			if (past == input[x][y]) {
				count++;
				continue;
			}
			
			if (past > input[x][y]) { // 내리막 길
				if (upDown == 1 && count < l) return false;
				upDown = 1;
			}
			if (past < input[x][y]) { // 오르막 길
				if (count < l) return false;
				if (upDown == 1 && l * 2 > count) {
					return false;
				}
				upDown = 2;
			}
			past = input[x][y];
			count = 1;
		}
		if (upDown == 1 && count < l) {
			return false;
		}
		return true;
	}

}