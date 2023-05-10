import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Main {
	static Memo[] m;
	static int size;
	static Set<String> set;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
//		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String s = br.readLine();
		m = new Memo[11];
		set = new HashSet<String>();
		find(s);
		f(s.toCharArray(), 0);
		set.remove(s);
		List<String> l = new ArrayList<>(set);
		Collections.sort(l);
		for (String result : l) {
			sb.append(result).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void find(String s) {
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.add(i);
			} else if (s.charAt(i) == ')') {
				int k = stack.pop();
				m[size] = new Memo(k, i);
				size++;
			}
		}
	}
	
	public static void f(char[] s, int path) {
		if (path == size) {
			set.add(String.valueOf(s).replaceAll(" ", ""));
			return;
		}
		f(s, path + 1);
		s[m[path].i1] = ' ';
		s[m[path].i2] = ' ';
		f(s, path + 1);
		s[m[path].i1] = '(';
		s[m[path].i2] = ')';
	}
	
	static class Memo{
		int i1, i2;

		public Memo(int i1, int i2) {
			super();
			this.i1 = i1;
			this.i2 = i2;
		}
		
	}
}
