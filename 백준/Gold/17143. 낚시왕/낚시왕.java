import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Shark implements Comparable<Shark> {
	int x, speed, d, size;

	public Shark(int x, int speed, int d, int size) {
		super();
		this.x = x;
		this.speed = speed;
		this.d = d;
		this.size = size;
	}

	@Override
	public int compareTo(Shark o) {
		return this.x == o.x ? o.size - this.size : this.x - o.x;
		// 같은 위치라면 사이즈가 큰 순으로
	}

	@Override
	public String toString() {
		return "Shark [x=" + x + ", speed=" + speed + ", d=" + d + ", size=" + size + "]";
	}

}

public class Main {
	static int R, C, M; // 격자판의 크기 R, C / 상어의 수
	static PriorityQueue<Shark>[] sharkList;
	static PriorityQueue<Shark>[] newSharkList;
	static int position; // 낚시왕 위치
	static int result;
	static int[][] dis = { { 0, 0 }, { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } }; // 위 아래 오 왼

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		sharkList = new PriorityQueue[C + 1];
		newSharkList = new PriorityQueue[C + 1];
		for (int j = 0; j <= C; j++) {
			sharkList[j] = new PriorityQueue<>();
			newSharkList[j] = new PriorityQueue<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			if (d <= 2) s %= (R - 1) * 2;
			else s %= (C - 1) * 2;
			sharkList[y].add(new Shark(x, s, d, z));
		}

		moveKing(0);
		sb.append(result);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	public static void moveKing(int position) {
		while (position != C) {
			fishing(++position, 0);
			moveSharks();
		}
	}

	public static void fishing(int position, int x) {
		if (sharkList[position].size() == 0)
			return;
		if (x == 0) {
			Shark catchShark = sharkList[position].poll();
			result += catchShark.size;
			x = catchShark.x;
		} else if (x == sharkList[position].peek().x) {
			sharkList[position].poll(); // 같은 위치라면 작은 애들은 사라진다.
		} else
			return;
		fishing(position, x);
	}

	public static void moveSharks() {
		for (int j = 1; j <= C; j++) {
			while (!sharkList[j].isEmpty()) {
				Shark s = sharkList[j].poll();
				moveShark(s.x, j, s.speed, s.speed, s.d, s.size);
			}
		}
		for (int j = 1; j <= C; j++) {
			sharkList[j].clear();
			Shark s = null;
			while (!newSharkList[j].isEmpty()) {
				if (s != null && s.x == newSharkList[j].peek().x) {
					newSharkList[j].poll();
					continue;
				}
				s = newSharkList[j].poll();
				sharkList[j].add(s);
			}
			newSharkList[j].clear();
		}
	}

	public static void moveShark(int x, int y, int s, int count, int d, int size) {
		if (count == 0) {
			newSharkList[y].add(new Shark(x, s, d, size));
			return;
		}
		if (y == 1 && d == 4)
			d = 3;
		else if (y == C && d == 3)
			d = 4;
		else if (x == 1 && d == 1)
			d = 2;
		else if (x == R && d == 2)
			d = 1;
		moveShark(x + dis[d][0], y + dis[d][1], s, count - 1, d, size);
	}

}