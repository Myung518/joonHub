import java.io.*;
import java.util.*;
public class Main {

	static boolean[] number;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		for (int j = 1; j <= t; j++) {
			long n = Integer.parseInt(br.readLine());
//			System.out.println(123);
			number = new boolean[10];
			numberCount(n);
			if (n == 0) {
				System.out.println("Case #" + j + ": INSOMNIA");
			}
			else {
				int i = 2;
				for (; !numberAllTrue(); i++) {
					numberCount(n * i);
//					System.out.println(n*i);
				}
				System.out.println("Case #" + j + ": " + n * (i - 1));
			}
		}
	}
	public static void numberCount(long n) {
		for(char c : Long.toString(n).toCharArray()) {
			number[c - '0'] = true;
		}
	}

	public static boolean numberAllTrue() {
		for (int i = 0; i < number.length; i++) {
			if (number[i] == false)
				return false;
		}
		return true;
	}
}
