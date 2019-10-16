package classAfter1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJ17471_게리멘더링_np {
	static int N, A, B, Min;
	static int people[], connect[][], arr[];
	static boolean[] visited;
	static ArrayList<Integer> first, second;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		people = new int[N];
		connect = new int[N][N];
		arr = new int[N]; //N만큼의 사람들을 뽑을거 
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
		for(int i=1; i<N; i++) {	//0001, 0011, 0111 --> 순서대로 넣어서 넥퍼 돌리기 
			arr = new int[N];
			for(int j=0; j<i; j++) {
				arr[N-1-j]= 1; //뒤에서부터 뽑을사람 =1 만큼을 선언
			}
			
			do {
				compare();
			}while(next_permu());
		}
		
		if(Min!=3000) System.out.println(Min);
		else System.out.println(-1);
	}
	
	

	private static void compare() {
		first = new ArrayList<>();
		second = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			if(arr[i]==1) first.add(i);
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
	
	private static boolean next_permu() {
		int i=N-1;
		while(i>0 && arr[i]<=arr[i-1]) i--;
		
		if(i==0) return false;
		
		int j=N-1;
		while(arr[j]<=arr[i-1]) j--;
		
		swap(i-1, j);
		
		j=N-1;
		while(i<j) swap(i++, j--);
		return true;
		
	}


	private static void swap(int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		
	}
	

}
