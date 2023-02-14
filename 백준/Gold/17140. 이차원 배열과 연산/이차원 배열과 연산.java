import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class Main {
	static final int MAXSIZE = 100;
	static int r, c, k;
	static int cSize = 3, rSize = 3;
	static int[][] arr;
	static Map<Integer, Integer> countm;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		arr = new int[MAXSIZE + 1][MAXSIZE + 1];

		for (int i = 1; i <= cSize; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = cSize;
			arr[0][i] = rSize;
			for (int j = 1; j <= rSize; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int count = 0;
		while (arr[r][c] != k && ++count <= 100) {
			countm = new HashMap<>();
			int maxSize = Integer.MIN_VALUE;
			if (cSize <= rSize) { // R연산 가로
				for (int i = 1; i <= rSize; i++) {
					countm.clear();
					for (int j = 1; j <= cSize; j++) {
						if (arr[i][j] == 0)
							continue;
						countm.compute(arr[i][j], (k, v) -> v == null ? 1 : v + 1);
					}

					List<Entry<Integer, Integer>> countList = sortMap();

					int k = 0;
					for (; k < countList.size() && k < MAXSIZE / 2; k++) {
						arr[i][k * 2 + 1] = countList.get(k).getKey();
						arr[i][k * 2 + 2] = countList.get(k).getValue();
					}
					k = (k - 1) * 2 + 2;
					while (k < cSize) {
						arr[i][++k] = 0;
					}
					maxSize = Math.max(countList.size() * 2, maxSize);
				}
				cSize = maxSize;
				continue;
			}
			for (int i = 1; i <= cSize; i++) {
				countm.clear();
				for (int j = 1; j <= rSize; j++) {
					if (arr[j][i] == 0)
						continue;
					countm.compute(arr[j][i], (k, v) -> v == null ? 1 : v + 1);
				}

				List<Entry<Integer, Integer>> countList = sortMap();

				int k = 0;
				for (; k < countList.size() && k < MAXSIZE / 2; k++) {
					arr[k * 2 + 1][i] = countList.get(k).getKey();
					arr[k * 2 + 2][i] = countList.get(k).getValue();
				}
				k = (k - 1) * 2 + 2;
				while (k < rSize) {
					arr[++k][i] = 0;
				}
				maxSize = Math.max(countList.size() * 2, maxSize);
			}
			rSize = maxSize;
		}
		if (count == 101)
			count = -1;
		sb.append(count);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	public static List<Entry<Integer, Integer>> sortMap() {
		List<Entry<Integer, Integer>> countList = new ArrayList<Entry<Integer, Integer>>(countm.entrySet());
		Collections.sort(countList, new Comparator<Entry<Integer, Integer>>() {
			public int compare(Entry<Integer, Integer> obj1, Entry<Integer, Integer> obj2) {
				// 오름차순 정렬
				return obj1.getValue() == obj2.getValue() ? obj1.getKey() - obj2.getKey()
						: obj1.getValue() - obj2.getValue();
			}
		});
		return countList;
	}
}
