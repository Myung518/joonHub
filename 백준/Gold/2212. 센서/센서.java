import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n, k;
	static int[] input;
	static int[] gap;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		input = new int[n];
		gap = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(input);
		
		for (int i = 0; i < n - 1; i++) {
			gap[i] = input[i + 1] - input[i];
		}
		Arrays.sort(gap);
		
		int result = 0;
		for (int i = 0; i < n - k + 1; i++) {
			result += gap[i];
		}
		
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}