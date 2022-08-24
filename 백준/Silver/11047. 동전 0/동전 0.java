import java.util.*;
import java.io.*;
public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] a = new int[n];
		int count = 0;
		while (n --> 0) {
			a[n] = Integer.parseInt(br.readLine());
		}
		for (int i = 0; i < a.length; i++) {
			count += k / a[i];
			k %= a[i];
		}
		System.out.println(count);
	}

}
