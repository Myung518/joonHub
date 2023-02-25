import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int[] a;
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		a = new int[n];
		f(0);
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	static int result;
	public static void f(int path) {
		if (path == n) {
			result++;
			return;
		}
		
		OUT : 
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < path; j++) {
				if (a[j] == i) continue OUT;
				if (Math.abs(j - path) == Math.abs(a[j] - i)) continue OUT;
			}
			a[path] = i;
			f(path + 1);
		}
	}

}
