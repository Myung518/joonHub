import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[] dis = {-1, 1, 0};
		Map<Integer, Integer> m = new HashMap<>();
		Queue<Integer> q = new ArrayDeque<>();
		q.add(n);
		m.put(n, 0);
		OUT : while(!q.isEmpty()) {
			int start = q.poll();
			for (int tmp : dis) {
				int next = start + tmp;
				if (tmp == 0) {
					next = start * 2;
				}
				if (next >= 0 && next <= 100000 && !m.containsKey(next)) {
					m.put(next, m.get(start) + 1);
					q.add(next);
				}
				if (m.containsKey(k)) {
					System.out.println(m.get(k));
					break OUT;
				}
			}
		}
	}
}
