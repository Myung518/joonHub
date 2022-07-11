import java.io.*;
import java.util.ArrayList;
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int count = 0;
		while (n --> 0) {
			String s = br.readLine();
			ArrayList<Character> a = new ArrayList<Character>();
			int i = 0;
			for (; i < s.length(); i++) {
				if (a.contains(s.charAt(i))) {
					break;
				}
				else {
					a.add(s.charAt(i));
					while (i+1 < s.length() && s.charAt(i) == s.charAt(i+1)) {
						i++;
					}
				}
			}
			if (i == s.length()) count++;
		}
		System.out.println(count);
	}

}
