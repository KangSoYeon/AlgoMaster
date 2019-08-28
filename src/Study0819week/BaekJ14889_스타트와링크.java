package Study0819week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJ14889_스타트와링크 {
	
	//N명중에 N/2명을 뽑는 조합
	static int N, min;
	static int[][] arr;
	static int[] start, link;
	
	public static void comb(int idx, int tIdx) {
		if(tIdx==N/2) { //N/2뽑기 
			//start팀 다꾸러졌음 남은 팀으로 link팀 꾸리면 됨
			int s = 0;
			int l = 0;
			for(int i=0; i<(N/2)-1; i++) {
				for(int j=i+1; j<N/2; j++) {
					s+=arr[start[i]-1][start[j]-1];
					s+=arr[start[j]-1][start[i]-1];
				}
			}
			
			//남은팀 링크에 넣기 
			boolean flag = false;
			int idx2=0;
			for(int i=1; i<=N; i++) {
				flag=false;
				for(int j=0; j<start.length; j++) {
					if(start[j]==i) {
						flag = true;
					}
				}
				if(!flag) {
					link[idx2++]=i;
				}
			}
			
			for(int i=0; i<(N/2)-1; i++) {
				for(int j=i+1; j<N/2; j++) {
					l+=arr[link[i]-1][link[j]-1];
					l+=arr[link[j]-1][link[i]-1];
				}
			}
						
			
			if(min>Math.abs(s-l)) min = Math.abs(s-l);
			return;
		}
		
		if(idx<=N) { //N개중에
			start[tIdx] = idx;
			comb(idx+1, tIdx+1); //해당꺼 선택
			comb(idx+1, tIdx); //해당꺼 안선택
		}		
	}
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		StringTokenizer tk;
		arr = new int[N][N];
		start = new int[N/2];
		link = new int[N/2];
		for(int i=0; i<N; i++) {
			tk = new StringTokenizer(bf.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j]=Integer.parseInt(tk.nextToken());
			}
		}
		
		min=100000;
		
		comb(1, 0);
		
		System.out.println(min);
		
	}

}
