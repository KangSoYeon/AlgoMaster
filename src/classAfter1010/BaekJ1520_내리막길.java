package classAfter1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJ1520_내리막길 {
	
	static int N, M;
	static int[][] arr;
	static int[][] dp; //해당 좌표까지 올수 있는 경우의 수들
	
	static int dfs(int x, int y) {
		
		if(x==N-1 && y==M-1) return dp[x][y]=1; //끝까지 한번 온 경로 하나
		
		if(dp[x][y]!= -1) return dp[x][y]; //이미 구했으니까 여기서 리턴 
		
		int ret = 0;
		//왼쪽에서 오는거
		if(y-1>=0 && arr[x][y]>arr[x][y-1]) { //현재좌표에서 다음 좌표로 넘어갈 수 있으면 
			ret += dfs(x, y-1);
		}
		
		//오른쪽에서 오는거
		if(y+1<M && arr[x][y]>arr[x][y+1]) {
			ret += dfs(x, y+1);
		}
		
		//위쪽에서 오는거
		if(x-1>=0 && arr[x][y]>arr[x-1][y]) {
			ret += dfs(x-1, y);
		}
		
		//아래쪽에서 오는거
		if(x+1<N && arr[x][y] >arr[x+1][y]) {
			ret += dfs(x+1, y);
		}
		
		return dp[x][y] = ret;
	}
	

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());
		arr= new int[N][M];
		dp = new int[N][M];
				
		for(int i=0; i<N; i++) {
			tk = new StringTokenizer(bf.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(tk.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				dp[i][j] = -1;
			}
		}
		
		int answer = dfs(0,0);
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(dp[i][j] +" ");
			}System.out.println();
		}
		System.out.println(answer);
				
	}
}
