import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int G, P;
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		G = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());
		makeSet();
		int i = 1;
		for (; i <= P; i++) {
			int num = Integer.parseInt(br.readLine());
			if (find(num) == 0) {
				break;
			}
			union(parent[num], parent[num] - 1);
		}
		sb.append(i - 1);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void makeSet() {
		parent = new int[G + 1];
		for (int i = 1; i <= G; i++) {
			parent[i] = i;
		}
	}
	
	public static void union(int x, int y) {
		int a = find(x);
		int b = find(y);
		if (a > b) parent[a] = b;
		else parent[b] = a;
	}
	
	public static int find(int x) {
		if (parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}

}