import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	static final int SIZE = 6;
	static Map<String, String> m = new HashMap<>();

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);
		int s = in.nextInt();
		String st = in.next();
		String result = "";
		initChar();
		for (int i = 0; i < s; i++) {
			String subst = st.substring(0 + i * SIZE, SIZE + i * SIZE);
			if (m.containsKey(subst)) {
				result = result.concat(m.get(subst));
				continue;
			}
			for (String data : m.keySet()) {
				int count = 0;
				for (int j = 0; j < SIZE; j++) {
					if (count > 2) {
						continue;
					}
					if (subst.charAt(j) != data.charAt(j)) {
						count++;
					}
				}
				if (count == 1) {
					result = result.concat(m.get(data));
					break;
				}
			}
			if (result.length() != i + 1) {
				System.out.println(i + 1);
				break;
			}
		}
		if (result.length() == s) {
			System.out.println(result);
		}
	}

	public static void initChar() {
		m.put("000000", "A");
		m.put("001111", "B");
		m.put("010011", "C");
		m.put("011100", "D");
		m.put("100110", "E");
		m.put("101001", "F");
		m.put("110101", "G");
		m.put("111010", "H");
	}

}