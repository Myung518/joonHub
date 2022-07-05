import java.util.*;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int a = n % 8;
		if (a > 5)
			a = 10 - a;
		if (a == 0) a = 2;
		System.out.println(a);
	}

}
