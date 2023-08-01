import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int n, m, max = 0;
	static int[][] board;
	static boolean[][] visited;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	static void dfs(int cnt, int value, int x, int y) {
		if (cnt == 4) {
			max = Math.max(value, max);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
				continue;
			}
			
			if (visited[nx][ny]) {
				continue;
			}
			
			visited[nx][ny] = true;
			dfs(cnt + 1, value + board[nx][ny], nx, ny);
			visited[nx][ny] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		board = new int[n][m];
		visited = new boolean[n][m];

		for (int i = 0; i < n; i++) {

			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < m; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				
				visited[i][j] = true;
				dfs(1, board[i][j], i, j);
				visited[i][j] = false;
				/*
				 * ㅏㅓㅗㅜ 모양의 테트로미노는 dfs로 체크할 수 없음
				 */
				int sum;
				
				/* ㅏ 모양 */
				if (i > 0 && i < n-1 && j < m-1) { 
					sum = board[i][j] + board[i-1][j] + board[i+1][j] + board[i][j+1];
					max = Math.max(max, sum);
				}
				
				/* ㅓ 모양 */
				if (i > 0 && i < n-1 && j > 0) {//
					sum = board[i][j] + board[i-1][j] + board[i+1][j] + board[i][j-1];
					max = Math.max(max, sum);
				}
				
				/* ㅗ 모양 */
				if (i > 0 && j > 0 && j < m-1) {
					sum = board[i][j] + board[i-1][j] + board[i][j-1] + board[i][j+1];
					max = Math.max(max, sum);
				}
				
				/* ㅜ 모양 */
				if (i < n-1 && j > 0 && j < m-1) {
					sum = board[i][j] + board[i+1][j] + board[i][j-1] + board[i][j+1];
					max = Math.max(max, sum);
				}
				
			}
		}
			
		System.out.println(max);
	}
}
