import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		long n = Long.parseLong(br.readLine());
		sb.append(bfs(n));
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	public static int bfs(long n) {
		
		Queue<Number> pq = new ArrayDeque<>();
		Set<Long> s = new HashSet<>();
		pq.add(new Number(n, 0));
		if (n == 1) return 0;
		while (!pq.isEmpty()) {
			Number now = pq.poll();
			if ((now.num % 3 == 0 && now.num / 3 == 1) || (now.num % 2 == 0 && now.num / 2 == 1) || now.num - 1 == 1) {
				return now.k + 1;
			}
			Number next = null;
			if (now.num % 3 == 0) {
				next = new Number(now.num / 3, now.k + 1);
				if (!s.contains(next.num)) {
					s.add(next.num);
					pq.add(next);
				}
			}
			if (now.num % 2 == 0) {
				next = new Number(now.num / 2, now.k + 1);
				if (!s.contains(next.num)) {
					s.add(next.num);
					pq.add(next);
				}
			}
			next = new Number(now.num - 1, now.k + 1);
			if (!s.contains(next.num)) {
				s.add(next.num);
				pq.add(next);
			}
		}
		return -1;
	}

	static class Number  {
		long num;
		int k;

		public Number(long num, int k) {
			super();
			this.num = num;
			this.k = k;
		}
	}
}