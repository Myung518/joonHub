import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int n, m, k; //도시의 개수, 도로의 개수, 파괴된 도시의 개수
	static List<Integer>[] links;
	static List<Integer> destroyedCitys;
	static Set<Integer> findCitys;
	static List<Integer> resultCity;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		resultCity = new ArrayList<>();
		destroyedCitys = new ArrayList<>();
		findCitys = new HashSet<>();
		links = new ArrayList[n + 1];
		for (int i = 0; i < n + 1; i++) {
			links[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int city1 = Integer.parseInt(st.nextToken());
			int city2 = Integer.parseInt(st.nextToken());
			links[city1].add(city2);
			links[city2].add(city1);
		}
		
		k = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < k; i++) {
			destroyedCitys.add(Integer.parseInt(st.nextToken()));
		}
		
		findCity();
		if (resultCity.size() == 0 || findCitys.size() != destroyedCitys.size()) {
			sb.append(-1);
		} else {
			sb.append(resultCity.size()).append("\n");
			Collections.sort(resultCity);
			for (int city : resultCity) {
				sb.append(city).append(" ");
			}
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void findCity() {
		OUT:
		for (int destroyedCity : destroyedCitys) {
			for (int city : links[destroyedCity]) {
				if (!destroyedCitys.contains(city)) {
					continue OUT;
				}
				findCitys.add(city);
			}
			findCitys.add(destroyedCity);
			resultCity.add(destroyedCity);
		}
	}

}