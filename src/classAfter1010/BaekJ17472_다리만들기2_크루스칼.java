package classAfter1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BaekJ17472_다리만들기2_크루스칼 {
	static int N, M;
	static int[][] arr, check;
	static int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}};
	
	static int parent[]; // i번째 노드의 루트 노드를 저장하고 있는 변수
	
	static void make() {
		Arrays.fill(parent, -1);
	}
	static int find(int a) {
		if(parent[a]<0) return a;
		return parent[a] = find(parent[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		parent[bRoot] = aRoot;
		return true;
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());
		arr = new int[N][M];
		check = new int[N][M];
		
		for(int i=0; i<N; i++) {
			tk = new StringTokenizer(bf.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(tk.nextToken());
			}
		}
		
		//각 섬 다른 수로 색칠하기
		int count = 1;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(arr[i][j]==1 && check[i][j]==0) { //아직 안간데면 
					dfs(i, j, count++);
				}
			}
		}
		
		//각 섬별 연결 행렬 만들기
		int[][] connect = new int[count-1][count-1];
		parent = new int[count-1];
		make();
		
		for(int i=0; i<count-1; i++) {
			for(int j=0; j<count-1; j++) {
				connect[i][j] = 150; //최대값으로 connect값 초기화 
			}
		}

		
		//섬 모두 탐색하면서 연결 검사 --> 섬끼리 인접리스트 만들기 
		int nx, ny;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(check[i][j]!=0) { //섬을 찾으면
					int other = check[i][j];
					for(int d=0; d<dir.length; d++) {
						nx = i;
						ny = j;
						int distance = 0;
						while(true) { //이 방향으로 계속 갔을 때 
							nx += dir[d][0];
							ny += dir[d][1];
							
							if(nx<0 || ny<0 || nx>=N || ny>=M || check[nx][ny]==other) break; //이방향으로 더이상 갈수 없으면 
													
							int now = check[nx][ny];
							if(now!=0 && now!=other) { //새로운 섬 찾으면
								if(distance>=2) { //2가 안넘으면 연결해도 안되고, 더 탐색해도 안됨!!!!
									if(connect[now-1][other-1] > distance) { //원래 있던 값보다 distance가 작으면 
										connect[now-1][other-1] = distance;
									}
								}
								break; //섬과 섬 연결했으면 그만가기, 2보다 거리가 작은데 다른섬 만난경우도 나가기 !!!!1
							}
							else if(now==0){ // 물일때만 
								distance++;
							}
						}
					}
				}
			}
		}
		
			
		ArrayList<int[]> list = new ArrayList<>();
		for(int i=0; i<count-1; i++) {
			for(int j=0; j<count-1; j++) {
				if(connect[i][j]!=150) {
					list.add(new int[] {i, j, connect[i][j]}); //인접한 x,y,그 무게 
				}
			}
		}
		
		Collections.sort(list, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2]-o2[2]; //작은 순서대로 정렬
			}
		});
		
		int answer =0;
		int cnt=0;
		for(int i=0; i<list.size(); i++) {
			int[] a = list.get(i);
			
			if(union(a[0], a[1])) {
				answer += a[2];
				cnt++; //연결하는 걸 전체 간선 수 보다 1만큼 작게돌면 답 
			}
		}
		
		if(cnt!=count-2) System.out.println("-1");
		else System.out.println(answer);

	}

	private static void dfs(int x,int y, int k) {
		check[x][y] = k;
		for(int d=0; d<4; d++) {
			int nx = x + dir[d][0];
			int ny = y + dir[d][1];
			
			if(nx>=0 && ny>=0 && nx<N && ny<M
					&& arr[nx][ny]==1 && check[nx][ny]==0) {
				check[nx][ny]=k;
				dfs(nx,ny, k);
			}
		}
	}
}
