import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[] input;
	static int[] A;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		input = new int[n + 1];
		A = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		f(0, 0);
		sb.append(result);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	static int result;
	public static void f(int path, int count) {
		if (path == n + 1) {
			result = count - 1;
			return;
		}
		if (path == 0) {
			f(path + 1, count + 1);
			return;
		}
		if (A[count - 1] < input[path]) {
			A[count] = input[path];
			count += 1;
		} else {
			for (int i = 0; i < count; i++) {
				if (A[i] > input[path]) {
					A[i] = input[path];
					break;
				}
			}
		}
		f(path + 1, count);
	}

}