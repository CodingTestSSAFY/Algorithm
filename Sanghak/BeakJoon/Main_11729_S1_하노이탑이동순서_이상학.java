import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_11729_S1_하노이탑이동순서_이상학 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		
		int N = Integer.parseInt(br.readLine());
		hanoi(N, 1, 2, 3);
		System.out.println(sb);
		
	}
	
	static void hanoi(int N, int start, int mid, int dest) {
 
		/**
		 * 1. 시작점에서 중간 지점에 가장 큰 원판을 뺀 나머지 옮김
		 * 2. 시작접에서 가장 큰 원판 목적지로 옮김
		 * 3. 중간 지점에서 나머지 원판 목적지로 옮김
		 */
		if (N == 1) {
			sb.append(start + " " + dest + "\n");
			return;
		}
	 
		hanoi(N - 1, start, dest, mid);
		sb.append(start + " " + dest + "\n");
		hanoi(N - 1, mid, start, dest);
	}
}

