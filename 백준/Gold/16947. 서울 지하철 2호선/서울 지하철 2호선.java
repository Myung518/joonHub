import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static List<Integer>[] input;
	static int[] visited;
	static Queue<Integer> q;
	static List<Integer> link;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		visited = new int[n + 1];
		Arrays.fill(visited, -1);
		link = new ArrayList<>();
		input = new ArrayList[n + 1];
		q = new ArrayDeque<>();
		for (int i = 0; i < n + 1; i++) {
			input[i] = new ArrayList<>();
		}

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			input[a].add(b);
			input[b].add(a);
		}
		
		findCycle(0, 1);
		checkNotCycle();
		for (int i = 1; i <= n; i++) {
			sb.append(visited[i]).append(" ");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	public static void findCycle(int past, int now) {
		for (int node : input[now]) {
			if (node == past)
				continue;
			if (link.contains(node)) { //싸이클 발생
				for (int i = link.indexOf(node); i < link.size(); i++) {
					q.add(link.get(i));
					visited[link.get(i)] = 0;
				}
				return;
			}
			link.add(node);
			findCycle(now, node);
			link.remove(Integer.valueOf(node));
		}
	}
	
	public static void checkNotCycle() {
		while(!q.isEmpty()) {
			int now = q.poll();
			for (int node : input[now]) {
				if (visited[node] != -1) continue;
				q.add(node);
				visited[node] = visited[now] + 1;
			}
		}
	}

}