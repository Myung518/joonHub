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

public class Main {
	static int n, total;
	static int min = Integer.MAX_VALUE;
	static int[] person;
	static List<Integer>[] edge;
	static boolean[] team;
	static List<Integer> teamA, teamB;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());

		total = 0;
		min = Integer.MAX_VALUE;
		person = new int[n + 1];
		team = new boolean[n + 1];
		teamA = new ArrayList<>();
		teamB = new ArrayList<>();
		edge = new ArrayList[n + 1];
		for (int i = 0; i <= n; i++) {
			edge[i] = new ArrayList<>();
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			person[i] = Integer.parseInt(st.nextToken());
			total += person[i];
		}

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int tmp = Integer.parseInt(st.nextToken());
			for (int j = 0; j < tmp; j++) {
				edge[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		team(1);
		
		min = min == Integer.MAX_VALUE ? -1 : min;
		sb.append(min);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	//팀 나누기
	public static void team(int path) {
		if (path > n) {
			if (teamA.size() == 0 || teamB.size() == 0) return;
			int a = bfs(teamA);
			int b = bfs(teamB);
			if (a + b == total) {
				min = Math.min(min, Math.abs(a - b));
			}
			return;
		}
		teamA.add(path);
		team(path + 1);
		teamA.remove(Integer.valueOf(path));
		teamB.add(path);
		team(path + 1);
		teamB.remove(Integer.valueOf(path));
	}
	//A팀을 뽑고 난 후 남은 사람들이 연결이 되어있는지
	public static int bfs(List<Integer> team) {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[n + 1];
		int node = team.get(0);
		int total = person[node];
		q.add(node);
		visited[node] = true;
		while(!q.isEmpty()) {
			int start = q.poll();
			for (int next : edge[start]) {
				if (!visited[next] && team.contains(next)) {
					q.add(next);
					visited[next] = true;
					total += person[next];
				}
			}
		}
		
		return total;
	}
}