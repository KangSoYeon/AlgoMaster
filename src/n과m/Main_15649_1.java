package n과m;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15649_1 {
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());
		pick = new int[M];
		visited = new boolean[N+1];
		//NCM
		nPm(0);
	}
	static int[] pick;
	static boolean[] visited;
	static void nPm(int index) {
		if(index==M) {
			for(int i:pick)
				System.out.print(i+" ");
			
			System.out.println();
			return;
		}
		
		for(int i=1; i<=N; i++) {
			if(visited[i]) continue;
			
			visited[i] = true;
			pick[index] = i;
			nPm(index+1);
			visited[i] = false;
			
		}
		
	}
	

}
