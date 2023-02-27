import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n, m, k;
    static int[][] S2D2;
    static int[][] ground; // 땅
    static List<Integer>[][] trees;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        S2D2 = new int[n + 1][n + 1];
        ground = new int[n + 1][n + 1];
        trees = new LinkedList[n + 1][n + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j <= n; j++) {
                trees[i][j] = new LinkedList<>();    
            }
        }

        // 양분의 값
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                S2D2[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 상도가 심은 나무의 정보
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            trees[x][y].add(age);
        }
        //처음 양분은 모든 칸에 5만큼 들어있다.
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                ground[i][j] = 5;
            }
        }

        while (k-- > 0) {
            spring_summer();
            fall();
            winter();
        }
        
        
        int result = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                result += trees[i][j].size();
            }
        }
        sb.append(result);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void spring_summer() {
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j <= n; j++) {
                int tmp = 0;
                Iterator<Integer> tree = trees[i][j].iterator();
                int count = 0;
                while (tree.hasNext()) {
                    int age = tree.next();
                    if (age > ground[i][j]) {
                        tree.remove();
                        tmp += age / 2;
                        continue;
                    }
                    trees[i][j].set(count++, age + 1);
                    ground[i][j] -= age;
                }
                ground[i][j] += tmp;
            }
        }
    }

    public static void fall() {
        int[][] dis = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };
        
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = trees[i][j].size() - 1; k >= 0; k--) {
                    if (trees[i][j].get(k) % 5 == 0) {
                        for (int[] d : dis) {
                            int x = i + d[0];
                            int y = j + d[1];
                            if (x > 0 && y > 0 && x <= n && y <= n) {
                                trees[x][y].add(0, 1);
                            }
                        }
                    }
                }
            }
        }
    }

    public static void winter() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                ground[i][j] += S2D2[i][j];
            }
        }
    }

}