import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Long> heap = new PriorityQueue<>();
        long answer = 0;

        for (int i = 0; i < n; i++) {
            heap.add(Long.parseLong(br.readLine()));
        }

        while (heap.size() > 1) {
            long comparison = heap.poll() + heap.poll();
            answer += comparison;
            heap.add(comparison);
        }

        System.out.println(answer);
    }
}
