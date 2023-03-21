import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

/*
 * (1, 1)에서 (N, M) 위치까지 이동
 */

class Pointer {
	int x, y;
	int l, k; // 움직인 수, 벽 부순 수
	boolean d;

	public Pointer(int x, int y, int l, int k, boolean d) {
		super();
		this.x = x;
		this.y = y;
		this.l = l;
		this.k = k;
		this.d = d;
	}
}

public class Main {
	static int N, M, K; // N x M의 행렬, 부술 수 있는 벽의 개수 : K
	static int[][] map;
	static int[][][] visited;
	static int[][] dis = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			map[i] = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		}

		sb.append(bfs());
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	public static int bfs() {
		Queue<Pointer> pq = new ArrayDeque<>();
		visited = new int[K + 1][N][M];
		pq.add(new Pointer(0, 0, 1, 0, true));
		visited[0][0][0] = 1;
		while (!pq.isEmpty()) {
			Pointer now = pq.poll();
			for (int[] d : dis) {
				int x = now.x + d[0];
				int y = now.y + d[1];
				if (x >= 0 && y >= 0 && x < N && y < M && !(x == now.x && y == now.y)) {
					if (map[x][y] == 1 && now.k + 1 <= K) {
						if (now.d && visited[now.k + 1][x][y] == 0) {
							pq.add(new Pointer(x, y, now.l + 1, now.k + 1, !now.d));
							visited[now.k + 1][x][y] = now.l + 1;
						} else if (!now.d && visited[now.k][x][y] == 0) {
							pq.add(new Pointer(now.x, now.y, now.l + 1, now.k, !now.d));
							visited[now.k][now.x][now.y] = now.l + 1;
						}
						continue;
					}
					if (map[x][y] == 0 && visited[now.k][x][y] == 0) {
						pq.add(new Pointer(x, y, now.l + 1, now.k, !now.d));
						visited[now.k][x][y] = now.l + 1;

						if (x == N - 1 && y == M - 1) {
							return now.l + 1;
						}
					}
				}
			}

		}
		
		if (0 == N - 1 && 0 == M - 1) {
			return 1;
		}
		return -1;
	}

	public static boolean checkVisited(int now, int past) {
		return now == 0 || now >= past;
	}

}