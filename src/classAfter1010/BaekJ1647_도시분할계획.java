package classAfter1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class BaekJ1647_도시분할계획 {

	static int N, M, arr[][], pick[], Acount, Bcount, answer;
	static ArrayList<Integer> A, B;
	static boolean[] check;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tk.nextToken()); //home
		M = Integer.parseInt(tk.nextToken()); //road
		//집과 집을 연결하는 인접행렬 만들기  
		arr = new int[N][N];
		
		for(int i=0; i<M; i++) {
			tk = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(tk.nextToken())-1;
			int b = Integer.parseInt(tk.nextToken())-1;
			arr[a][b] = arr[b][a] = Integer.parseInt(tk.nextToken()); //집과집을 연결하는다리 유지
		}
		
		//그룹을 두그룹으로 나누고
		//각각을 prim으로 돌리기
		//가능한 경우에만-> 세운 다리유지비 비교, 최소값 구하기
		
/*		for(int[] i : arr) {
			for(int j: i) {
				System.out.print(j+" ");
			}
			System.out.println();
		}*/
		answer = Integer.MAX_VALUE;
		for(int times=1; times<=N/2; times++) {
			pick = new int[N]; //0과 1로 나눌 
			for(int j=N-1; j>=times; j--) {
				pick[j] = 1;
			}
			
			do {
				divide();
				System.out.println(Arrays.toString(pick));
				
			}while(next_permu());
		}
		
		System.out.println(answer);
		
	}	


	private static void divide() {
		A = new ArrayList<>();
		B = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			if(pick[i]==1) {
				A.add(i);
			}else {
				B.add(i);
			}
		}
/*		//dfs로 A그룹끼리 연결돼있나 확인하고
		//bfs로 B그룹끼리 연결돼있나 확인하
		check = new boolean[N];
		dfs(A.get(0));
		if(Acount < A.size()) {
			return; //함수종료  
		}
		
		
		check = new boolean[N];
		bfs();
		if(Bcount < B.size()) {
			return; 
		}
		*/
		//둘다 연결되어 있으면 prim돌려서 최소 스패닝 트리 찾기
		ArrayList<Integer> been ;
		int min = Integer.MAX_VALUE;
		int idx = 200000;
		int road = 0;
		int size = A.size();
		if(size!=1) { //A에 두개이상 원소가 있을 때만 mst돌리기 
		
			check = new boolean[N];
			been = new ArrayList<>();
			int a = A.get(0);
			
			check[a]=true;
			been.add(a);
			min = Integer.MAX_VALUE;
			idx = 200000; //갈리 없는 index로 초기화 해야함   
			
			for(int t=0; t<size-1; t++) { //전체 노드수 -1만큼 반
				min = Integer.MAX_VALUE;
				//idx = 0;
				for(int i : been) { //이미 방문한 모든 노드 돌면서  
					for(int j: A) { //해당노드에서 아직 안간 간선들의 최소값 구하기   
						if(arr[i][j]!=0 && arr[i][j]<min && !check[j]) {
							min = arr[i][j];
							idx = j;
						}
					}
				}
				if(!been.contains(idx) && idx!=200000) { //idx값이 변했으면  
					been.add(idx);
					check[idx] = true;
					road += min; //총정답길이늘리기 
				}
			}
			
			if(been.size()< size) {
				return; //모두 연결 안되는 경우 끝내기 
			}
		}
		size = B.size();
		if(size!=1) { //A에 두개이상 원소가 있을 때만 mst돌리기 
			check = new boolean[N];
			been = new ArrayList<>();
			int b = B.get(0);
			check[b]=true;
			been.add(b);
			min = Integer.MAX_VALUE;
			idx = 200000;
			for(int t=0; t<size-1; t++) { //전체 노드수 -1만큼 반
				min = Integer.MAX_VALUE;
				//idx = 0;
				for(int i : been) { //이미 방문한 모든 노드 돌면서  
					for(int j: B) { //해당노드에서 아직 안간 간선들의 최소값 구하기   
						if(arr[i][j]!=0 && arr[i][j]<min && !check[j]) {
							min = arr[i][j];
							idx = j;
						}
					}
				}
				if(!been.contains(idx) && idx!=200000) {
					been.add(idx);
					check[idx] = true;
					road += min; //총정답길이늘리기 
				}
				
			}
			
			if(been.size()< size) {
				return; //모두 연결 안되는 경우 끝내기 
			}
		}
		
		//둘다 연결이 되고, 그중 최소 연결되는 간선 길이 road구했으면 

		if(road<answer) {
			answer = road;
		}
		
	}
/*	private static void bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(B.get(0));
		check[B.get(0)]=true;
		
		while(!q.isEmpty()) {
			int n = q.poll();
			Bcount++; //꺼내는 횟수만큼 방문한거니까 
			for(int i: B) {
				if(arr[i][n]!=0 && !check[n]) {
					q.offer(i);
					check[i] = true;
				}
			}
			
		}
		
	}

	private static void dfs(Integer x) {
		check[x]= true;
		Acount++;

		for(int i : A) {
			if(arr[i][x]!=0 && !check[i]) {
				check[i] = true;
				dfs(i);
			}
		}
				
		
	}*/

	static boolean next_permu() {
		int i = N-1;
		while(i>0 && pick[i-1]>=pick[i]) i--;
		
		if(i==0) return false;
		
		int j = N-1;
		while(pick[j] <= pick[i-1]) j--;
		
		swap(i-1, j);
		
		j = N-1;
		while(j>i) swap(i++, j--);
		
		return true;
		
	}

	private static void swap(int i, int j) {
		int temp = pick[i];
		pick[i] = pick[j];
		pick[j] = temp;
		
	}

}
