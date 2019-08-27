package Study0819week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJ14889_스타트와링크 {
	
	//N명중에 N/2명을 뽑는 조합
	static int N;
	static int[][] arr;
	static int[] start;
	
	public static void comb(int idx, int tIdx) {
		if(tIdx==N/2) {
			//start팀 다꾸러졌음 남은 팀으로 link팀 꾸리면 됨
			int s = 0;
			int l = 0;
			for(int i=0; i<(N/2)-1; i++) {
				for(int j=i+1; j<N/2; j++) {
					s+=arr[start[i]][start[j]];
					s+=arr[start[j]][start[i]];
				}
			}
			
			for(int i=1; i<=N; i++) {
				
			}
			
			
			
			return;
		}
		
		start[tIdx] = idx;
		comb(idx+1, tIdx+1); //해당꺼 선택
		comb(idx+1, tIdx); //해당꺼 안선택
		
	}
	
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		StringTokenizer tk;
		arr = new int[N+1][N+1];
		start = new int[N/2];
		for(int i=0; i<N; i++) {
			tk = new StringTokenizer(bf.readLine());
			for(int j=1; j<=N; j++) {
				arr[i][j]=Integer.parseInt(tk.nextToken());
			}
		}
		
		
		comb(1, 0);
		
		
		
		
	}

}
