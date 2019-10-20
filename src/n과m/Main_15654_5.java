package n과m;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_15654_5 {
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
		check = new boolean[N];
		pick = new int[M];
		permu(0);
		System.out.println(sb.toString());
		
	}
	static boolean[] check;
	static void permu(int idx) {
		if(idx==M) {
			for(int i: pick)
				sb.append(i+" ");
			
			sb.append("\n");
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(check[i]) continue;
			
			pick[idx] = arr[i];
			check[i] = true;
			permu(idx+1);
			check[i] = false;
		}
		
		
	}
	
	

}
