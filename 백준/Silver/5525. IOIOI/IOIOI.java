import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		Integer.parseInt(br.readLine());
		String s = br.readLine();
		
		char[] input = s.replaceAll("IO", "a").toCharArray();
		int find = 0;
		int result = 0;
		for (char c : input) {
			if (find == n && c != 'O') {
				if (c == 'I') {
					find = 0;
				}
				result++;
				continue;
			}
			if (c == 'a') {
				find += 1;
				continue;
			}
			find = 0;
		}
		
		sb.append(result);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}