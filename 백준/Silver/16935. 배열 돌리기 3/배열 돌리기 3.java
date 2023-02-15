import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

class Point1 {
	int x, y;

	public Point1(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static int n, m, r;
	static int[][] arr;
	static int[] carcs;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		arr = new int[n][m];
		for (int i = 0; i < n; i++) {
			arr[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		carcs = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		int size = n > m ? n : m;
		
		int N = n;
		int M = m;
		int[][] result = new int[size][size];
		for (int k = 0; k < r; k++) {
			result = new int[size][size];
			if (carcs[k] == 3 || carcs[k] == 4) {
				int tmp = m;
				m = n;
				n = tmp;
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					Point1 newP = carc(carcs[k], new Point1(i, j));
					result[newP.x][newP.y] = arr[i][j];
				}
			}
			if (carcs[k] == 3 || carcs[k] == 4) {
				N = n;
				M = m;
			}
			arr = Arrays.copyOf(result, n*m);
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sb.append(result[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static Point1 carc(int carcIndex, Point1 now) {
		switch(carcIndex) {
		case 1 : 
			return new Point1(n - 1 - now.x, now.y);
		case 2 :
			return new Point1(now.x, m - 1 - now.y);
		case 3 :
			return new Point1(now.y, m - 1 - now.x);
		case 4 :
			return new Point1(n - 1 - now.y, now.x);
		case 5 : 
			if (now.x < n / 2) { // 1
				if (now.y < m / 2) return new Point1(now.x, m / 2 + now.y);
				return new Point1(now.x + n / 2, now.y);
			}
			if (now.y < m / 2) return new Point1(now.x - n / 2, now.y);
			return new Point1(now.x, now.y - m / 2);
		default :
			if (now.x < n / 2) { 
				if (now.y < m / 2) return new Point1(now.x + n / 2, now.y);
				return new Point1(now.x, now.y - m / 2);
			}
			if (now.y < m / 2) return new Point1(now.x, now.y + m / 2);
			return new Point1(now.x - n / 2, now.y);
		}
	}

}