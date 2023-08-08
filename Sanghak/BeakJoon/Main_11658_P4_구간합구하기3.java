import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11658_P4_구간합구하기3 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int N, M;
	static int[][] map;
	static int[][] sumMap;

	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N+1][N+1];
		sumMap = new int[N+1][N+1];
		
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<N+1; j++) {
				int input = Integer.parseInt(st.nextToken());
				map[i][j] = input;
				sumMap[i][j] = sumMap[i][j-1] + input;
			}
		}
		
		for(int i=0; i<M; i++) {
			
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			switch(op) {
			case 0:
				getZeroCase();
				break;
			case 1:
				getOneCase();
				break;
			}
		}
		
		System.out.println(sb);
	}
	
	static void getZeroCase() {
		
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int change = Integer.parseInt(st.nextToken());
		int sumChange = change - map[x][y];
		map[x][y] = change;
		
		for(int i=y; i<N+1; i++) {
			sumMap[x][i] += sumChange;
		}
	}
	
	static void getOneCase() {
		
		int x1 = Integer.parseInt(st.nextToken());
		int y1 = Integer.parseInt(st.nextToken());
		int x2 = Integer.parseInt(st.nextToken());
		int y2 = Integer.parseInt(st.nextToken());
		
		int result = 0;
		for(int i=x1; i<=x2; i++) {
			result += sumMap[i][y2] - sumMap[i][y1-1];
		}
		
		sb.append(result + "\n");
	}
}
