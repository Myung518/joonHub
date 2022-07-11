import java.io.*;
public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean a = false;
		for (int i = 0; i < 5; i++) {
			String s = br.readLine();
			s += "a";
			String[] f = s.split("FBI");
			if (f.length != 1) {
				System.out.print((i+1) + " ");
				a = true;
			}
		}
		if (!a) System.out.println("HE GOT AWAY!");
	}

}
