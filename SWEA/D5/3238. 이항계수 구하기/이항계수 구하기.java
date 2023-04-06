
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {

	static long N, R, P;
	static long factorial[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Long.parseLong(st.nextToken());
			R = Long.parseLong(st.nextToken());
			P = Long.parseLong(st.nextToken());

			long r = nCr();
			
			sb.append((String.format("#%d %d\n", t, (r) % P)));
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static void fact() {
		factorial[0] = 1;
		for (int i = 1; i <= P; i++) {
			factorial[i] = (factorial[i - 1] * i) % P;
		}
	}
	
	static long nCr() {
		factorial = new long[(int) P + 1];
		fact();
		
		long re=1L;
		while (R > 0 || N > 0) {
			long tmp = N % P;
			long tmp2 = R % P;

			if (tmp2 > tmp) {
				return 0;
			}
			re *= factorial[(int) tmp];
			re %= P;
			for (int i = 0; i < P - 2; i++) {
				re *= factorial[(int) (tmp - tmp2)];
				re *= factorial[(int) tmp2];
				re %= P;
			}
			N /= P;
			R /= P;
		}
		return re;
	}
}
