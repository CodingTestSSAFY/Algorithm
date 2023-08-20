import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n, m, d, originCount, count, targetX, targetY, kill = 0, answer = 0;
    static boolean[][] field;
    static int[] combination, archers = new int[3];
    static Queue<int[]> arrow = new ArrayDeque<int[]>();
    static int[] dx = { 0, -1, 0 };
    static int[] dy = { -1, 0, 1 };

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        boolean[][] originField = new boolean[n + 1][m];
        field = new boolean[n + 1][m];

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; ++j) {
                originField[i][j] = st.nextToken().equals("1");
                if (originField[i][j]) {
                    ++originCount;
                }
            }
        }

        combination = new int[m];
        for (int i = 0; i < 3; ++i) {
            combination[m - 1 - i] = 1;
        }

        while (combination != null) {
            count = originCount;
            field = arrayDeepCopy(originField);
            battle();
            combination = nextCombination(combination);
        }

        System.out.println(answer);
    }

    static void battle() {

        int index = 0;
        kill = 0;

        for (int i = 0; i < m; ++i) {
            if (combination[i] == 1) {
                archers[index++] = i;
            }
        }


        while (count > 0) {

            targetX = n;
            targetY = 0;

            for (int i = 0; i < 3; ++i) {
                bfs(archers[i]);
            }

            field[targetX][targetY] = false;
            advance();
        }

        answer = Math.max(answer, kill);
    }

    static void bfs(int archer) {

        arrow.clear();

        boolean[][] visited = new boolean[n][m];

        arrow.offer(new int[] { n, archer });
        int range = 1;

        while (!arrow.isEmpty() && range <= d) {

            for (int i = 0, size = arrow.size(); i < size; ++i) {
                int[] spot = arrow.poll();

                for (int j = 0; j < 3; ++j) {

                    int nx = spot[0] + dx[j];
                    int ny = spot[1] + dy[j];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                        continue;
                    }

                    if (!visited[nx][ny]) {
                        visited[nx][ny] = true;
                        if (field[nx][ny]) {
                            if (!(nx == targetX && ny == targetY)) {
                                field[targetX][targetY] = false;
                                ++kill;
                                --count;
                                targetX = nx;
                                targetY = ny;
                            }
                            return;
                        }
                        arrow.offer(new int[] {nx, ny});
                    }
                }
            }

            ++range;
        }
    }

    static void advance() {

        for (int j = 0; j < m; ++j) {
            if (field[n - 1][j]) {
                --count;
            }
        }

        for (int i = n - 2; i >= 0; --i) {
            for (int j = 0; j < m; ++j) {
                field[i + 1][j] = field[i][j];
            }
        }

        for (int j = 0; j < m; j++) {
            field[0][j] = false;
        }
    }

    static int[] nextCombination(int[] comb) {

        int i = comb.length - 1;

        while (i > 0 && comb[i - 1] >= comb[i]) {
            --i;
        }

        if (i == 0) {
            return null;
        }

        int j = comb.length - 1;

        while (comb[i - 1] >= comb[j]) {
            --j;
        }

        comb[i - 1] = 1;
        comb[j] = 0;

        j = comb.length - 1;
        while (i < j) {
            int temp = comb[i];
            comb[i] = comb[j];
            comb[j] = temp;
            ++i;
            --j;
        }

        return comb;
    }

    static boolean[][] arrayDeepCopy(boolean[][] origin) {

        boolean[][] res = new boolean[n + 1][];

        for (int i = 0; i <= n; i++) {
            res[i] = new boolean[m];
            System.arraycopy(origin[i], 0, res[i], 0, m);
        }

        return res;
    }
}