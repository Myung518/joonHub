import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {

	static int[] arr;
	static boolean[] isprime;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		
		arr = new int[n];
		st = new StringTokenizer(br.readLine()," ");
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		isprime = new boolean[1000001];
		for(int i = 2; i < isprime.length; i++) {
			if (isprime[i])
				continue;
			for(int j = 2 * i; j < isprime.length; j += i){
				isprime[j] = true;
			}
		}
		long total = 1;
		for (int i : arr) {
			if (!isprime[i]) {
				total *= i;
				isprime[i] = true;
			}
		}
		if (total == 1)
			total = -1;
		System.out.println(total);
	}

}
