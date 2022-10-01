import java.io.*;
import java.util.*;
public class Main {
	static boolean[] check;
	static int n, m;
	static Set<Integer> tree[];
	static Set<Integer> s = new HashSet<>();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		check = new boolean[n];
		tree = new HashSet[n];
		for (int i = 0; i < n; i++) {
			tree[i] = new HashSet<>();
		}
		st = new StringTokenizer(br.readLine());
		int start = 0;
		for (int i = 0; i < n; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			if (tmp == -1) {
				start = i;
				continue;
			}
			tree[tmp].add(i);
		}
		m = Integer.parseInt(br.readLine());
		f(start);
		System.out.println(s.size());
	}
	public static int f(int start) {
		int count = 0;
		if (start == m) {
			return 0;
		}
		check[start] = true;
		for (int i : tree[start]) {
			if (!check[i]) {
				count += f(i);
			}
		}
		if (count == 0)
			s.add(start);
		return 1;
	}

}
