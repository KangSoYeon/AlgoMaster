package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek9095_123더하기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		
		for(int test = 0; test<T; test++) {
			int N = Integer.parseInt(bf.readLine());
			int[] memo = new int[N+4]; //1,2,3만큼은 확보하기  
			memo[1]=1;
			memo[2]=2;
			memo[3]=4;
			
			if(N>=4) {
				for(int i=4; i<=N; i++) {
					memo[i] = memo[i-1]+memo[i-2]+memo[i-3];
				}
			}
			
			System.out.println(memo[N]);
			
		}
		
	}

}
