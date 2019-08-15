package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJ2163_초콜릿자르기 {
	
	static int N, M;
	static int[][] memo;
	
	//a>b라고 가정  
	public static void dp(int a, int b) { //a*b초콜릿을 쪼개는 횟수  
		memo[2][1]=1;
		memo[1][2]=1;
		int min = 10000000;
		for(int i=1; i<=b; i++) { //이게 언제나 작은수  
			for(int j=1; j<=a; j++) { //이게 언제나 큰수  
				if(memo[i][j]==0 && memo[i][j]==0) {
					memo[i][j] = memo[i][j-1] + i; //a*i와 a*(b-i)로 쪼갠거  
					memo[j][i] = memo[i][j];
				}
			}	
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());
		
		if(M>N) {
			memo = new int[M+1][M+1];	
			dp(M,N);
		}else {
			memo = new int[N+1][N+1];
			dp(N,M);
		}
		System.out.println(memo[N][M]);
	//	for(int[] a : memo)
	//		System.out.println(Arrays.toString(a));

	}

}