import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static String s;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		s = br.readLine();
		
		System.out.println(f());

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static int f() {
		for (int start = 0, end = s.length() - 1; start < end; start++, end--) {
			if (s.charAt(start) != s.charAt(end)) return s.length();
		}
		for (int start = 0, end = s.length() - 2; start < end; start++, end--) {
			if (s.charAt(start) != s.charAt(end)) return s.length() - 1;
		}
		return -1;
	}

}