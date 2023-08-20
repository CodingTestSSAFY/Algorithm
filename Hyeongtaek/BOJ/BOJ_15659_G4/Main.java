import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int n, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    static int[] num, operatorCount = new int[4];
    static int[] operator;

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        num = new int[n];
        operator = new int[n-1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operatorCount[i] = Integer.parseInt(st.nextToken());
        }

        backtrack(0);

        sb.append(max).append("\n").append(min);
        System.out.println(sb);
    }

    static void backtrack(int cnt) {
        if (cnt == n - 1) {
            calculate();
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operatorCount[i] > 0) {
                operatorCount[i]--;
                operator[cnt] = i; // 0: +, 1: -, 2: *, 3: /
                backtrack(cnt + 1);
                operatorCount[i]++;
            }
        }
    }

    static void calculate() {

        int res = mulAndDiv(0);

        for (int i = 0; i < n - 1; i++) {
            if (operator[i] > 1) {
                continue;
            }

            int operand = num[i + 1];

            if (i != n - 2 && operator[i + 1] > 1) {
                operand = mulAndDiv(i + 1);
            }

            if (operator[i] == 0) {
                res += operand;
            } else {
                res -= operand;
            }
        }

        max = Math.max(res, max);
        min = Math.min(res, min);
    }

    static int mulAndDiv(int index) {

        int res = num[index];

        while (index < n - 1 && operator[index] > 1) {

            if (operator[index] == 2) {
                res *= num[index + 1];
            } else {
                res /= num[index + 1];
            }

            index++;
        }

        return res;
    }
}