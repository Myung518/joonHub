import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;

public class Main {
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		
		BigInteger big2 = new BigInteger("2");
		bw.write(big2.pow(n).subtract(new BigInteger("1")) + "\n");
		
		if (n <= 20) {
			hanoi(n, 1, 2, 3);
		}
		bw.write(sb.toString());
		bw.flush();
	}
	public static void hanoi(int n, int start, int middle, int dst) throws IOException {
		if (n == 1) {
			sb.append(start).append(" ").append(dst).append("\n");
			return;
		}
		hanoi(n - 1, start, dst, middle);
		sb.append(start).append(" ").append(dst).append("\n");
		hanoi(n - 1, middle, start, dst);
	}
}