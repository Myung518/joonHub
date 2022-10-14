import java.util.*;
import java.io.*;
public class Main {
	static int[] arr;
	static boolean[] check;
	static int n, result;
	static LinkedList<Integer> list;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());
		while(t --> 0) {
			n = Integer.parseInt(br.readLine());
			result = n;
			arr = new int[n + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			check = new boolean[n + 1];
//			for (int i = 1; i <= n; i++) {
//				if(check[i]) continue;
//				list = new LinkedList<>();
//				dfs(i);
//			}
			
			for (int i = 1; i <= n; i++) {
				if(check[i]) continue;
				list = new LinkedList<>();
				int tmp = i;
				while(!check[tmp]) {
//					System.out.println(tmp);
					list.add(tmp);
					check[tmp] = true;
					tmp = arr[tmp];
				}
				int a;
				if ((a = list.indexOf(tmp)) != -1) {
					int num = list.size() - a;
//					System.out.println(list + " " + num);
					result -= num;
				}
			}
			System.out.println(result);
		}
	}
//	public static void dfs(int start) {
//		list.add(start);
//		if(check[arr[start]]) return;
//		if (!list.contains(arr[start])) {
//			dfs(arr[start]);
//		}
//		else {
//			int num = list.size() - list.indexOf(arr[start]);
//			result -= num;
//			for (int tmp : list) {
//				check[tmp] = true;
//			}
//		}
//	}
}
