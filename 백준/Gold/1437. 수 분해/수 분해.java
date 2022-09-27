import java.io.*;
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int max = 1;
		if (n < 5)
			max = n;
		else {
			if (n % 3 == 1) {
				for (int i = 1; i < (n / 3) % 10007; i++) {
					max *= 3;
					max %= 10007;
				}
				max *= 4;
			}
			else if (n % 3 == 2) {
				for (int i = 0; i < (n / 3) % 10007; i++) {
					max *= 3;
					max %= 10007;
				}
				max *= 2;
			}
			else 
				for (int i = 0; i < (n / 3) % 10007; i++) {
					max *= 3;
					max %= 10007;
				}
			max %= 10007;
		}
		System.out.println(max);
	}
}
