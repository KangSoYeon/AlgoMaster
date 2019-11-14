package class1114;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJ17069_파이프옮기기2 {
	static int N, arr[][], count;
	static long[][] dp;
	static int dir[][] = {{1,0}, {1,1}, {0,1}};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk;
		N = Integer.parseInt(bf.readLine());
		arr = new int[N][N];
		dp = new long[N][N];
		for(int i=0; i<N; i++) {
			tk = new StringTokenizer(bf.readLine());

			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(tk.nextToken());
			}
		}
		//파이프는 0,0/ 0,1 차지
		//가로, 세로, 대각선 구분해서
		//가로면 이동 0, (1: 이동할떄 세방향 다 비어있는지 봐야함)
		//세로면 이동 1, (2: 이동할떄 세방향 다 비어있는지 봐야함)
		//대각선이면 이동 0,1,(2: 이동할떄 세방향 다 비어있는지 봐야함)
		
		for(int i=0; i<N; i++) {
			dp[0][i] = 1;
			dp[i][2] = 1;
		}
		
		for(int i=0; i<N; i++) {
			dp[1][i] = i-1;
			dp[i][3] = i+1;
		}
		dp[0][0] = dp[0][1] = dp[1][0] = dp[1][1]= 0;
		
		
		for(int i=2; i<N; i++) {
			for(int j=4; j<N; j++) {
				dp[i][j] = dp[i-1][j] + dp[i][j-1] + dp[i-1][j-1];
			}
		}
		
		
		for(int i=0; i<N; i++) {
			System.out.println(Arrays.toString(dp[i]));
			
		}
		
		
		
		
	}

	private static boolean check(int x, int y) {
		for(int i=0; i<dir.length; i++) {
			int nx = x + dir[i][0];
			int ny = y + dir[i][1];
			
			if(nx>=N || ny>=N || arr[nx][ny]==1) return false;
			
		}
		return true;
	}
	
}
