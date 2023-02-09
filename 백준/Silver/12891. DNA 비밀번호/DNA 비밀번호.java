import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int s, p;
	static char[] DNA;
	static int[] minCount, subCount;
	static int result = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		s = Integer.parseInt(st.nextToken());
		p = Integer.parseInt(st.nextToken());
		
		DNA = new char[s];
		
		DNA = br.readLine().toCharArray();
		minCount = new int[4];
		subCount = new int[4];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			minCount[i] = Integer.parseInt(st.nextToken());
		}
		
		f(0);
		System.out.println(result);
	}
	
	public static void f(int next) {
		if (next == 0) {
			for(int i = 0; i < p; i++) {
				subCount[alCount(DNA[i])]++;
			}
			next = p - 1;
		}
		if (checkCount()) result++;
		if (++next == s) return;
		subCount[alCount(DNA[next - p])]--;
		subCount[alCount(DNA[next])]++;
		f(next);
	}

	public static int alCount(char al) {
		if (al == 'A') return 0;
		else if (al == 'C') return 1;
		else if (al == 'G') return 2;
		else return 3;
	}
	
	public static boolean checkCount() {
		for (int i = 0; i < 4; i++) {
			if (minCount[i] > subCount[i])
				return false;
		}
		return true;
	}
	
}