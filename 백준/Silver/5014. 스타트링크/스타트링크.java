import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int f = Integer.parseInt(st.nextToken()); //건물 총 층수
		int s = Integer.parseInt(st.nextToken()); //현재 위치
		int g = Integer.parseInt(st.nextToken()); //목적지
		int u = Integer.parseInt(st.nextToken()); 
		int d = Integer.parseInt(st.nextToken());
		
		Map<Integer, Integer> m = new HashMap<>();
		Queue<Integer> q = new ArrayDeque<>();
		List<Integer> l = new ArrayList<>();
		l.add(u);
		l.add(d * -1);
		
		q.add(s);
		m.put(s, 0);
		OUT : while(!q.isEmpty()) {
			int start = q.poll();
			for (int i : l) {
				int next = start + i;
				if (next >= 1 && next <= f && !m.containsKey(next)) {
					m.put(next, m.get(start) + 1);
					q.add(next);
				}
				if (m.containsKey(g)) {
					break OUT;
				}
			}
		}
		if (m.containsKey(g)) {
			System.out.println(m.get(g));
		} else {
			System.out.println("use the stairs");
		}
	}

}
