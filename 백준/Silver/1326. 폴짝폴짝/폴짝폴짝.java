import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

class Jump implements Comparable<Jump>{
	int p, c;

	public Jump(int p, int c) {
		super();
		this.p = p;
		this.c = c;
	}

	@Override
	public int compareTo(Jump o) {
		return this.c == o.c ? Math.abs(this.p - Main.end) -  Math.abs(o.p - Main.end) : this.c - o.c;
	}
	
}

public class Main {
	static int n;
	static int[] input;
	static int start, end;
	static int[] check;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken()) - 1;
		end = Integer.parseInt(st.nextToken()) - 1;
		check = new int[n + 1];
		
		sb.append(f());
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static int f() {
		PriorityQueue<Jump> q = new PriorityQueue<>();
		q.add(new Jump(start, 0));
		while(!q.isEmpty()) {
			Jump j = q.poll();
			for (int i = input[j.p]; j.p + i <= n - 1; i += input[j.p]) {
				if (j.p + i == end) return j.c + 1;
				q.add(new Jump(j.p + i, j.c + 1));
			}
			for (int i = input[j.p]; j.p - i >= 0; i += input[j.p]) {
				if (j.p - i == end) return j.c + 1;
				q.add(new Jump(j.p - i, j.c + 1));
			}
		}
		return -1;
	}

}