import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int total = -1;
		for (int i = n / 5; i >= 0; i--) {
			if ((n - (5 * i)) % 3 == 0) {
				total = i + (n - (5 * i)) / 3;
				break;
			}
		}
		System.out.println(total);
		br.close();
	}

}