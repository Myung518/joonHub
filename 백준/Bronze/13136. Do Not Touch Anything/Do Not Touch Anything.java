import java.util.*;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		long r = sc.nextInt();
		long c = sc.nextInt();
		long n = sc.nextInt();
		
		long a = r / n;
		long b = c / n;
		
		if (r % n > 0) {
			a++;
		}
		if (c % n > 0) {
			b++;
		}
		System.out.println(a*b);
	}

}
