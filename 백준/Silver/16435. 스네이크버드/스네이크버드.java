import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		
		int[] f = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Arrays.sort(f);
		for (int i = 0; i < n; i++) {
			if (l < f[i]) break;
			l++;
		}
		sb.append(l);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}