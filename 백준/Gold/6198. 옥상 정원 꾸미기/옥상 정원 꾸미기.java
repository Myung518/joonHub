
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {
	static Stack<Long> stack;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
//		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(br.readLine());
		stack = new Stack<>();
		long total = 0;
		while (n-- > 0) {
			long h = Integer.parseInt(br.readLine());
			while (stack.size() != 0 && stack.peek() <= h) {
				stack.pop();
			}
			stack.add(h);
			total += stack.size() - 1;
		}
		sb.append(total);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}
