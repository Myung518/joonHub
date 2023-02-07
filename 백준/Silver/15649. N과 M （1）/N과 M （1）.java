import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static List<Integer> l;
	static int n, m;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken()); // n까지
		m = Integer.parseInt(st.nextToken()); // m개

		for (int i = 1; i <= n; i++) {
			l = new ArrayList<>();
			f(i);
		}
		bw.write(sb.toString());
		
		bw.flush();
		bw.close();
		br.close();
	}

	public static void f(int now) {
		if (l.contains(now)) {
			return;
		}
		l.add(now);
		if (l.size() == m) {
			for (int tmp : l) {
				sb.append(tmp).append(" ");
			}
			sb.append("\n");
			l.remove(Integer.valueOf(now));
			return;
		}
		for (int i = 1; i <= n; i++) {
			f(i);
		}
		l.remove(Integer.valueOf(now));

	}

}
