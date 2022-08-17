import java.io.*;
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int a = Integer.parseInt(br.readLine());
		long[] b = new long[36];
		b[0] = 1;
		b[1] = 1;
		b[2] = 2;
		for(int i = 3; i <= a; i++) {
			for(int j = i - 1; 0 <= j; j--) {
				b[i] += b[j] * b[i - j - 1]; 
			}
		}
		System.out.println(b[a]);
	}

}