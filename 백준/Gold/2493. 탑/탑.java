import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

class Top {
	int no, height;

	public Top(int no, int height) {
		super();
		this.no = no;
		this.height = height;
	}
}

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		
		Stack<Top> stack = new Stack<>();
		int[] result = new int[n + 1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int height = Integer.parseInt(st.nextToken());
			if (stack.isEmpty()) {
				stack.push(new Top(i + 1, height));
				continue;
			}
			while (!stack.isEmpty() && stack.peek().height < height) {
				Top t = stack.pop();
				int no = 0;
				if (!stack.isEmpty()) no = stack.peek().no;
				result[t.no] = no;
			}
			stack.push(new Top(i + 1, height));
		}
		
		while(!stack.isEmpty()) {
			Top t = stack.pop();
			int no = 0;
			if (!stack.isEmpty()) no = stack.peek().no;
			result[t.no] = no;
		}
		
		for (int i = 1; i < n + 1; i++) {
			sb.append(result[i]).append(" ");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}