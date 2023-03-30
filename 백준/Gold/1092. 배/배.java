import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	static int n;
	static List<Integer> cw;
	static List<Integer> list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		list = new LinkedList<>();
		n = Integer.parseInt(br.readLine());
		cw = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
		Collections.sort(cw, Collections.reverseOrder());

		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(list, Collections.reverseOrder());
		
		sb.append(f());
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	public static int f() {
		int nowC = 0;
		int result = 1;
		
		Iterator<Integer> iter = list.iterator();
		while (list.size() != 0) {
			if (nowC >= n || !iter.hasNext()) {
				nowC = 0;
				result++;
				iter = list.iterator();
			}
			int box = iter.next();
//			System.out.println(box + " " + cw.get(nowC));
			if (box > cw.get(nowC)) {
				if (nowC == 0) return -1;
				continue;
			}
			iter.remove();
			nowC++;
		}
		return result;
	}
}