import java.util.*;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int x = 1;
		for (int i = 0; i < n; i++) {
			x *= 2;
		}
		System.out.println(x);
	}

}
