import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int n, sNum, num;
	static int left, right;
	static boolean[] switches;
	
	public static void main(String[] args) throws IOException {
		
		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		switches = new boolean[n];
		
		for (int i = 0; i < n; i++) {
			if (st.nextToken().equals("1")) {
				switches[i] = true;
			}
		}
		
		sNum = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < sNum; i++) {
			st = new StringTokenizer(br.readLine());
			
			if (st.nextToken().equals("1")) {
				num = Integer.parseInt(st.nextToken());
				for (int j = num - 1; j < n; j += num) {
					switches[j] = !switches[j];
				}
			} else {
				num = Integer.parseInt(st.nextToken());
				switches[num-1] = !switches[num-1];
				for (left = num - 2, right = num; left >= 0 && right < n; left--, right++) {
					if (switches[left] == switches[right]) {
						switches[left] = !switches[left];
						switches[right] = !switches[right];
					} else {
						break;
					}
				}
			}
		}
		
		for (int i = 0; i < n; i++) {
			if (i > 0 && i % 20 == 0) {
				sb.append("\n");
			}
			if (switches[i]) {
				sb.append(1);
			} else {
				sb.append(0);
			}
			sb.append(" ");
		}
		
		System.out.println(sb);
	}
}
