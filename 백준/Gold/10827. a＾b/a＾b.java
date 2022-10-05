import java.io.*;
import java.math.BigDecimal;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String tmp = st.nextToken();
		BigDecimal a = new BigDecimal(tmp);
		int b = Integer.parseInt(st.nextToken());
		System.out.println(a.pow(b).toPlainString());
	}
}
