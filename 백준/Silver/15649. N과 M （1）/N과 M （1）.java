import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[] path;
	static int[] used;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // n까지
		M = Integer.parseInt(st.nextToken()); // m개
		
		path = new int[M + 1];
		used = new int[N + 1];

		f2(0);
		bw.write(sb.toString());
		
		bw.flush();
		br.close();
		bw.close();
	}

	static void f2(int now) {
		if(now == M) {
			for (int i = 0; i < M; i++) {
				sb.append(path[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=1; i<=N; i++) {
			if(used[i] == 1) continue;
			
			path[now] = i;
			used[i] = 1;
			
			f2(now+1);
			
			path[now] = 0;
			used[i] = 0;
		}
	}

}
