import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); // n일동안 사용할 금액
		int m = Integer.parseInt(st.nextToken()); // 정확히 m번만 통장에서 돈 뺌
		List<Integer> list = new LinkedList<>();
		
		for (int i = 0; i < n; i++) {
			list.add(Integer.parseInt(br.readLine()));
		}
		
		int min = Collections.max(list);
		int max = 100000 * 10000;
		int result = Integer.MAX_VALUE;
		
		con:
		while (min <= max) {
			int k = (min + max) / 2;
			int count = 1;
			int total = k;
			for (int i : list) {
				total -= i;
				if (total < 0) {
					count++;
					total = k - i;
				}
				if (count > m) {
					min = k + 1;
					continue con;
				}
			}
			if (count <= m) {
				result = Math.min(result, k);
				max = k - 1;
			}
		}
		
		sb.append(result);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}