package n과m;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15650_2 {
	static int N, M, pick[];

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());
		pick = new int[M];
		nCm(1,0);
	}

	private static void nCm(int current, int index) {
		if(index==M) {
			for(int i: pick)
				System.out.print(i+" ");
			
			System.out.println();
			return;
		}
		
		for(int i=current; i<=N; i++) {
			pick[index] = i;
			nCm(i+1, index+1);
		}
		
	}
}
