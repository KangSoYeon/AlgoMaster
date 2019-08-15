package dp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJ14916_거스름돈 {
	
	static int N;
	static int[] memo; //memo안의 값을 거슬러줄때의 최소값 저장하는 배열
	static int[] coin={2,5};
	
	public static void dp(int change) {
		memo[0]=0;
		for(int i=1; i<=change; i++) {
			boolean f = false;
			int min = 100001;
			for(int j=0; j<coin.length; j++) {
				if(i>=coin[j] && memo[i-coin[j]]!=-1) { //해당 코인 쓸수 있고, -1값이 아니면
					if(min>memo[i-coin[j]])	min = memo[i-coin[j]];	
					f=true; //거슬러줄수 있는 동전이 없으면
				}
			}
			if(f==true)	memo[i] = min+1;
			else	memo[i] = -1;
		}
	}
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		memo = new int[N+1];
			
		dp(N);
		System.out.println(memo[N]);
	}

}
