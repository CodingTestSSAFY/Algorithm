
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_6808_D3 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static final int SIZE = 9;
	static int T;
	static int[] kuyoungCard = new int[SIZE];
	static int[] inyoungCard = new int[SIZE];
	static int score;
	static int winBoundary;
	static int winCnt;
	static int totalRoundCnt;
	static boolean[] visit = new boolean[SIZE];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		T = Integer.parseInt(br.readLine());
		loopTest();
		System.out.println(sb);
	}
	
	static void loopTest() throws IOException {
		
		for(int testCase=1; testCase<=T; testCase++) {
			
			sb.append("#" + testCase + " ");
			inputDatas();
			
			totalRoundCnt = 0;
			winCnt = 0;
			score = 0;
			getResult(0);
			sb.append(winCnt + " " + (totalRoundCnt - winCnt) + "\n");
		}
	}
	
	static void inputDatas() throws IOException {
		
		//인영 카드 추가
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<SIZE; i++) {
			kuyoungCard[i] = Integer.parseInt(st.nextToken());
		}
		
		boolean isSame = false;
		int count = 0;
		for(int i=1; i<=18; i++) {
			
			isSame = false;
			for(int j=0; j<SIZE; j++) {
				if(i == kuyoungCard[j]) {
					isSame = true;
					break;
				}
			}
			if(!isSame) {
				inyoungCard[count] = i;
				count++;
			}
		}
		
		//이기는 값 구하기
		int sum = 0;
		for(int i=1; i<=18; i++) {
			sum += i;
		}
		
		winBoundary = sum/2;
	}
	
	static void getResult(int depth) {
		
		if(depth == SIZE) {
			
			totalRoundCnt++;
			if(score > winBoundary) {
				winCnt++;
			}
			return;
		}
		
		for(int i=0; i<SIZE; i++) {
			
			if(!visit[i]) {
				visit[i] = true;
				//이겼을 경우
				if(kuyoungCard[i] > inyoungCard[depth]) {
					score += kuyoungCard[i] + inyoungCard[depth];
					getResult(depth + 1);
					score -= kuyoungCard[i] + inyoungCard[depth];
					visit[i] = false;
				}
				//졌을 경우
				else {
					getResult(depth + 1);
					visit[i] = false;
				}
			}
		}
	}
}
