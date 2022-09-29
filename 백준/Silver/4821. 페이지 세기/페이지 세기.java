import java.io.*;
import java.util.*;
public class Main {
	static int[][] p;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		while (n != 0) {
			String[] s = br.readLine().split(",");
			p = new int[s.length][2];
			Set<Integer> page = new HashSet<>();
			for (int i = 0; i < s.length; i++) {
				p[i][0] = Integer.parseInt(s[i].split("-")[0]);
				if (!s[i].contains("-"))
					p[i][1] = p[i][0];
				else p[i][1] = Integer.parseInt(s[i].split("-")[1]);
				for (int j = p[i][0]; j <= p[i][1] && j <= n; j++) {
					page.add(j);
				}
			}
			
			System.out.println(page.size());
			n = Integer.parseInt(br.readLine());
		}
		
	}

}
