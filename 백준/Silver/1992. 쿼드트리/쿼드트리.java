import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.stream.Stream;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int[][] input;
	static int n;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		input = new int[n][n];
		for (int i = 0; i < n; i++) {
			input[i] = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		}
		
		cut(0, 0, n);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	static void cut(int r, int c, int size) {
		int sum = 0;
		for (int i = r, rEnd = r + size; i < rEnd; i++) {
			for (int j = c, cEnd = c + size; j < cEnd; j++) {
				sum += input[i][j];
			}
		}
		if (sum == size * size) {
			sb.append(1);
		} else if (sum == 0) {
			sb.append(0);
		} else {
			int half = size / 2;
			sb.append("(");
			cut(r, c, half);
			cut(r, c + half, half);
			cut(r + half, c, half);
			cut(r + half, c + half, half);
			sb.append(")");
		}
	}

}