import java.util.*;
import java.io.*;
public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); //시간
		int k = Integer.parseInt(st.nextToken()); //고ㅏ목수
		int[][] item = new int[k][2];
		int[] arr = new int[n + 1];
		for (int i = 1; i < n + 1; i++) {
			arr[i] = Integer.MIN_VALUE;
		}
		arr[0] = 0;
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			item[i][0] = Integer.parseInt(st.nextToken());//중요도
			item[i][1] = Integer.parseInt(st.nextToken());//공부시간
		}
		Arrays.sort(item, new Comparator<int[]>() {

			@Override
			public int compare(int[] arg0, int[] arg1) {
				// TODO Auto-generated method stub
				Integer tmp = new Integer(arg0[1]);
				return tmp.compareTo(arg1[1]);
			}
			
		});
//		int max = Integer.MIN_VALUE;
		for (int i = 0; i < k; i++) {
			for (int j = n; j >= 0; j--) {
				int tmp;
				if ((tmp = j - item[i][1]) < 0) continue;
				if (arr[tmp] == Integer.MIN_VALUE) continue;
				arr[j] = Math.max(arr[j], arr[tmp] + item[i][0]);
//				max = Math.max(max, arr[j]);
			}
		}
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n + 1; i++) {
			max = Math.max(max, arr[i]);
		}
		System.out.println(max);
	}

}
