import java.io.*;
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] coin = {500, 100, 50, 10, 5, 1};
		int count = 0;
		int t = 0;
		while (t < coin.length) {
			if (coin[t] + n > 1000)
				t++;
			else {
				n += coin[t];
				count++;
			}
			if (n == 1000)
				break;
		}
		System.out.println(count);
	}

}
