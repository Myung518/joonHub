import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
//		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(br.readLine());
		char[] input = br.readLine().toCharArray();
		Arrays.sort(input);
		int result = 0;
		for (int i = 1; i < n; i++) {
			char[] tmp = br.readLine().toCharArray();
			Arrays.sort(tmp);
			if (Math.abs(input.length - tmp.length) > 1) continue;
			int count = 0;
			int j = 0, k = 0; 
			while (j < input.length && k < tmp.length) {
				if (input[j] == tmp[k]) {
					count++;
					j++;
					k++;
				} else if (input[j] > tmp[k]) {
					k++;
				} else {
					j++;
				}
			}
			if (tmp.length == 1 && count == 0) continue;
			if (tmp.length - count < 2 && input.length - count < 2) {
				result++;
			}
		}
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}
