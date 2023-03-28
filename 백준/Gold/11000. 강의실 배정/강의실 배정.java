import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		
		PriorityQueue<Time> q = new PriorityQueue<>();
		PriorityQueue<Integer> c = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			q.add(new Time(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		while(!q.isEmpty()) {
			Time t = q.poll();
			if (c.size() == 0) {
				c.add(t.t);
				continue;
			}
			if (c.peek() <= t.s) {
				c.poll();
			}
			c.add(t.t);
		}
		
		sb.append(c.size());
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static class Time implements Comparable<Time>{
		int s, t;

		public Time(int s, int t) {
			super();
			this.s = s;
			this.t = t;
		}

		@Override
		public int compareTo(Time o) {
			return this.s - o.s;
		}
		
	}
}