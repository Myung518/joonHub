import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Ball implements Comparable<Ball>{
	int num, color, size;

	public Ball(int num, int color, int size) {
		super();
		this.num = num;
		this.color = color;
		this.size = size;
	}

	@Override
	public int compareTo(Ball o) {
		return this.size == o.size ? this.color - o.color : this.size - o.size;
	}
}

public class Main {
	static int n;
	static Map<Integer, Ball> balls = new HashMap<>();
	static int[] sumSize;
	static List<Entry<Integer, Ball>> ballsList;
	static PriorityQueue<Ball> pq = new PriorityQueue<>();
	static Map<Integer, Integer> colors = new HashMap<>();
	static int[] result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		sumSize = new int[n + 1];
		result = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int color = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			pq.add(new Ball(i, color, size));
		}
		
		int total = 0;
		Ball pastBall = new Ball(0, 0, 0);
		int sizeCount = 0;
		while (!pq.isEmpty()) {
			Ball ball = pq.poll();
			
			sizeCount = pastBall.size != ball.size ? 1 : sizeCount + 1;
			total += ball.size;
			colors.compute(ball.color, (k, v) -> v == null ? ball.size : v + ball.size);
			
			if (pastBall.size == ball.size && pastBall.color == ball.color) {
				result[ball.num] = result[pastBall.num];
				continue;
			}
			result[ball.num] = total - colors.get(ball.color) - ball.size * (sizeCount - 1);
			pastBall = ball;
		}
		
		for (int i = 1; i <= n; i++) {
			sb.append(result[i]).append("\n");
		}
		
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}