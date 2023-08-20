import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int r, c, answer = Integer.MIN_VALUE;
	static char[][] map;
	static Set<Character> set = new HashSet<>();
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	
	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		map = new char[r][c];
		
		for (int i = 0; i < r; ++i) {
			String line = br.readLine();
			for (int j = 0; j < c; ++j) {
				map[i][j] = line.charAt(j);
			}
		}
		
		set.add(map[0][0]);
		dfs(0, 0);
		System.out.println(answer);
	}
	
	static void dfs(int x, int y) {
		
		boolean stuck = true;
		
		for (int i = 0; i < 4; ++i) {
			
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || nx >= r || ny < 0 || ny >= c) {
				continue;
			}
			
			if (!set.contains(map[nx][ny])) {
				set.add(map[nx][ny]);
				dfs(nx, ny);
				set.remove(map[nx][ny]);
			}
		}
		
		if (stuck) {
			answer = Math.max(answer, set.size());
		}
	}
}