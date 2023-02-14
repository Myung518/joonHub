import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		
		int[][] input = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			input[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		int count = Math.min(n, m) / 2;
		
		int[][] dis = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
		
		int R = r % ((n + m - 2) * 2);
		for (int i = 0; i < r; i++) { 
			for (int j = 0; j < count; j++) {
				int x = j;
				int y = j;
				int value = input[x][y];
				
				int idx = 0;
				while (idx < 4) {
					int nx = x + dis[idx][0];
					int ny = y + dis[idx][1];
					
					if (nx >= j && ny >= j && nx < n - j && ny < m - j) {
						input[x][y] = input[nx][ny];
						x = nx;
						y = ny;
					}
					else idx++;
				}
				input[j + 1][j] = value;
				
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sb.append(input[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}