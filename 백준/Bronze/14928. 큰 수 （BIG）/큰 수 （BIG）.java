import java.io.*;
public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
//		BigInteger N = new BigInteger(br.readLine());
//		BigInteger y = new BigInteger("20000303");
//		System.out.println(N.remainder(y));
		//BigInteger 시간초과
		
		String input = br.readLine();
		long temp = 0;
        for (int i = 0; i < input.length(); i++) {
        	temp = (temp * 10 + (input.charAt(i) - '0')) % 20000303;
        }
        System.out.println(temp);
	}

}
