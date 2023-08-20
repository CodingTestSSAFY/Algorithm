import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    static long[] fact = new long[11];
    static long[] comb = new long[11];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static int[] decreasingNumber;
    static PriorityQueue<Long> minHeap = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(br.readLine());

        if (N < 10) {
            System.out.println(N);
        } else {
            int r = 2;

            N -= 9;

            /**
             * r개 자릿수는 (r+1)개 자릿수보다 무조건 작다.
             * r개 자릿수를 만들 수 있는 가짓수는 = 10Cr ( 0~9의 10개 숫자 중 r개를 뽑아 내림차순으로 나열한 수)
             * 따라서 (10C1 - 1) + ... 10Cr <= N < (10C1 - 1) + ... 10Cr+1 일 경우, N번째 감소하는 수는 r개 자릿수.
             * 이어서 N번째 감소하는 수는 r개 자릿수 중 {N - ( (10C1 - 1) + ... 10Cr ) + 1}번째 작은 숫자.
             * ** 10C1 - 1인 이유 : 한자릿수는 0~9로 0의 경우 10C0과 같은 경우로 제외해야한다.
             */
            while (N > 0 && r <= 10) {
                N -= nCr(10, r++);
            }

            if (N > 0) {
                System.out.println(-1);
                return;
            }

            N += nCr(10, --r);
            decreasingNumber = new int[r];
            combination(0, 0, r);

            for (int i = 0; i < N - 1; i++) {
                minHeap.poll();
            }

            System.out.println(minHeap.peek());
        }
    }

    static void combination(int cnt, int index, int r) {

        if (cnt == r) {
            long answer = 0;
            for (int i = r - 1; i >= 0; i--) {
                answer = 10 * answer + decreasingNumber[i];
            }

            minHeap.offer(answer);
            return;
        }

        for (int i = index; i < 10; i++) {
            decreasingNumber[cnt] = i;
            combination(cnt + 1, i + 1, r);
        }
    }

    static long factorial(int n) {
        if (fact[n] > 0) {
            return fact[n];
        }

        if (n < 1) {
            return fact[n] = 1;
        }

        return fact[n] = n * factorial(n - 1);
    }

    static long nCr(int n, int r) {
        if (n < r) {
            return -1;
        }

        if (comb[r] > 0) {
            return comb[r];
        }

        if (r > n / 2) {
            return comb[r] = nCr(n, n - r);
        }

        return comb[r] = factorial(n) / (factorial(n - r) * factorial(r));
    }
}
