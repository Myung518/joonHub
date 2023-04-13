

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int n, k;
	static int[][] input;
	static String[] words;
	static int max;
	static boolean[] check;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken()) - 5;
		input = new int[n][7];
		check = new boolean[26];
		words = new String[n];
		check[0] = check[2] = check[8] = check[13] = check[19] = true;
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			words[i] = s.substring(4, s.length() - 4);
		}
		choose(0, 0);
		if (k >= 0) {
			sb.append(max);
		} else {
			sb.append(0);
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void choose(int path, int start) {
		if (path == k) {
			max = Math.max(max, f(0));
			return;
		}
		for (int i = start; i < 26; i++) {
			if (i == 0 || i == 2 || i == 8 || i == 13 || i == 19) continue;
			check[i] = true;
			choose(path + 1, i + 1);
			check[i] = false;
		}
	}
	
	public static int f(int path) {
		if (path == n) return 0;
		boolean isRead = true;
		for (int i = 0; i < words[path].length(); i++) {
			if (!check[words[path].charAt(i) - 'a']) {
				isRead = false;
				break;
			}
		}
		return f(path + 1) + (isRead ? 1 : 0); 
	}
}
