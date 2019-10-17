package Test0827;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Test0827_사각형칠하기 { //class명 Solution
	
	static int N, M, K;
	static int[][] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		StringTokenizer tk;
		for(int test =1 ; test<=T; test++) {
			

			
			tk = new StringTokenizer(bf.readLine());
			N = Integer.parseInt(tk.nextToken());
			M = Integer.parseInt(tk.nextToken());
			K = Integer.parseInt(tk.nextToken());
			
			arr = new int[N][M];
			int[] color = new int[22];
			for(int i=0; i<K; i++) {
				tk = new StringTokenizer(bf.readLine());
				int sx = Integer.parseInt(tk.nextToken());
				int sy = Integer.parseInt(tk.nextToken());
				
				int ex = Integer.parseInt(tk.nextToken());
				int ey = Integer.parseInt(tk.nextToken());
				
				int c = Integer.parseInt(tk.nextToken());
				
				boolean flag = false;
				for(int j=sx; j<=ex; j++) {
					for(int k=sy; k<=ey; k++) {
						if(arr[j][k]>c) {
							flag = true;
						}
					}
				}
				
				if(flag==false) {
					for(int j=sx; j<=ex; j++) {
						for(int k=sy; k<=ey; k++) {
							arr[j][k]=c;
						}
					}
				}
			}
			
		
			for(int i=0; i<N; i++) { //각 색칠된 영역의 넓이 
				for(int j=0; j<M; j++) {
					color[arr[i][j]]++;
				}
			}
		
			int max=0;
			for(int i=0; i<color.length; i++) {
				if(max<=color[i]) {
					max = color[i];
				}
			}
			
			System.out.println("#"+test+" "+max);
		}
		
		
	}

}
