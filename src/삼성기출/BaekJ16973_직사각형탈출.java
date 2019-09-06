package 삼성기출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJ16973_직사각형탈출 {
	
	static int N, M, H, W;
	static int[][] arr;
	static int[] s, e;

	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());
		s = new int[2];
		e = new int[2];
		arr = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(tk.nextToken());
			}
		}
		
		H = Integer.parseInt(tk.nextToken());
		W = Integer.parseInt(tk.nextToken());
		
		s[0] = Integer.parseInt(tk.nextToken());
		s[1] = Integer.parseInt(tk.nextToken());
		
		e[0] = Integer.parseInt(tk.nextToken());
		e[1] = Integer.parseInt(tk.nextToken());
		
		
		
		

	}

}
