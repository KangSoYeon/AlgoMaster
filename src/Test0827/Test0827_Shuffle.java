package Test0827;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test0827_Shuffle {
	static int N;
	static int[] arr, left, right;
	
	public static int[] shuffle(int[] cards, int x) { 
		//a번 셔플로 셔플하고 다시 카드를 반환한다.
		int[] answer = new int[cards.length];
		int half = N/2;
		int idx=0;
		int r=half-1;
		int l=half-1;
		int turn = 0;
		if(x>=N/2) { //왼쪽카드부터 쌓는다
			turn = N-x-1;
			
			
			
			//N-X-1 값으로 똑같이
			//0 왼쪽카드부터 다놓고 오른쪽 놓기
			//1 왼쪽카드가 하나남았을 떄 오른쪽 카드 놓기
			//2 왼쪽카드가 두개 남았을 때 오른쪽 카드 놓기 
		}else { //오른쪽 카드부터 쌓는다.
			//x값에 따라 
			//0 오른쪽 카드부터 다놓고 왼쪽 놓기
			//1 오른쪽 카드가 하나 남았을 때 왼쪽카드 놓기
			//2 오른쪽 카드가 두개 남았을 때 왼쪽카드 놓기
			turn = x;
		
			
			while(turn!=0) {
				while(true) {
					answer[idx++] = right[r--];
					if(r==half-x) {
						break;
					}
				}
				turn--;
				
				while(true) {
					answer[idx++] = left[l--];
					if(l==x) {
						break;
					}
				}
				
			}
			
			
		}
		
		
		return answer;
	
	}
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk;
		int T = Integer.parseInt(bf.readLine());
		
		for(int test=1; test<=T; test++) {
			int answer=0;
			
			N = Integer.parseInt(bf.readLine());
			arr = new int[N];
			tk = new StringTokenizer(bf.readLine());
			
			for(int i=0; i<N; i++) {
				arr[i]=Integer.parseInt(tk.nextToken());
			}
			
			int half = N/2; //6장의 카드 들어왔을 때 3
			
			left = new int[half];
			right = new int[half];
			
			for(int i=0; i < half; i++) {
				left[i]= arr[i];
			}
			
			for(int i=half; i < N; i++) {
				right[i-half]= arr[i];
			}
			

			
			System.out.println("#"+test+" "+ answer);
		}

	}

}
