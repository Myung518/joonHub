/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;


class Solution
{
	final static int TESTCOUNT = 10;
	final static int TOTALBOX = 100;
	final static int LOWEST_INDEX = 0;
	final static int BEST_INDEX = TOTALBOX - 1;
	static List<Integer> heightList;
	static int totalCanMoveCount;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int t = 1; t <= TESTCOUNT; t++) {
			StringTokenizer st;
			StringBuilder sb = new StringBuilder();
			
			totalCanMoveCount = Integer.parseInt(br.readLine());
			
			heightList = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < TOTALBOX; i++) {
				heightList.add(Integer.parseInt(st.nextToken()));
			}
			
			Collections.sort(heightList);
			
			while (totalCanMoveCount > 0) {
				getCanMoveCount();
				Collections.sort(heightList);
			}
			
			sb.append("#").append(t).append(" ").append(minus(heightList.get(LOWEST_INDEX), heightList.get(BEST_INDEX))).append("\n");
			bw.write(sb.toString());
			bw.flush();
		}
		br.close();
		bw.close();
	}
	
	public static void getCanMoveCount() {
		int canMoveCount = Math.min(totalCanMoveCount, 
				getHeightMinusMin(heightList.get(LOWEST_INDEX), heightList.get(LOWEST_INDEX + 1), 
									heightList.get(BEST_INDEX), heightList.get(BEST_INDEX - 1)));
		
		MoveBox(canMoveCount);
	}
	
	public static int getHeightMinusMin(int num1, int num2, int num3, int num4) {
		return Math.min(minus(num1, num2), minus(num3, num4)) + 1;
	}
	
	public static int minus(int num1, int num2) {
		return Math.abs(num1 - num2);
	}
	
	public static void MoveBox(int count) {
		heightList.set(BEST_INDEX, heightList.get(BEST_INDEX) - count);
		heightList.set(LOWEST_INDEX, heightList.get(LOWEST_INDEX) + count);
		totalCanMoveCount -= count;
	}
}