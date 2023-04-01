import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
//		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(br.readLine());
		
		while (T-- > 0) {
			StringBuilder s = new StringBuilder(br.readLine().toUpperCase());
			for (int i = 0; i < s.length(); ++i) {
                char ch = s.charAt(i);
                if (ch <= 'C') s.setCharAt(i, '2');
                else if (ch <= 'F') s.setCharAt(i, '3');
                else if (ch <= 'I') s.setCharAt(i, '4');
                else if (ch <= 'L') s.setCharAt(i, '5');
                else if (ch <= 'O') s.setCharAt(i, '6');
                else if (ch <= 'S') s.setCharAt(i, '7');
                else if (ch <= 'V') s.setCharAt(i, '8');
                else s.setCharAt(i, '9');
            }
			if (s.toString().equals(new StringBuffer(s).reverse().toString())) sb.append("YES\n");
            else sb.append("NO\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}
