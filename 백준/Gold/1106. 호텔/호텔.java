import java.util.*;
import java.io.*;

public class Main {
	static int[][] city;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int c = Integer.parseInt(st.nextToken());//최소 고객 수
		int n = Integer.parseInt(st.nextToken());//도시
		city = new int[n][2];
		arr =  new int[c + 100];
		for (int i = 1; i < c + 100; i++)
			arr[i] = Integer.MAX_VALUE;
		arr[0] = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			city[i][0] = Integer.parseInt(st.nextToken());//cost
			city[i][1] = Integer.parseInt(st.nextToken());//guest
		}
		Arrays.sort(city, new Comparator<int[]>() {

			@Override
			public int compare(int[] arg0, int[] arg1) {
				// TODO Auto-generated method stub
				Integer tmp = new Integer(arg0[1]);
				return tmp.compareTo(arg1[1]);
			}
		});
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= c; j++) {
				if (arr[j] == Integer.MAX_VALUE)
					continue;
				int k = 1;
				int tmp;
				while ((tmp = j + city[i][1] * k) <= c) {
					arr[tmp] = Math.min(arr[tmp], arr[j] + city[i][0] * k);
					k++;
//					System.out.println(tmp + " " + arr[tmp]);
				}	
				if (j + city[i][1] * k > c) {
					arr[c] = Math.min(arr[c], arr[j] + city[i][0] * k);
				}
			}
		}

		System.out.println(arr[c]);
	}
}
