import java.util.*;
import java.io.*;
class Point {
	int point;
	int weight;
	public Point(int point, int weight) {
		this.point = point;
		this.weight = weight;
	}
}
public class Main {
	static LinkedList<Point> al[];
	static boolean[] check;
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		al = new LinkedList[n + 1];
		for (int i = 0; i <= n; i++) {
			al[i] = new LinkedList<>();
		}
		check = new boolean[n + 1];
		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			al[a].add(new Point(b, w));
//			al[b].add(new Point(a, w));
		}
		if (n == 1)
			System.out.println(0);
		else {
			dfs(1);
			System.out.println(result);
		}
	}
	static int result = Integer.MIN_VALUE;
	public static int dfs(int start) {
		check[start] = true;
		int max1 = Integer.MIN_VALUE;
		int max2 = Integer.MIN_VALUE;
		for (Point p : al[start]) {
			if (!check[p.point]) {
				int tmp;
				if (max1 < (tmp = dfs(p.point) + p.weight)) {
					max2 = Math.max(max2, max1);
					max1 = tmp;
				}
				else if (max2 < tmp) {
					max2 = tmp;
				}
			}
		}
//		System.out.println(start + " " + max1 + " " + max2);
		if (max1 == Integer.MIN_VALUE)
			return 0;
		if (max2 != Integer.MIN_VALUE)
			result = Math.max(result, max1 + max2);
		else result = Math.max(result, max1);
		return max1;
	}

}
