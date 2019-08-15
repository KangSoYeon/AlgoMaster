package dp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP인강_동전바꿔주기 {
	
	static int T, K;
	static int[][] arr;
	static int[] memo, temp; //memo안의 값을 거슬러줄때의 최소값 저장하는 배열
	
	/*
//만들어야하는 가격, 들어오는 동전 종류, 각 동전의 값과,갯수 : 출력값 최소로 거슬러주는 동전 갯수
20
3
1 5
5 2
10 2
	 */
	public static void dp(int change) {
		memo[0]=0;
		for(int i=1; i<=change; i++) {
			int min = 100000000;
			for(int j=0; j<K; j++) { //동전 갯수 초기화 
				arr[j][1]=temp[j];
			}
			
			for(int j=0; j<K; j++) { //동전들을 모두 검사 
				if(i>=arr[j][0] && arr[j][1]>0) { //현재 거슬러줄 돈이 coin보다 크면
					if(memo[i-arr[j][0]]<min) {	//들어갈수 있는 동전들 가운데 가장 작은 값을 min으로 업데이트
						min = memo[i-arr[j][0]]; //이동전 사용한거니까 
						arr[j][1]--;
					}
				}
			}
			
			memo[i] = min + 1;
		}
	}
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(bf.readLine());
		K = Integer.parseInt(bf.readLine());
		StringTokenizer tk;
		arr = new int[K][2];
		memo = new int[T+1];
		temp = new int[K];
		
		for(int i=0; i<K; i++) {
			tk = new StringTokenizer(bf.readLine());
			arr[i][0] = Integer.parseInt(tk.nextToken()); //금액  
			arr[i][1] = Integer.parseInt(tk.nextToken()); //갯수 
			temp[i] = arr[i][1];
			
		}
		
		dp(T);
		//for(int a : memo)
		//	System.out.print(a+" ");
		
		System.out.println(memo[T]);
		
	}

}
