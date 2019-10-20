package n과m;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15651_3 {
	static int N, M, pick[];
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());
		sb = new StringBuilder();
		pick = new int[M];
		dupPermu(0);
		System.out.println(sb.toString());
	}
	
	private static void dupPermu(int idx) {
		if(idx==M) {
			for(int i : pick) {
				sb.append(i+" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=1; i<=N; i++) {
			pick[idx]=i;
			dupPermu(idx+1);
		}
			
		
	}

}
