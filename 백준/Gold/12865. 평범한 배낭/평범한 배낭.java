import java.util.*;
import java.io.*;
public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[][] item = new int[n][2];
		int[] arr = new int[k + 1];
		for (int i = 1; i < k + 1; i++) {
			arr[i] = Integer.MIN_VALUE;
		}
		arr[0] = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			item[i][0] = Integer.parseInt(st.nextToken());
			item[i][1] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(item, new Comparator<int[]>() {

			@Override
			public int compare(int[] arg0, int[] arg1) {
				// TODO Auto-generated method stub
				Integer tmp = new Integer(arg0[0]);
				return tmp.compareTo(arg1[0]);
			}
			
		});
		for (int j = 0; j < n; j++) { //물건
			for (int i = k; i >= 0; i--) { //무게
				if (i - item[j][0] < 0) continue;
				if (arr[i - item[j][0]] == Integer.MIN_VALUE) continue;
				else {
					arr[i] = Math.max(arr[i], arr[i - item[j][0]] + item[j][1]);
				}
			}
		}
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < k + 1; i++) {
			max = Math.max(max, arr[i]);
//			System.out.println(arr[i]);
		}
		System.out.println(max);
	}

}
