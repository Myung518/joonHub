import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

class Point {
	int x, y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class Main {
	static int R, C;
	static char[][] arr;
	static Set<Character> s = new HashSet<>();
	static final int STARTX = 0, STARTY = 0;
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[R][];
		
		for (int i = 0; i < R; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		s.add(arr[STARTX][STARTY]);
		f(STARTX, STARTY);
		System.out.println(max);
	}
	static int dis[][] = { {-1, 0}, {0, -1}, {1, 0}, {0, 1} };
	public static void f(int x, int y) {
		max = Math.max(max, s.size());
		for (int[] tmp : dis) {
			int tx = x + tmp[0];
			int ty = y + tmp[1];
			if (tx >= 0 && ty >= 0 && tx < R && ty < C && !s.contains(arr[tx][ty])) {
				s.add(arr[tx][ty]);
				f(tx, ty);
				s.remove(arr[tx][ty]);
			}
		}
	}

}
