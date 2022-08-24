import java.util.*;
import java.io.*;
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int[] p = new int[n];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			p[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(p);
		int count = 0;
		for (int i = 0; i < n; i++) {
			count += p[i] * (n - i);
		}
		System.out.println(count);
	}

}
