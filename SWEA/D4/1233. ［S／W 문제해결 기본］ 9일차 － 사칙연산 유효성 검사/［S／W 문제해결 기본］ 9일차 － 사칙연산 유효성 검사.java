import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {
	static final int T = 10;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			int result = 1;
			if (!validation()) result = 0;
			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	public static boolean validation() throws IOException {
		int n = Integer.parseInt(br.readLine());
		String[] s = new String[n + 1];
		for (int i = 1; i <= n; i++) {
			s[i] = br.readLine();
		}
		if (n % 2 == 0) {
			return false;
		}
		for (int i = 1; i <= n; i++) {
			String[] input = s[i].split(" ");
			if (input.length == 4 && input[1].matches("\\d*")) {
				return false;
			}
			if (input.length == 2 && !input[1].matches("\\d*")) {
				return false;
			}
		}
		return true;
	}

}