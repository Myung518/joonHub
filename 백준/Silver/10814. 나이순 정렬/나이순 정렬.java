import java.util.*;
import java.io.*;
class People {
	int age;
	String name;
	public People(int age, String name) {
		this.name = name;
		this.age = age;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		ArrayList<People> a = new ArrayList<People>();
		while (n --> 0) {
			st = new StringTokenizer(br.readLine(), " ");
			People p = new People(Integer.parseInt(st.nextToken()), st.nextToken());
			a.add(p);
		}
		Collections.sort(a, new Comparator<People>() {
			@Override
			public int compare(People p1, People p2) {
				// TODO Auto-generated method stub
				return p1.getAge()-p2.getAge();
			}
		});
		for (People t : a) {
			System.out.println(t.getAge() + " " + t.getName());
		}
	}

}