import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 세그먼트 트리
 * 바이너리 인덱스 트리 -> 펜윅 트리, BIT, 구간합을 빠르게 구하기 위한 자료구조
 * 	1. 트리 구성
 *		펜윅트리는 0번 index를 사용하지 않는다
 *		각각의 index(2진수)에서 가장 뒤에 위치한 1의 위치(값)만큼 자신을 포함하여 앞부분 index의 value 더하기
 */
public class Main {
	static int n, m, k;
	static long[]  fenwickTree;
	static long[] input;
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken()); //수 개수
		m = Integer.parseInt(st.nextToken()); //변경 횟수
		k = Integer.parseInt(st.nextToken()); //구간합 구하는 횟수
		fenwickTree = new long[(int) (n + 1)]; //0번 index는 사용하지 않는다
		input = new long[(int) (n + 1)];
		//트리 구성
		for (int i = 1; i < n + 1; i++) {
			//가장 끝에 있는 1의 위치를 찾는다 -> (i & -i)
			input[i] = Long.parseLong(br.readLine());
			update(i, input[i]);
			
		}
		for (int i = 0; i < m + k; i++) {
			/*
			 * a == 1 ) b번째 수를 c로 바꿈
			 * a == 2 ) b번째 수부터 c번째 수까지 합 출력
			 */
//			System.out.println(Arrays.toString(fenwickTree));
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); 
			long b = Long.parseLong(st.nextToken());
			long c = Long.parseLong(st.nextToken());		
			if (a == 1) {
				update(b, c - input[(int) b]);
				input[(int) b] = c;
				continue;
			}
			sb.append(sum(c) - sum(b - 1)).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	//값을 포함하는 펜윅 트리들을 바꿔야한다
	public static void update(long index, long value) {
		while (index < n + 1) {
			fenwickTree[(int) index] += value;
			index += (index & -index);
		}
	}
	
	public static long sum(long index) {
		long result = 0;
		while (index > 0) {
			result += fenwickTree[(int) index];
			index -= index & -index;
		}
		return result;
	}
}