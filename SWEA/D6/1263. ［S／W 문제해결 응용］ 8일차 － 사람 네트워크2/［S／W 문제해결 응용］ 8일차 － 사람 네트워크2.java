import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static List<Integer>[] link;
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());//노드 개수
			link = new ArrayList[n];
			
			for (int i = 0; i < n; i++) {
				link[i] = new ArrayList<>();
				for (int j = 0; j < n; j++) {
					if (st.nextToken().equals("1")) {
						link[i].add(j);
					}
				}
			}
			sb.append(String.format("#%d %d\n", t, f()));
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static int f() {
		int min = Integer.MAX_VALUE;
		Queue<Integer> q;
		int[] visited;
		for (int i = 0; i < n; i++) {
			int result = 0;
			q = new ArrayDeque<>();
			visited = new int[n];
			q.add(i);
			visited[i] = 1;
			while (!q.isEmpty()) {
				int node = q.poll();
				for (int next : link[node]) {
					if (visited[next] != 0) continue;
					q.add(next);
					visited[next] = visited[node] + 1;
					result += visited[node];
				}
			}
			min = Math.min(min, result);
		}
		return min;
	}

}