import java.io.*;
import java.util.Arrays;
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		int[] a = new int[n];
		while (n --> 0) {
			int s = Integer.parseInt(br.readLine());
			a[n] = s;
		}
		Arrays.sort(a);
		for (int i : a) {
			sb.append(i + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
