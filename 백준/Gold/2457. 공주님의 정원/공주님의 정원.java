import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static PriorityQueue<Flower> q;
	static final int START = 60;
	static final int END = 335;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		q = new PriorityQueue<>();
		
		while (n-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			q.add(new Flower(a, b, c, d));
		}
		sb.append(select());
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static int select() {
		int last = START;
		int count = 0;
		while (last < END) { //334에 지면 안됨 
			int maxEnd = 0;
			while (!q.isEmpty() && q.peek().start <= last) {
				Flower f = q.poll();
				maxEnd = Math.max(maxEnd, f.end);
			}
			if (maxEnd == 0) return 0;
			last = maxEnd;
			count++;
		}
		return count;
	}
	
	static class Flower implements Comparable<Flower>{
//		int[] md = {0, 31, 28, 31, 30, 31,    30, 31,   31, 30,  31, 30, 31};
		int[] md = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};
		int start, end;
		
		public Flower(int startM, int startD, int endM, int endD) {
			super();
			this.start = md[startM - 1] + startD;
			this.end = md[endM - 1] + endD;
		}

		@Override
		public int compareTo(Flower o) {
			return this.start - o.start;
		}
	}

}