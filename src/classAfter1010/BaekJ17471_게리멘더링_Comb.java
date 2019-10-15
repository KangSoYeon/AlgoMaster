package classAfter1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJ17471_게리멘더링_Comb {
	static int N, A, B, Min;
	static int[] people;
	static int[][] connect;
	static boolean[] comb, visited;
	static ArrayList<Integer> first, second;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		people = new int[N];
		connect = new int[N][N];
		comb = new boolean[N]; //N만큼의 사람들을 뽑을거 
		visited = new boolean[N];
		Min = 3000;
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		
		for(int i=0; i<N; i++) {
			people[i] = Integer.parseInt(tk.nextToken());
		}
		
		for(int t = 0; t<N; t++) {
			tk = new StringTokenizer(bf.readLine());
			int m = Integer.parseInt(tk.nextToken());
			for(int i=0; i<m; i++) {
				int p = Integer.parseInt(tk.nextToken());
				connect[t][p-1] = connect[p-1][t] = 1;
			}
		}
				
		//combination으로 1개부터~ N-1개까지 뽑기
		for(int i=1; i<=N/2; i++) {
			combination(0, 0, i);
		}
		
		if(Min!=3000) System.out.println(Min);
		else System.out.println(-1);
	}
	
	static void combination(int current, int index, int pick) { //pick만큼 뽑을 때까지 
		
		if(index==pick) { //pick만큼 뽑았음 
			//뽑은 배열로 비교 돌리기 
			compare();
			System.out.println(Arrays.toString(comb));
			return;
		}
		
		for(int i=current; i<N; i++) {
			comb[i] = true;
			combination(i+1, index+1, pick);
			comb[i] = false;
		}
		
	}

	private static void compare() {
		first = new ArrayList<>();
		second = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			if(comb[i]) first.add(i);
			else second.add(i);
		}
		
		A=0;
		visited = new boolean[N];
		dfs(first, first.get(0), true);
		for(int i=0; i<first.size(); i++) {
			if(!visited[first.get(i)]) { //하나라도 방문 안한게 있으면 함수 끝내기 
				return;
			}
		}
		
		B=0;
		visited = new boolean[N];
		dfs(second, second.get(0), false);
		for(int i=0; i<second.size(); i++) {
			if(!visited[second.get(i)]) {
				return;
			}
		}
		
		if(Min>Math.abs(A-B)) {
			Min = Math.abs(A-B);
		}
		
		
	}
	
	private static void dfs(ArrayList<Integer> arr, int x, boolean flag) {
		visited[x]=true;
		if(flag) { //true면 A그룹 인원수 구하기
			A+=people[x];
		}else { //false면 B그룹 인원수 구하기
			B+=people[x];
		}
		
		for(int i=0; i<N; i++) {
			if(arr.contains(i) && !visited[i] && connect[i][x]==1) { //연결됐을때만 가기
				visited[i] = true;
				dfs(arr, i, flag);
			}
		}
		
	}

}
