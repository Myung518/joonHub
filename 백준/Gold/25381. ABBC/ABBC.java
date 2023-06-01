import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		Queue<Integer> qa = new ArrayDeque<>();
		Queue<Integer> qb = new ArrayDeque<>();
		String s = br.readLine();
		
		int result = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == 'C') {
				if (!qb.isEmpty()) {
					qb.poll();
					result++;
				}
				continue;
			} if (c == 'A') {
				qa.add(i);
				continue;
			}
			qb.add(i);
		}
		while (!qb.isEmpty()) {
			int path = qb.poll();
			if (!qa.isEmpty() && qa.peek() < path) {
				qa.poll();
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
