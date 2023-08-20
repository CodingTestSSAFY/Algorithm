import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n, m, h;
    static byte[][][] tomato;
    static List<int[]> ripeTomatoes = new LinkedList<>();
    static List<int[]> newlyRipeTomatoes = new LinkedList<>();
    static int totalTomatoes, ripeCount = 0;

    static int[] dx = {1, -1, 0, 0, 0, 0};
    static int[] dy = {0, 0, 1, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        totalTomatoes = n * m * h;
        tomato = new byte[n][m][h];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {

                st = new StringTokenizer(br.readLine());

                for (int k = 0; k < m; k++) {
                    tomato[j][k][i] = Byte.parseByte(st.nextToken());
                    if (tomato[j][k][i] == 1) {
                        ripeTomatoes.add(new int[] {j, k, i});
                    }
                    if (tomato[j][k][i] == -1) {
                        totalTomatoes--;
                    }
                }
            }
        }

        ripeCount += ripeTomatoes.size();

        int day = 0;

        while (ripeCount < totalTomatoes) {
            newlyRipeTomatoes.clear();
            for (int[] ripeIndex : ripeTomatoes) {
                ripe(ripeIndex[0], ripeIndex[1], ripeIndex[2]);
            }

            if (newlyRipeTomatoes.size() == 0) {
                break;
            }
            day++;
            ripeCount += newlyRipeTomatoes.size();
            ripeTomatoes.clear();
            ripeTomatoes.addAll(newlyRipeTomatoes);
        }

        if (ripeCount == totalTomatoes) {
            System.out.println(day);
        } else {
            System.out.println(-1);
        }
    }

    static void ripe(int x, int y, int z) {

        for (int i = 0; i < 6; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            int nz = z + dz[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= m || nz < 0 || nz >= h) {
                continue;
            }

            if (tomato[nx][ny][nz] != 0) {
                continue;
            }

            tomato[nx][ny][nz] = 1;
            newlyRipeTomatoes.add(new int[]{nx, ny, nz});
        }
    }
}