class Solution {
    public int[][] answer;
	public int count;
	
	public int[][] solution(int n) {
		answer = new int[(int) Math.pow(2, n) - 1][2];
        count = 0;
        f(1, 2, 3, n);
        return answer;
    }
	public void f(int start, int mid, int end, int n) {
		if (n == 0)
			return;
		f(start, end, mid, n-1);
		answer[count][0] = start;
		answer[count][1] = end;
        count++;
		f(mid, start, end, n-1);
	}
}