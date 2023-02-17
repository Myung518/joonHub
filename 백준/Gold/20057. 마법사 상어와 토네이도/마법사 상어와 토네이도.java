import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
	static int n;
	static int[][] input;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(br.readLine());
		input = new int[n][n];

		for (int i = 0; i < n; i++) {
			input[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		solution();
		System.out.println(totalPoint);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static int[][] dis = { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } }; // 왼, 아래, 오, 위

	public static void solution() {
		int x = n / 2;
		int y = x;
		int count = 0;
		int index = 1;
		int disIndex = 0;

		while (!(x == 0 && y == 0)) {
			for (int i = 0; i < index; i++) {
				int nx = x + dis[disIndex][0];
				int ny = y + dis[disIndex][1];
				if (disIndex == 0)
					moveSand0(nx, ny);
				else if (disIndex == 1)
					moveSand1(nx, ny);
				else if (disIndex == 2)
					moveSand2(nx, ny);
				else if (disIndex == 3)
					moveSand3(nx, ny);
				input[nx][ny] = 0;
				x = nx;
				y = ny;
				if (x == 0 && y == 0)
					break;
			}
			disIndex++;

			if (disIndex >= dis.length)
				disIndex = 0;
			count++;
			if (count != 0 && count % 2 == 0) { // 방향 전환
				index++;
			}
		}
	}

	public static void moveSand0(int x, int y) {
		int total = input[x][y];
		int p1 = input[x][y] * 1 / 100;
		outSand(x - 1, y + 1, p1);
		outSand(x + 1, y + 1, p1);
		total -= p1 * 2;

		p1 = input[x][y] * 7 / 100;
		outSand(x - 1, y, p1);
		outSand(x + 1, y, p1);
		total -= p1 * 2;

		p1 = input[x][y] * 2 / 100;
		outSand(x - 2, y, p1);
		outSand(x + 2, y, p1);
		total -= p1 * 2;

		p1 = input[x][y] * 10 / 100;
		outSand(x - 1, y - 1, p1);
		outSand(x + 1, y - 1, p1);
		total -= p1 * 2;

		p1 = input[x][y] * 5 / 100;
		outSand(x, y - 2, p1);
		total -= p1;
		outSand(x, y - 1, total);
	}

	public static void moveSand1(int x, int y) { //아래
		int total = input[x][y];
		int p1 = input[x][y] * 1 / 100;
		outSand(x - 1, y - 1, p1);
		outSand(x - 1, y + 1, p1);
		total -= p1 * 2;

		p1 = input[x][y] * 7 / 100;
		outSand(x, y - 1, p1);
		outSand(x, y + 1, input[x][y] * 7 / 100);
		total -= p1 * 2;

		p1 = input[x][y] * 2 / 100;
		outSand(x, y - 2, p1);
		outSand(x, y + 2, p1);
		total -= p1 * 2;

		p1 = input[x][y] * 10 / 100;
		outSand(x + 1, y - 1, p1);
		outSand(x + 1, y + 1, p1);
		total -= p1 * 2;

		p1 = input[x][y] * 5 / 100;
		outSand(x + 2, y, p1);
		total -= p1;

		outSand(x + 1, y, total);
	}

	public static void moveSand2(int x, int y) {
		int total = input[x][y];
		int p1 = input[x][y] * 1 / 100;
		outSand(x - 1, y - 1, p1);
		outSand(x + 1, y - 1, p1);
		total -= p1 * 2;

		p1 = input[x][y] * 7 / 100;
		outSand(x - 1, y, p1);
		outSand(x + 1, y, p1);
		total -= p1 * 2;

		p1 = input[x][y] * 2 / 100;
		outSand(x - 2, y, p1);
		outSand(x + 2, y, p1);
		total -= p1 * 2;

		p1 = input[x][y] * 10 / 100;
		outSand(x - 1, y + 1, p1);
		outSand(x + 1, y + 1, p1);
		total -= p1 * 2;

		p1 = input[x][y] * 5 / 100;
		outSand(x, y + 2, p1);
		total -= p1;
		outSand(x, y + 1, total);
	}

	public static void moveSand3(int x, int y) {
		int total = input[x][y];
		int p1 = input[x][y] * 1 / 100;
		outSand(x + 1, y - 1, p1);
		outSand(x + 1, y + 1, p1);
		total -= p1 * 2;

		p1 = input[x][y] * 7 / 100;
		outSand(x, y - 1, p1);
		outSand(x, y + 1, p1);
		total -= p1 * 2;

		p1 = input[x][y] * 2 / 100;
		outSand(x, y - 2, p1);
		outSand(x, y + 2, p1);
		total -= p1 * 2;

		p1 = input[x][y] * 10 / 100;
		outSand(x - 1, y - 1, p1);
		outSand(x - 1, y + 1, p1);
		total -= (p1 * 2);

		p1 = input[x][y] * 5 / 100;
		outSand(x - 2, y, p1);
		total -= p1;
		outSand(x - 1, y, total);
	}

	static int totalPoint = 0;

	public static void outSand(int x, int y, int point) {
		if (x < 0 || x >= n || y < 0 || y >= n) {
			totalPoint += point;
			return;
		}
		input[x][y] += point;
	}

}