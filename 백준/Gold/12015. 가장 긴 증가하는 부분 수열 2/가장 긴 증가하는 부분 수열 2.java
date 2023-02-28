import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
	static int n;
	static int[] input;
	static int[] a;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		a = new int[n + 1];
		input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		sb.append(f(0, 1));
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static int f(int path, int count) {
		if (path == n) {
			return count - 1;
		}
		if(a[count - 1] < input[path]) {
			a[count++] = input[path];
		} else { //이분탐색
			int num = Arrays.binarySearch(a, 1, count, input[path]);
			a[num < 0 ? Math.abs(num) - 1 : num] = input[path];
		}
		return f(path + 1, count);
	}
	
}