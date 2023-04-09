import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static int[] input;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		input = new int[n];
		int total = 0;
		for (int i = 0; i < n; i++) {
			total += Integer.parseInt(br.readLine());	
			input[i] = total;
		}
		sb.append(f(0));
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static int f(int path) {
		if (path > n) {
			return 0;
		}
		int min = 0;
		for (int i = path + m - 1; i < n; i++) {
			if (path == 0) {
				min = Math.max(min, input[i]);
				continue;
			}
			min = Math.max(min, input[i] - input[path - 1]);
		}
		return Math.max(min, f(path + 1));
	}
}
