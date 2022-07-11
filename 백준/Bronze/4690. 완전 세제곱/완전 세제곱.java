public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[] m = new double[101];
		for (int i = 1; i < 101; i++) {
			m[i] = Math.pow(i, 3);
		}
		for (int i = 5; i < 101; i++) {
			for (int j = 2; j < i - 2; j++) {
				for (int k = j + 1; k < i - 1; k++) {
					for (int l = k + 1; l < i; l++) {
						if (m[i] == m[j] + m[k] + m[l]) {
							System.out.println("Cube = " + i + ", Triple = (" + j + "," + k + "," + l + ")");
						}
					}
				}
			}
		}
	}
}