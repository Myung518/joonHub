import java.util.*;
import java.io.*;
class Ascending implements Comparator<String>{
	public int compare(String s1, String s2)
	{
		if (s1.length() == s2.length())
			return s1.compareTo(s2);
		return s1.length() - s2.length();
	}
}
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		ArrayList<String> a = new ArrayList<String>();
		String s;
		while(n --> 0) {
			s = br.readLine();
			if (!a.contains(s))
				a.add(s);
		}
		Collections.sort(a, new Ascending());
		for (String t : a)
			System.out.println(t);
	}

}