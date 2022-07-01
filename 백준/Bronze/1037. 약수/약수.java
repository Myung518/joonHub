import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");

		int min = 1000000, max = 1;
		while (n --> 0) {
			int a = Integer.parseInt(st.nextToken());
			if (min > a) min = a;
			if (max < a) max = a;
		}
		System.out.println(min*max);
	}

}
