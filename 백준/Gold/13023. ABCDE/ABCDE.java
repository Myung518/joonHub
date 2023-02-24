import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static List<Integer>[] person; //친구관계 저장
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		visited = new boolean[n];
		person = new ArrayList[n + 1];
		for (int i = 0; i < n + 1; i++) {
			person[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			person[a].add(b);
			person[b].add(a);
		}
		
		//가상의 정점 n을 만들어서 0번부터 n-1번으로 가는 간선을 만들어줌
		for (int i = 0; i < n; i++) {
			person[n].add(i);
		}
		sb.append(f(n, 0) ? 1 : 0);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static boolean f(int a, int count) {
		if (count == 5) return true;
		for (int p : person[a]) {
			if (visited[p]) continue;
			visited[p] = true;
			if (f(p, count + 1)) return true;
			visited[p] = false;
		}
		return false;
	}

}