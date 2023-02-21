import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int[][] input;
	static int[][] check;
	static boolean result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			result = false;
			input = new int[7][3];
			check = new int[7][7];
			int tmp = -1;
			for (int j = 1; j <= 6; j++) {
				input[j][0] = Integer.parseInt(st.nextToken());
				input[j][1] = Integer.parseInt(st.nextToken());
				input[j][2] = Integer.parseInt(st.nextToken());
			}
			int sum = 0;
			for (int j = 1; j <= 6; j++) {
				if (input[j][0] + input[j][1] + input[j][2] != 5) {
					tmp = 0;
					break;
				}
				sum += input[j][1];
			}
			if (sum % 2 == 1) tmp = 0; 
			if (tmp == -1)
				f(1, 2);
			sb.append(result ? 1 : 0).append(" ");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void f(int a, int b) {
		if (b == 7) {
			f(a + 1, a + 2);
			return;
		}
		if (a >= 6) {
			for (int j = 1; j <= 6; j++) {
				for (int i = 0; i < 3; i++) {
					if(check[j][i] != input[j][i])
						return;
				}
			}
			result = true;
			return;
		}
		
		if (check[a][0] + 1 <= input[a][0] && check[b][2] + 1 <= input[b][2]) { // a가 이기고 b가 지는 경우
			check[a][0]++;
			check[b][2]++;
			f(a, b + 1);
			check[a][0]--;
			check[b][2]--;
		}
		if (check[a][1] + 1 <= input[a][1] && check[b][1] + 1 <= input[b][1]) { // 무승부
			check[a][1]++;
			check[b][1]++;
			f(a, b + 1);
			check[a][1]--;
			check[b][1]--;
		}
		if (check[a][2] + 1 <= input[a][2] && check[b][0] + 1 <= input[b][0]) { // b가 이기고 a가 지는 경우
			check[a][2]++;
			check[b][0]++;
			f(a, b + 1);
			check[a][2]--;
			check[b][0]--;
		}
		return;
	}

}