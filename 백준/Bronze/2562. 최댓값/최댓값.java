import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Main {
	static class Num implements Comparable<Num>{
		int n, p;

		public Num(int n, int p) {
			super();
			this.n = n;
			this.p = p;
		}

		@Override
		public int compareTo(Num o) {
			return o.n - this.n;
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		PriorityQueue<Num> pq = new PriorityQueue<>();
		
		for (int i = 1; i <= 9; i++) {
			pq.add(new Num(Integer.parseInt(br.readLine()), i));
		}
		sb.append(String.format("%d\n%d", pq.peek().n, pq.peek().p));

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}

}