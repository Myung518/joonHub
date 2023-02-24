import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int r, c, t; //r x c의 격자판, t : 시간
	static int airX; // 공기청정기 머리 위치
	static int[][] map; //변하기 전
	static int[][] newMap; //변한 후를 담을 공간
	static int[][] dis = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}}; //오 위 왼 아래

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		map = new int[r][c];
		
		for (int i = 0; i < r; i++) {
			map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		for (int i = 0; i < r; i++) { //공기청정기 머리 위치 찾기
			if (map[i][0] == -1) {
				airX = i;
				break;
			}
		}
		
		for (int i = 0; i < t; i++) {
			oneSecond();
		}
		
		int sum = 0;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] == -1) continue;
				sum += map[i][j];
			}
		}
		sb.append(sum);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void oneSecond() {
		newMap = new int[r][c];
		//1. 미세먼지 확산
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (j == 0 && (i == airX || i == airX + 1)) {
					continue;
				}
				dustDiffusion(i, j);
			}
		}
		map = Arrays.copyOf(newMap, newMap.length);
		//2. 공기청정기 작동
		operate(airX, 0, 0, true, -1);
		operate(airX + 1, 0, 0, false, -1);
	}
	
	public static void dustDiffusion(int x, int y) { //미세먼지 확산
		int dust = map[x][y];
		for (int[] d : dis) {
			int nx = x + d[0];
			int ny = y + d[1];
			
			if (nx >= 0 && ny >= 0 && nx < r && ny < c && !(ny == 0 && (nx == airX || nx == airX + 1))) {
				newMap[nx][ny] += map[x][y] / 5;
				dust -= map[x][y] / 5;
			}
		}
		newMap[x][y] += dust;
	}
	
	public static void operate(int x, int y, int d, boolean type, int past) {
		if (map[x][y] == -1) return;
		if (type && ((y == c - 1 && x == airX) || 
				(x == 0 && (y == 0 || y == c - 1)))) {
			d = ++d % 4;
		}
		if (!type && ((y == c - 1 && x == airX + 1) ||
				(x == r - 1 && (y == 0 || y == c - 1)))) {
			d = (--d + 4) % 4;
		}
		int tmp = map[x][y];
		map[x][y] = past;
		operate(x + dis[d][0], y + dis[d][1], d, type, tmp);
	}
}