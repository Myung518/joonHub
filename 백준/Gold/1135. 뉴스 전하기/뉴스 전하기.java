import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static List<Integer>[] p;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		p = new LinkedList[N];
		for (int i = 0; i < N; i++) {
			p[i] = new LinkedList<Integer>();
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int t = Integer.parseInt(st.nextToken());
			if (i == 0) continue;
			p[t].add(i);
		}
		
		sb.append(find(0));

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static int find(int now) {
		int size = p[now].size();
		if (size == 0) {
			return 0;
		}
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for (int link : p[now]) {
			pq.add(find(link) + 1);
		}
		int max = Integer.MIN_VALUE;
		while (!pq.isEmpty()) {
			max = Math.max(max, pq.poll() + --size);
		}
		return max;
	}

}