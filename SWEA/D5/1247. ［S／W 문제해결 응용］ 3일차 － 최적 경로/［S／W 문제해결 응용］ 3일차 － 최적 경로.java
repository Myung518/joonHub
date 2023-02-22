import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
	int node, size;
	int x, y;

	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
}
public class Solution {
	static int n;
	static boolean[] check;
	static List<Point> input;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());
			input = new ArrayList<>();
			check = new boolean[n + 2];
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < n + 2; i++) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				input.add(new Point(x, y));
			}
			min = Integer.MAX_VALUE;
			f(0, 0, 0);
			sb.append(String.format("#%d %d\n", t, min));
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	static int min;
	public static void f(int path, int total, int count) {
		if (count == n) {
			total += Math.abs(input.get(path).x - input.get(1).x) + Math.abs(input.get(path).y - input.get(1).y);
			min = Math.min(min, total);
		}
		if (min < total) return;
		for (int i = 2; i < n + 2; i++) {
			if (check[i]) continue;
			check[i] = true;
			int l = Math.abs(input.get(path).x - input.get(i).x) + Math.abs(input.get(path).y - input.get(i).y);
			f(i, total + l, count + 1);
			check[i] = false;
		}
	}

}
