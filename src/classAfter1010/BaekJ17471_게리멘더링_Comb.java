package classAfter1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJ17471_게리멘더링_Comb {
	static int N;
	static int[] people;
	static int[][] connect;
	static boolean[] comb;
	static ArrayList<Integer> first, second;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		people = new int[N];
		connect = new int[N][N];
		comb = new boolean[N]; //N만큼의 사람들을 뽑을거 
		
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		
		for(int i=0; i<N; i++) {
			people[i] = Integer.parseInt(tk.nextToken());
		}
		
		for(int t= 0; t<N; t++) {
			tk = new StringTokenizer(bf.readLine());
			int m = Integer.parseInt(tk.nextToken());
			for(int i=0; i<m; i++) {
				int p = Integer.parseInt(tk.nextToken());
				connect[t][p] = connect[p][t] = 1;
			}
		}
		
		//combination으로 1개부터~ N-1개까지 뽑기
		for(int i=1; i<=N/2; i++) {
			combination(0, 0, i);
		}
		
		
		
	

	}
	
	static void combination(int n, int r, int pick) { //pick만큼 뽑을 때까지 
		
		if(r==pick) {
			//뽑은 배열로 비교 돌리기 
			compare();
			return;
		}
		
		
		if(n<N) {
			comb[r]= true;
			combination(n+1, r+1, pick);
			combination(n+1, r, pick);
		}
		
	}

	private static void compare() {
		first = new ArrayList<>();
		second = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			if(comb[i]) first.add(i);
			else second.add(i);
		}
		
		dfs(i, j);
		
		
	}
	
	

}
