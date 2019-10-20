package n과m;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_15656_7 {
	static int N, M, pick[], arr[];
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());
		arr = new int[N];
		tk = new StringTokenizer(bf.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(tk.nextToken());
		}
		Arrays.sort(arr);
		sb = new StringBuilder();
		pick = new int[M];
		comb(0, 0);
		System.out.println(sb.toString());
		
	}
	private static void comb(int current, int index) {
		
		if(index==M) {
			for(int i: pick)
				sb.append(i+" ");
			
			sb.append("\n");
			return;
		}
		
		for(int i=current; i<N; i++) {
			pick[index] = arr[i];
			comb(i+1, index+1);
		}
		
		
	}

	
	
	

}
