import java.util.*;
class Solution {
    public int[] solution(String s) {
        int[] answer = {};
        s = s.replace("{", "-");
        s = s.replace("}", "-");
        s = s.replace(",", "-");
//        System.out.println(s);
        String[] tmp = s.split("-");
        Map<Integer, Integer> m = new HashMap<Integer, Integer>();
        Arrays.sort(tmp);
        int q = 0, count = 0;
        for (int i = 0; i < tmp.length; i++) {
        	if (tmp[i].equals(""))
        		continue;
        	if (q == 0) {
//        		System.out.println(tmp[i]);
        		q = Integer.parseInt(tmp[i]);
        		count++;
        		continue;
        	}
        	if (q != Integer.parseInt(tmp[i])) {
        		m.put(count, Integer.parseInt(tmp[i - 1]));
        		q = Integer.parseInt(tmp[i]);
        		count = 1;
        	}		
        	else count++;
        }
        m.put(count, Integer.parseInt(tmp[tmp.length - 1]));
        List<Integer> keyList = new ArrayList<>(m.keySet());
        keyList.sort((s1, s2) -> s2.compareTo(s1));
//        System.out.println(keyList);
        int i = 0;
        answer = new int[keyList.size()];
        for (int key : keyList) {
        	answer[i] = m.get(key);
        	i++;
        }
//        for (int j = 0; j < answer.length; j++) {
//        	System.out.println(answer[j]);
//        }
        return answer;
    }
}