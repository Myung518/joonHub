import java.io.*;
import java.util.*;
public class Main {
	static String[] word;
	static boolean[] check;
	static Queue<Integer> q = new ArrayDeque<>();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int d = Integer.parseInt(st.nextToken());
		String s = st.nextToken();
		
		word = new String[d];
		check = new boolean[d];
		for (int i = 0; i < d; i++) {
			word[i] = br.readLine();
			if (word[i].equals(s)) {
				q.add(i);
				check[i] = true;
			}
		}
		to();
	}
	public static void to() {
		int start = 0;
		while(!q.isEmpty()) {
			start = q.poll();
			for (int i = 0; i < word.length; i++) {
				if (check[i])
					continue;
				if (word[start].length() + 1 == word[i].length()) {
//					int k = 0;
					int count = 0;
					for (int j = 0, k = 0; k < word[start].length() && j < word[i].length() && count <= 1;) {//단어 비교
//						if (word[start].equals(word[i].substring(0, word[i].length() - 1)) ||
//								(word[start].charAt(k) != word[i].charAt(j) && 
//								word[start].substring(k).equals(word[i].substring(j+1)))) {
						
						if (word[start].charAt(k) != word[i].charAt(j)) {
							count++;
							j++;
						}
						else {
							k++;
							j++;
						}
					}
					if (count <= 1) {
						q.add(i);
						check[i] = true;
					}
				}
			}
		}
		System.out.println(word[start]);
	}
}
