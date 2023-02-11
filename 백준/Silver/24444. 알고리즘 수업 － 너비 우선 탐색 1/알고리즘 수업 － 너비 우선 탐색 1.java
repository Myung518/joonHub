import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, m, r;
	static List<Integer>[] input;
	static int[] visited;
	static int count = 1;
	static Queue<Integer> q = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		visited = new int[n + 1];
		input = new ArrayList[n + 1];
		for (int i = 0; i < n + 1; i++) {
			input[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			input[a].add(b);
			input[b].add(a);
		}
		
		for (int i = 1; i < n + 1; i++) {
			Collections.sort(input[i]);
		}
		bfs();
		
		for (int i = 1; i <= n; i++) {
			sb.append(visited[i]).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	public static void bfs() {
		q.add(r);
		visited[r] = count;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			for (int next : input[now]) {
				if(visited[next] == 0) {
					q.add(next);
					visited[next] = ++count;
				}
			}
		}
	}

}
