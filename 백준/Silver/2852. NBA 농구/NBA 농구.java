import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int totalScore; // 양수면 1팀 음수면 2팀
	static int[] timeCheck;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		timeCheck = new int[3];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int team = Integer.parseInt(st.nextToken());
			int[] time = Stream.of(st.nextToken().split(":")).mapToInt(Integer::parseInt).toArray();
			timeCal(team, time);
		}
		if (totalScore != 0) { //경기 끝
			timeCheck[totalScore > 0 ? 1 : 2] += 2880 - timeCheck[0];
		}
		timeConvertRe();
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	public static void timeCal(int team, int[] time) {
		int convertT = timeConvert(time);
		int pastScore = totalScore;
		totalScore += (team == 1 ? 1 : -1);
		if (pastScore == 0) { //누군가 이겼다.
			timeCheck[0] = convertT;
		}
		else if (totalScore == 0) { //누군가 이김에서 끝났다.
			timeCheck[totalScore + pastScore > 0 ? 1 : 2] += convertT - timeCheck[0];
		}
	}
	public static int timeConvert(int[] time) {
		return time[0] * 60 + time[1];
	}
	public static void timeConvertRe() {
		for (int i = 1; i <= 2; i++) {
			if (timeCheck[i] / 60 < 10) {
				sb.append(0);
			}
			sb.append(timeCheck[i] / 60).append(":");
			
			if (timeCheck[i] % 60 < 10) {
				sb.append(0);
			}
			sb.append(timeCheck[i] % 60).append("\n");
		}
	}

}