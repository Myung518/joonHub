import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

class Number implements Comparable<Number>{
	int num, absN;

	public Number(int num) {
		super();
		this.num = num;
		this.absN = Math.abs(num);
	}

	@Override
	public int compareTo(Number o) {
		return this.absN == o.absN ? this.num - o.num : this.absN - o.absN;
	}
}

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		PriorityQueue<Number> pq = new PriorityQueue<>();
		
		while (n-- > 0) {
			int input = Integer.parseInt(br.readLine());
			if (input == 0) {
				sb.append(pq.isEmpty() ? 0 : pq.poll().num).append("\n");
				continue;
			}
			pq.offer(new Number(input));
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}