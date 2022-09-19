import java.util.*;
import java.io.*;

public class Main {
	
	public static int[][] arr;
	public static boolean[] check;
	static Queue<Integer> q = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int n = Integer.parseInt(st.nextToken()); //정점개수
		int m = Integer.parseInt(st.nextToken()); //간선개수
		int v = Integer.parseInt(st.nextToken()); //시작 번호
		
		arr = new int[n + 1][n + 1];
		check = new boolean[n+1];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[a][b] = arr[b][a] = 1;
		}
		dfs(v);
		System.out.println();
		check = new boolean[n+1];
		bfs(v);
	}
	public static void dfs(int v) {
		if (check[v])
			return;
		check[v] = true;
		System.out.print(v + " ");
		for (int i = 1; i < check.length; i++) {
			if (arr[v][i] == 1)
				dfs(i);
		}
	}
	public static void bfs(int v) {
		q.add(v);
		check[v] = true;
		
		while(!q.isEmpty()) {
			v = q.poll();
			System.out.print(v + " ");
			for (int i = 1; i < check.length; i++) {
				if (arr[v][i] == 1 && !check[i]) {
					q.add(i);
					check[i] = true;
				}
			}
		}
	}

}
