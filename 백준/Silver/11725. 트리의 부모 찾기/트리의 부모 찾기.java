import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static List<Integer>[] list;
	static Queue<Integer> q = new ArrayDeque<>();
	static int[] check;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		check = new int[n + 1];
		list = new ArrayList[n + 1];
		for (int i = 0; i <= n; i++) {
			list[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			list[x].add(y);
			list[y].add(x);
		}
		f();
		for (int i = 2; i <= n; i++) {
			System.out.println(check[i]);
		}
	}
	public static void f() {
		q.add(1);
		check[1] = 1;
		while (!q.isEmpty()) {
			int p = q.poll();
			for (int i : list[p]) {
				if (check[i] == 0) {
					check[i] = p;
					q.add(i);
				}
			}
		}
	}
}
