import java.util.*;
import java.io.*;
public class Main {
	static int size;
	static boolean[][] check;
	static List<Integer>[] g;
	static Queue<Integer> q = new ArrayDeque<>();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		size = Integer.parseInt(br.readLine());
		check = new boolean[size][size];
		g = new ArrayList[size];
		for (int i = 0; i < size; i++) {
			g[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++) {
				if (Integer.parseInt(st.nextToken()) == 1) {
					g[i].add(j);
				}
			}
		}
		bfs();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if(check[i][j]) System.out.print("1 ");
				else System.out.print("0 ");
			}
			System.out.println();
		}
	}
	public static void bfs() {
		for (int i = 0; i < size; i++) {
			List<Integer> l = new LinkedList<>();
			q.add(i);
			while (!q.isEmpty()) {
				int tmp = q.poll();
				l.add(tmp);
				for (int n : g[tmp]) {
					if (!check[i][n]) {
						check[i][n] = true;
						q.add(n);
					}
				}
			}
		}
	}

}
