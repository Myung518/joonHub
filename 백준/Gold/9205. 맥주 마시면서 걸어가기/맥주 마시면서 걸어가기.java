import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 맥주 한 박스를 들고 출발
 * 맥주 한 박스에는 맥주가 20개
 * 50미터에 한 병씩
 */
public class Main {
	static int n;
	static Point[] point;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			n = Integer.parseInt(br.readLine());
			point = new Point[n + 2];
			
			for (int i = 0; i < n + 2; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				point[i] = new Point(x, y);
			}
			
			sb.append(f() ? "happy\n" : "sad\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static boolean f() {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[n + 2];
		q.add(0);
		visited[0] = true;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			if (disCalc(point[now], point[n + 1])) {
				return true;
			}
			for (int i = 1; i < n + 1; i++) {
				if (visited[i]) continue;
				if (disCalc(point[now], point[i])) {
					q.add(i);
					visited[i] = true;
				}
			}
		}
		
		return false;
	}
	
	public static boolean disCalc(Point start, Point end) {
		return Math.abs((start.x - end.x)) + Math.abs((start.y - end.y)) <= 1000;
	}
	
	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

}