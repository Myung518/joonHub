import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static final int[] sosu = {1, 2, 3, 5, 7, 9};
	static int n;
	static int[] newSosu;
	static boolean[] sosuList;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		newSosu = new int[n];
		sosuList = new boolean[9999];
		
		createSosu(0);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	public static void createSosu(int cnt) { //cnt: 자리
		if (cnt == n) {
			for (int i = 0; i < cnt; i++) {
				sb.append(newSosu[i]);
			}
			sb.append("\n");
			return;
		}
		for (int i = 0; i < sosu.length; i++) {
			if(cnt == 0 && (i == 0 || i == 5)) continue;
			if(cnt == 1 && newSosu[0] == sosu[i]) continue;
			newSosu[cnt] = sosu[i];
			if(cnt != 0 && check(cnt)) continue;
			createSosu(cnt + 1);
		}
		
	}
	public static boolean check(int cnt) {
		int number = 0;
		for (int i = 0; i <= cnt; i++) {
			number *= 10;
			number += newSosu[i];
		}
		for (int j = 2; j <= (int)Math.sqrt(number); j++) {
			if(number % j == 0) 
				return true;
		}
		return false;
	}

}