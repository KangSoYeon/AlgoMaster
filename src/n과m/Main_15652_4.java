package n과m;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15652_4 {
	static int N, M, pick[];
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());
		sb = new StringBuilder();
		pick = new int[M];
		rec(1, 0);
		System.out.println(sb.toString());
		
	}
	private static void rec(int current, int index) {
		if(index==M) {
			for(int i: pick)
				sb.append(i+" ");
			
			sb.append("\n");
			return;
		}
		
		for(int i=current; i<=N; i++) {
			pick[index] = i;
			rec(i,index+1); //다음꺼 말고 현재꺼까지뽑는 순열 
		}
			
	}
	
	
	

}
