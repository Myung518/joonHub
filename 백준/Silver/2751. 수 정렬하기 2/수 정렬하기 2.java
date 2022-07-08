import java.io.*;
import java.util.*;
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		ArrayList<Integer> a = new ArrayList<Integer>();
		while(n --> 0) {
			a.add(Integer.parseInt(br.readLine()));
		}
		Collections.sort(a);
		for (int t : a) {
			sb.append(t + "\n");
		}
		System.out.println(sb);
	}
}
		
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		int n = Integer.parseInt(br.readLine());
//		int[] a = new int[n];
//		int t;
//		while(n --> 0) {
//			t = Integer.parseInt(br.readLine());
//			a[n] = t;
//		}
//		mergeSort(a, 0, a.length - 1);
//		for (int m : a) {
//			System.out.println(m);
//		}
//	}
//	public static void mergeSort(int[] a, int l, int r) {
//		if (l < r) {
//			int m = (l + r) / 2;
//			mergeSort(a, l, m);
//			mergeSort(a, m + 1, r);
//			merge(a, l, m, r);
//		}
//	}
//	public static void merge(int[] a, int l, int m, int r) {
//		int[] tmp = new int[r + 1];
//		int i = l, j = m + 1, k = l;
//		while (i <= m && j <= r) {
//			if (a[i] < a[j]) {
//				tmp[k++] = a[i++];
//			}
//			else {
//				tmp[k++] = a[j++];
//			}
//		}
//		while (i <= m) {
//			tmp[k++] = a[i++];
//		}
//		while (j <= r) {
//			tmp[k++] = a[j++];
//		}
//		while (l <= r) {
//			a[l] = tmp[l];
//			l++;
//		}
//	}
//
//}
