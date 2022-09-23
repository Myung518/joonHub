import java.util.*;
import java.io.*;
class Node {
	private int a;
	private int b;
	public Node(int a, int b) {
		this.a = a;
		this.b = b;
	}
	public int getA() {
		return a;
	}
	public int getB() {
		return b;
	}
	
}
public class Main {
	
	static int[][] dirs = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
	static int[][] arr;
	static Queue<Node> q = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		arr = new int[n + 1][m + 1];
		for (int i = 0; i < n; i++) {
			int j = 1;
			for (char c : br.readLine().toCharArray()) {
				arr[i + 1][j] = c - '0';
				j++;
			}
		}
		bfs(1, 1);
		System.out.println(arr[n][m]);
	}
	public static void bfs(int a, int b) {
		q.add(new Node(a, b));
		while (!q.isEmpty()) {
			Node n = q.poll();
			a = n.getA();
			b = n.getB();
			for (int[] dir : dirs) {
				int pointA = dir[0] + a;
				int pointB = dir[1] + b;
				if (pointA < arr.length && pointA > 0 && pointB < arr[1].length && pointB > 0 && arr[pointA][pointB] == 1) {
					q.add(new Node(pointA, pointB));
					arr[pointA][pointB] = arr[a][b] + 1;
//					System.out.println(pointA + " " + pointB + " " + arr[pointA][pointB]);
				}
			}
		}
	}
}
