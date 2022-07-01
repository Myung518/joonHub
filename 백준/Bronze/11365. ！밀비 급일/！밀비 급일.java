import java.util.*;
import java.io.*;
public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String a = br.readLine();
		while(!a.equals("END")) {
			for (int i = a.length() - 1; i >= 0; i--) {
				System.out.print(a.charAt(i));
			}
			System.out.println();
			a = br.readLine();
		}
	}

}
