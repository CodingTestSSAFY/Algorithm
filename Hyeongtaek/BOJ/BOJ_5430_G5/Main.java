import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int t, n, left, right;
	static int[] data, answer;
	static String p;
	static boolean reversed;

	public static void main(String[] args) throws IOException {
		
		t = Integer.parseInt(br.readLine());
		
		testcase:
		for (int j = 0; j < t; j++) {
			
			p = br.readLine();
			n = Integer.parseInt(br.readLine());
			data = new int[n];
			st = new StringTokenizer(br.readLine(), "[],");
			
			for (int i = 0; i < n; i++) {
				data[i] = Integer.parseInt(st.nextToken());
			}
			
			left = 0;
			right = n;
			reversed = false;
			
			for (int i = 0, size = p.length(); i < size; i++) {
				if (p.charAt(i) == 'R') {
					reversed = !reversed;
				} else if (left == right) {
					sb.append("error\n");
					continue testcase;
				} else if (reversed){
					right--;
				} else {
					left++;
				}
			}
			
			answer = new int[right - left];
			
			sb.append("[");
			for (int i = 0; i < answer.length; i++) {
				if (reversed) {
					answer[i] = data[right-1 - i];
				} else {
					answer[i] = data[left + i];										
				}
				
				sb.append(answer[i]);
				
				if (i < answer.length-1) {
					sb.append(",");
				}
			}
			sb.append("]\n");
		}
		
		System.out.println(sb);
	}
}
