import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

class Pointer {
	int x, y;
	public Pointer(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static int N, L, R;
	static int[][] arr;
	static boolean[][] check;
	static Queue<Pointer> q = new ArrayDeque<>(); 
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		arr = new int[N][];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = new int[st.countTokens()];
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(check() - 1);
	}
	
	public static int check() {
		int day = 0;
		boolean isChange = true;
		while (isChange) {
			check = new boolean[N][arr[0].length];
			isChange = false;
			day++;
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					if (check[i][j]) continue;
					if (bfs(i, j)) isChange = true;
				}
			}
		}
		return day;
	}
	
	static int[][] dis = { {-1, 0}, {1, 0}, {0, 1}, {0, -1} };
	static Set<Pointer> set;
	public static boolean bfs(int startx, int starty) {
		set = new HashSet<>();
		int sum = 0;
		check[startx][starty] = true;
		q.add(new Pointer(startx, starty));
		while (!q.isEmpty()) {
			Pointer p = q.poll();
			sum += arr[p.x][p.y];
			set.add(new Pointer(p.x, p.y));
			
			for (int[] tmp : dis) {
				int nx = p.x + tmp[0];
				int ny = p.y + tmp[1];
				
				if (nx >= 0 && ny >= 0 && nx < arr.length && ny < arr[0].length && !check[nx][ny]) {
					int m = Math.abs(arr[nx][ny] - arr[p.x][p.y]);
					if (m >= L && m <= R) {
						check[nx][ny] = true;
						q.add(new Pointer(nx, ny));
					}
				}
			}
		}
		if (set.size() > 1) {
			change(sum);
			return true;
		}
		return false;
	}
	
	public static void change(int sum) {
		sum /= set.size();
		for (Pointer p : set) {
			arr[p.x][p.y] = sum;
		}
	}

}
