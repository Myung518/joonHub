import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		List<Integer> l = new ArrayList<>();
		int sum = 0;
		for (int i = 0; i < 9; i++) {
			l.add(Integer.parseInt(br.readLine()));
			sum += l.get(i);
		}
		
		sum -= 100;
		
		for (int i = 0; i < 9; i++) {
			if (sum - l.get(i) != l.get(i) && l.contains(sum - l.get(i))) {
				sum -= l.get(i);
				l.remove(i);
				l.remove(Integer.valueOf(sum));
				break;
			}
				
		}
		
		for (int i = 0; i < l.size(); i++) {
			sb.append(l.get(i)).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
		
	}

}