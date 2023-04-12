import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int n; //추의 개수
	static int[] input;
	static Set<Integer> memo;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		input = new int[n + 1];
		memo = new HashSet<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		memo.add(0);
		f(1);
		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		while(m-- > 0) {
			int w = Integer.parseInt(st.nextToken());
			sb.append(memo.contains(w) ? "Y " : "N ");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void f(int path) {
		if (path == n + 1) return;
		Set<Integer> tmp = new HashSet<>();
		for (int data : memo) {
			tmp.add(data + input[path]);
			tmp.add(data - input[path]);
		}
		memo.addAll(tmp);
		f(path + 1);
	}

}