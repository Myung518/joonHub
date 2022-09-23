import java.util.*;
import java.io.*;
public class Main {

	static List<Integer> arr[];
	static int[] check;
	static int count;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		arr = new ArrayList[n + 1];
		for (int i = 0; i < n; i++) {
			arr[i + 1] = new ArrayList<>();
		}
		check = new int[n + 1];
		count = 0;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[a].add(b);
			arr[b].add(a);
		}
		for (int i = 0; i < n; i++) {
			arr[i + 1].sort(Comparator.reverseOrder());
		}
		dfs(r);
		for (int i = 0; i < n; i++) {
			sb.append(check[i + 1]);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	public static void dfs(int r) {
		count++;
		check[r] = count;
		for (int t : arr[r]) {
			if (check[t] == 0)
				dfs(t);
		}
	}
}
