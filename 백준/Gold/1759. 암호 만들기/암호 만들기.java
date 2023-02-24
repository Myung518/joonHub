import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int L, C;
	static char[] input;
	static List<Character> mo;
	static char[] result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		
		input = br.readLine().replaceAll(" ", "").toCharArray();
		Arrays.sort(input);
		result = new char[L];
		init();
		f(0, 0, 0);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void init() {
		mo = new ArrayList<>();
		mo.add('a');
		mo.add('e');
		mo.add('i');
		mo.add('o');
		mo.add('u');		
	}
	
	public static void f(int path, int mcount, int jcount) {
		if (mcount + jcount == L) {
			if (mcount >= 1 && jcount >= 2) {
				for(int i = 0; i < L; i++) {
					sb.append(result[i]);
				}
				sb.append("\n");
			}
			return;
		}
		for (int i = path; i < input.length; i++) {
			result[mcount + jcount] = input[i];
			if (mo.contains(input[i])) {
				f(i + 1, mcount + 1, jcount);
			} else f(i + 1, mcount, jcount + 1);
		}
	}

}