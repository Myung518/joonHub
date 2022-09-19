import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {

	static int[][] arr;
	static boolean[] check;
	static int count;
	static Queue<Integer> q = new LinkedList<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		arr = new int[n + 1][n + 1];
		check = new boolean[n + 1];
		count = 0;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a =  Integer.parseInt(st.nextToken());
			int b =  Integer.parseInt(st.nextToken());
			arr[a][b] = arr[b][a] = 1;
		}
		bfs(1);
		System.out.println(count);
	}

	public static void bfs(int v) {
		q.add(v);
		check[v] = true;
		
		while(!q.isEmpty()) {
			v = q.poll();
			
			for (int i = 1; i < check.length; i++) {
				if (arr[v][i] == 1 && !check[i]) {
					q.add(i);
					check[i] = true;
					count++;
				}
			}
		}
	}
}
