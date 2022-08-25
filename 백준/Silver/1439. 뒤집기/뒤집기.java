import java.io.*;
public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int[] count = {0, 0};
		count[s.charAt(0) - '0']++;
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) != s.charAt(i - 1)) {
				count[s.charAt(i) - '0']++;
			}
		}
		System.out.println(Math.min(count[0], count[1]));
	}

}
