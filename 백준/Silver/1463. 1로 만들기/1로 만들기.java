import java.io.*;
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] m = new int[n + 1];
		System.out.println(f(n, m));
	}
	public static int f(int n, int[] m) {
		if (n == 1)
			return 0;
		if (m[n] != 0)
			return m[n];
		int a = 10000000, b = 10000000, c = 10000000;
		if (n % 3 == 0) {
			a = f(n/3, m) + 1;
		}
		if (n % 2 == 0) {
			b = f(n/2, m) + 1;
		}
		c = f(n-1, m) + 1;
		int min = Math.min(a, Math.min(b, c));
		m[n] = min;
		return min;
	}

}
