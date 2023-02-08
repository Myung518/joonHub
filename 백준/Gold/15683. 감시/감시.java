import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;

class Point {
	int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
}

public class Main {
	static int n, m;
	static Map<Point, List<Set<Point>>> cctvWatchListMap = new HashMap<>();
	static Map<Point, Integer> cctvNumberMap = new HashMap<>();
	static List<Point> walls = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				int input = Integer.parseInt(st.nextToken());
				if (input == 0) {
					continue;
				}
				Point now = new Point(i, j);
				if (input == 6) {
					walls.add(now);
				} else {
					cctvNumberMap.put(now, input);
					cctvWatchListMap.put(now, new ArrayList<>());
				}
			}
		}
		
		for (Entry<Point, Integer> entry : cctvNumberMap.entrySet()) {
			cctvWatchListMap.put(entry.getKey(), getCctvWatchList(entry.getValue(), entry.getKey()));
		}
//		cctvWatchListMap.keySet().toArray();
		getMin(new ArrayList<>(cctvWatchListMap.keySet()), 0);
		
		System.out.println(n * m - max - walls.size());
	}

	public static List<Set<Point>> getCctvWatchList(int cctvNumber, Point now) {
		List<Set<Point>> cctvWatchList = new ArrayList<>();
		Set<Point> sUp = up(now);
		Set<Point> sDown = down(now);
		Set<Point> sLeft = left(now);
		Set<Point> sRight = right(now);
		if (cctvNumber == 1) {
			cctvWatchList.add(sUp);
			cctvWatchList.add(sDown);
			cctvWatchList.add(sLeft);
			cctvWatchList.add(sRight);
		} else if (cctvNumber == 2) {
			cctvWatchList.add(sumSets(sUp, sDown));
			sLeft.addAll(sRight);
			cctvWatchList.add(sumSets(sLeft, sRight));
		} else if (cctvNumber == 3) {
			cctvWatchList.add(sumSets(sLeft, sUp));
			cctvWatchList.add(sumSets(sLeft, sDown));
			cctvWatchList.add(sumSets(sRight, sUp));
			cctvWatchList.add(sumSets(sRight, sDown));
		} else if (cctvNumber == 4) {
			cctvWatchList.add(sumSets(sLeft, sUp, sDown));
			cctvWatchList.add(sumSets(sLeft, sDown, sRight));
			cctvWatchList.add(sumSets(sRight, sUp, sLeft));
			cctvWatchList.add(sumSets(sRight, sDown, sUp));
		} else if (cctvNumber == 5) {
			cctvWatchList.add(sumSets(sLeft, sUp, sDown, sRight));
		}
		return cctvWatchList;
	}

	public static Set<Point> sumSets(Set<Point>... sets) {
		Set<Point> sumSet = new HashSet<>();
		for (Set<Point> set : sets) {
			sumSet.addAll(set);
		}
		return sumSet;
	}

	public static Set<Point> left(Point now) {
		Set<Point> cctvWatch = new HashSet<>();
		for (int i = now.y; i >= 0; i--) {
			if (!wallCheck(now.x, i))
				break;
			cctvWatch.add(new Point(now.x, i));
		}
		return cctvWatch;
	}

	public static Set<Point> up(Point now) {
		Set<Point> cctvWatch = new HashSet<>();
		for (int i = now.x; i >= 0; i--) {
			if (!wallCheck(i, now.y))
				break;
			cctvWatch.add(new Point(i, now.y));
		}
		return cctvWatch;
	}

	public static Set<Point> right(Point now) {
		Set<Point> cctvWatch = new HashSet<>();
		for (int i = now.y; i < m; i++) {
			if (!wallCheck(now.x, i))
				break;
			cctvWatch.add(new Point(now.x, i));
		}
		return cctvWatch;
	}

	public static Set<Point> down(Point now) {
		Set<Point> cctvWatch = new HashSet<>();
		for (int i = now.x; i < n; i++) {
			if (!wallCheck(i, now.y))
				break;
			cctvWatch.add(new Point(i, now.y));
		}
		return cctvWatch;
	}

	public static boolean wallCheck(int x, int y) {
		for (Point wall : walls) {
			if (wall.x == x && wall.y == y) {
				return false;
			}
		}
		return true;
	}
	static Set<Point> result = new HashSet<>();
	static int max = Integer.MIN_VALUE;
	public static void getMin(ArrayList<Point> keyList, int i) {
		if (i == keyList.size()) {
			max = Math.max(result.size(), max);
			
//			if (result.size() >= 28) {
//				System.out.println(result.size());
//				for (int k = 0; k < n; k++) {
//					for (int j = 0; j < m; j++) {
//						if(result.contains(new Point(k, j))) {
//							System.out.print("X ");
//						}
//						else {
//							System.out.print("O ");
//						}
//					}
//					System.out.println();
//				}
//				System.out.println("---------------");
//			}
			

			return;
		}
		List<Set<Point>> list = cctvWatchListMap.get(keyList.get(i));
		for (int j = 0; j < list.size(); j++) {
			Set<Point> tmp = new HashSet<>();
			tmp.addAll(result);
			result = sumSets(result, list.get(j));
			getMin(keyList, i + 1);
			result.clear();
			result.addAll(tmp);
		}
	}
}
