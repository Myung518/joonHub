import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int n;
	static int[] food;
	static int[][] memo;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		food = new int[n];
		memo = new int[n][3];
		for (int i = 0; i < n; i++) {
			food[i] = Integer.parseInt(br.readLine());
		}
		sb.append(f(0, 0));
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static int f(int path, int pastCheck) {
		if (path == n) return 0;
		if (memo[path][pastCheck] != 0) return memo[path][pastCheck];
		int max = 0;
		if (pastCheck == 0) { //먹거나 안먹거나
			max = Math.max(max, f(path + 1, 1) + food[path]);
		} else if (pastCheck == 1) { //그전에 먹었다면 1/2먹거나 안먹거나
			max = Math.max(max, f(path + 1, 2) + food[path] / 2);
		}
		max = Math.max(max, f(path + 1, 0));
		return memo[path][pastCheck] = max;
	}

}