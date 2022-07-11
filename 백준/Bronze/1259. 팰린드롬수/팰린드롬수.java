import java.io.*;
public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String n = br.readLine();
		while (!n.equals("0")) {
			boolean b = true;
			for (int i = 0; i < n.length() / 2; i++) {
				if (n.charAt(i) != n.charAt(n.length() - i - 1)) {
					b = false;
					System.out.println("no");
					break;
				}
			}
			if (b) System.out.println("yes");
			n = br.readLine();
		}
	}

}
