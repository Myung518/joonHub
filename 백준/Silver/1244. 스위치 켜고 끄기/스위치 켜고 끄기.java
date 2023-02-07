import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Student {
	int s, number;
	public Student(int s, int number) {
		this.s = s;
		this.number = number;
	}
}

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < n + 1; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		List<Student> studentList = new ArrayList<>();
		
		int stuCount = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < stuCount; i++) {
			st = new StringTokenizer(br.readLine());
			studentList.add(
					new Student(
							Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		for (Student stu : studentList) {
			if (stu.s == 1) {
				for(int i = stu.number; i < n + 1; i += stu.number) {
					arr[i] = arr[i] ^ 1;
				}
			} else {
				arr[stu.number] = arr[stu.number] ^ 1;
				for(int i = 1; stu.number - i > 0 && stu.number + i <= n && arr[stu.number - i] == arr[stu.number + i]; i++) {
					arr[stu.number - i] = arr[stu.number - i] ^ 1;
					arr[stu.number + i] = arr[stu.number + i] ^ 1;
				}
			}
		}
		for (int i = 1; i < n + 1; i++) {
			sb.append(arr[i]).append(" ");
            if (i % 20 == 0) {
				sb.append("\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();
	}

}
