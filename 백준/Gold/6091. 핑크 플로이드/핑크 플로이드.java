import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Tree implements Comparable<Tree> {
	int node1, node2 , length;
	
	public Tree(int node1, int node2, int length) {
		super();
		this.node1 = node1;
		this.node2 = node2;
		this.length = length;
	}

	@Override
	public int compareTo(Tree o) {
		return this.length - o.length;
	}
}

public class Main {
	static List<Integer>[] result; //mst 결과 값
	static PriorityQueue<Tree> q; //우선순위 큐
	static int[] parent; //유니온 파인드

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());

		q = new PriorityQueue<>();
		result = new ArrayList[n + 1];
		parent = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			result[i] = new ArrayList<>();
			parent[i] = i;
		}

		for (int i = 1; i <= n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = i + 1; j <= n; j++) {
				q.add(new Tree(i, j, Integer.parseInt(st.nextToken())));
			}
		}
		
		mst();
		
		for (int i = 1; i <= n; i++) {
			sb.append(result[i].size()).append(" ");
			Collections.sort(result[i]);
			for (int node : result[i]) {
				sb.append(node).append(" ");
			}
			sb.append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void mst() {
		while(!q.isEmpty()) {
			Tree t = q.poll();
			if(!find(t.node1, t.node2)) {
				result[t.node1].add(t.node2);
				result[t.node2].add(t.node1);
				unionParent(t.node1, t.node2);
			}
		}
	}

	// ----유니온 파인드----
	public static void unionParent(int x, int y) {
		x = getParent(x);
		y = getParent(y);
		if (x > y) parent[y] = x;
		else parent[x] = y;
	}
	
	public static boolean find(int x, int y) {
		x = getParent(x);
		y = getParent(y);
		return x == y ? true : false;
	}
	
	public static int getParent(int x) {
		if (parent[x] == x) return x;
		return parent[x] = getParent(parent[x]);
	}
}