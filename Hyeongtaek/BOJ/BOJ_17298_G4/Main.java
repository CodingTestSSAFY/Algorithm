import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int[] a, nge;
    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());
        a = new int[n];
        nge = new int[n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = n - 1; i >= 0; i--) {

            while (!stack.isEmpty() && stack.peek() <= a[i]) {
                stack.pop();
            }

            nge[i] = stack.isEmpty() ? -1 : stack.peek();

            stack.push(a[i]);
        }

        for (int e : nge) {
            sb.append(e).append(" ");
        }

        System.out.println(sb);
    }
}