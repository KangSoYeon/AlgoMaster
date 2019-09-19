package dp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution3307_최장증가부분수열 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		
		StringTokenizer tk;
		for(int test =1; test<=T; test++) {
			int N = Integer.parseInt(bf.readLine());
			tk = new StringTokenizer(bf.readLine());
			int[] arr = new int[N];
			int answer = 1;
			
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(tk.nextToken());
			}
			
			int[] memo = new int[N];
			memo[0]=1; //첫번째 최장부분 증가수열은 무조건 1  
			int max = 0;
			for(int i=1; i<N; i++) {
				max = 0;
				for(int j=0; j<i; j++) { 
					if(arr[i]>arr[j] && memo[j]>max) { //그 이전까지 값들 중 증가하는 추이이면서, memo값이; 맥스인걸 찾음
						max = memo[j];
					}
				}
				memo[i] = max + 1;
				if(memo[i]>answer)	answer=memo[i];
			}
			
			System.out.println("#"+test+" "+answer);

		}
	}

}
