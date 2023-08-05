import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static void hanoiTower(int n, int i, int j) {
        int k = 6 - i - j;
        if (n == 1) {
            sb.append(i).append(" ").append(j).append("\n");
            return;
        }

        hanoiTower(n-1, i, k);
        hanoiTower(1, i, j);
        hanoiTower(n-1, k, j);
    }

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());
        sb.append((1 << n) - 1).append("\n");

        hanoiTower(n, 1, 3);

        System.out.print(sb);
    }
}
