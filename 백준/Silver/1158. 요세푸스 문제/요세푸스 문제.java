import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		Queue<Integer> q = new ArrayDeque<>();
		for (int i = 1; i <= n; i++) {
			q.add(i);
		}
		sb.append("<");
		while(q.size() != 1) {
			for (int i = 0; i < k - 1; i++) {
				q.add(q.poll());
			}
			sb.append(q.poll()).append(", ");
		}
		sb.append(q.poll()).append(">");
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}