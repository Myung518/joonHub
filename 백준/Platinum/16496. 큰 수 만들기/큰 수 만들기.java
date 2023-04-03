import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<Number> l;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		l = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		while (n-- > 0) {
			l.add(new Number(st.nextToken()));
		}
		
		Collections.sort(l);
//		System.out.println(l);
		
		if (l.get(0).number.equals("0")) sb.append(0);
		else {
			for (Number data : l) {
				sb.append(data.number);
			}
		}	
		
//		System.out.println(sb.toString().equals("1111111111111111110111101111101111110111111011011101010101011010101010"));
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	/**
	 * 값이 0만 주어지는 경우
	 */
	
	static class Number implements Comparable<Number>{
		String number;

		public Number(String n) {
			this.number = n;
		}

		@Override
		public int compareTo(Number o) {
			if (this.number.equals(o.number)) return 0;
			for (int i = 0; i < this.number.length() && i < o.number.length(); i++) {
				if (o.number.charAt(i) == this.number.charAt(i)) continue;
				return (o.number.charAt(i) - '0') - (this.number.charAt(i) - '0');
			}
//			if (this.number.length() < o.number.length()) {
//				for (int i = this.number.length(); i < o.number.length(); i++) {
//					if (o.number.charAt(i) == this.number.charAt(this.number.length() - 1)) continue;
//					return Integer.compare(o.number.charAt(i) - '0', this.number.charAt(this.number.length() - 1) - '0');
//				}
//			} else {
//				for (int i = o.number.length(); i < this.number.length(); i++) {
//					if (this.number.charAt(i) == o.number.charAt(o.number.length() - 1)) continue;
//					return Integer.compare(o.number.charAt(o.number.length() - 1), this.number.charAt(i));
//				}
//			}

			return -1 * Long.compare(Long.parseLong(this.number.concat(o.number))
					, Long.parseLong(o.number.concat(this.number)));
		}

		@Override
		public String toString() {
			return "Number [number=" + number + "]";
		}
	}

}