import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int n, m, x, y, k;
	static int[][] map;
	static int[] instruction;
	
	static class Dice {
		
		int[] value = new int[6];
		int top = 0;
		int east = 2;
		int south = 4;
		
		int roll(int direction, int i, int j) {
			
			int temp = top;
			
			if (direction == 1) {
				top = 5 - east;
				east = temp;
			} else if (direction == 2) {
				top = east;
				east = 5 - temp;
			} else if (direction == 3) {
				top = south;
				south = 5 - temp;
			} else {
				top = 5 - south;
				south = temp;
			}
			
			if (map[i][j] != 0) {
				value[5 - top] = map[i][j];
				map[i][j] = 0;
			} else {
				map[i][j] = value[5 - top];
			}
			
			return value[top];
		}
	}

	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		instruction = new int[k];
		Dice dice = new Dice();
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < k; i++) {
			instruction[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int ins : instruction) {
			
			int nx = x;
			int ny = y;
			
			if (ins == 1) {
				ny++;
			} else if (ins == 2) {
				ny--;
			} else if (ins == 3) {
				nx--;
			} else {
				nx++;
			}
			
			if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
				continue;
			}
			
			
			x = nx;
			y = ny;
			sb.append(dice.roll(ins, x, y)).append("\n");
		}
		
		System.out.print(sb);
	}
}
