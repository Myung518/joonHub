import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, k;
	static int numSize;
	static int changeSize = 1;
	static Map<Integer, List<Integer>> numList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		String input = st.nextToken();
		numSize = input.length();
		int tmp = numSize;
		while(--tmp > 0) {
			changeSize *= tmp;
		}
		n = Integer.parseInt(input);
		k = Integer.parseInt(st.nextToken());
		numList = new HashMap<>();
		
		sb.append(bfs());

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static int bfs() {
		Queue<Number> q = new ArrayDeque<>();
		int max = Integer.MIN_VALUE;
		q.add(new Number(n, 0));
		while (!q.isEmpty()) {
			Number now = q.poll();
//			System.out.println(now.number + " " + now.count);
			if (now.count > k) break;
			FindChangeNum(now.number);
			for (int num : numList.get(now.number)) {
				Number number = new Number(num, now.count + 1);
				if (!q.contains(number)) {
					q.add(number);
					if (now.count + 1 == k) {
						max = Math.max(max, num);
					}
				}
			}
		}
		return max == Integer.MIN_VALUE ? -1 : max;
	}
	
	public static void FindChangeNum(int num) {
		if (!numList.containsKey(num)) {
			numList.put(num, new ArrayList<>());
		}
		List<Integer> list = numList.get(num);
		if (list.size() == changeSize) {
			return;
		}
		for (int i = 0; i < numSize - 1; i++) {
			for (int j = i + 1; j < numSize; j++) {
				char[] numS = Integer.toString(num).toCharArray();
				char tmp = numS[i];
				numS[i] = numS[j];
				numS[j] = tmp;
				int newNum = Integer.parseInt(String.valueOf(numS));
				if (Integer.toString(newNum).length() != numSize) continue;
				if (!list.contains(newNum)) {
					list.add(newNum);
					if (!numList.containsKey(newNum)) {
						numList.put(newNum, new ArrayList<>());
					}
					List<Integer> tmplist = numList.get(newNum);
					tmplist.add(num);
					numList.put(newNum, tmplist);
				}
			}
		}
		numList.put(num, list);
	}
	
	static class Number {
		int number, count;

		public Number(int number, int count) {
			super();
			this.number = number;
			this.count = count;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + count;
			result = prime * result + number;
			return result;
		}



		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Number other = (Number) obj;
			if (count != other.count)
				return false;
			if (number != other.number)
				return false;
			return true;
		}
	}
}