
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1244_S4 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int switchCnt;
	static int[] switchs;
	static int studentNum;
	static int[] students;
	static int gender, switchNum;

	public static void main(String[] args) throws IOException {
		
		inputDatas();
		getResult();
		System.out.println(sb);
		
	}
	
	static void inputDatas() throws IOException {
		
		switchCnt = Integer.parseInt(br.readLine());
		switchs = new int[switchCnt];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<switchCnt; i++) {
			switchs[i] = Integer.parseInt(st.nextToken());
		}
		
		studentNum = Integer.parseInt(br.readLine());
	}
	
	static void getResult() throws IOException {
		
		for(int i=0; i<studentNum; i++) {
			
			st = new StringTokenizer(br.readLine());
			gender = Integer.parseInt(st.nextToken());
			switchNum = Integer.parseInt(st.nextToken());
			
			switch(gender) {
			case 1:
				boyChange();
				break;
			case 2:
				girlChange();
				break;
			}
		}
		
		for(int i=0; i<switchCnt; i++) {
			sb.append(switchs[i] + " ");
			if((i+1) % 20 == 0) {
				sb.append("\n");
			}
		}
	}
	
	static void boyChange() {
		
		int mul = 1;
		for(int i=0; i<switchCnt; i++) {
			if(i+1 == switchNum*mul) {
				if(switchs[i] == 1) {
					switchs[i] = 0;
				}
				else {
					switchs[i] = 1;
				}
				mul++;
			}
		}
	}
	
	static void girlChange() {
		
		int tempCnt = 1;
		if(switchs[switchNum-1] == 1) {
			switchs[switchNum-1] = 0;
		}
		else {
			switchs[switchNum-1] = 1;
		}
		
		while(true) {
			
			if((switchNum - 1 - tempCnt >= 0) && (switchNum - 1 + tempCnt < switchCnt)) {
				if(switchs[switchNum - 1 - tempCnt] == switchs[switchNum - 1 + tempCnt]) {
					if(switchs[switchNum - 1 - tempCnt] == 0) {
						switchs[switchNum - 1 - tempCnt] = 1;
						switchs[switchNum - 1 + tempCnt] = 1;
					}
					else {
						switchs[switchNum - 1 - tempCnt] = 0;
						switchs[switchNum - 1 + tempCnt] = 0;
					}
				}
				
				else {
					break;
				}
			}
			else {
				break;
			}
			
			tempCnt++;
		}
	}
}
