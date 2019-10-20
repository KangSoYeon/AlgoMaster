package classAfter1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BaekJ17472_다리만들기2_프림2 {
	static int N, M;
	static int[][] arr, check;
	static int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}};

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
	
		//만들어진 인접행렬로 prim 돌리기
		long answer =0;
		boolean[] check = new boolean[count-1]; //방문했는지 안했는지 체크, 모든 정점 방문했는지 체크

		ArrayList<Integer> been = new ArrayList<>();
		been.add(0);
		check[0]= true;
		
		int idx = 0;
		//안간 노드수 만큼 반복 돌리는데, 
		for(int node=0; node<count-2; node++) { //한노드는 갔으니까 count-2만큼 돌리기 
			int min = Integer.MAX_VALUE;
			for(int i=0; i<been.size(); i++) { //이미 방문한 노드에서 
				int cur = been.get(i);
				for(int j=0; j<count-1; j++) { //모든 노드를 검사하면서
					if(connect[cur][j]!=150 && !check[j] && connect[cur][j]<min ) { //연결되어있고, 지금 노드랑 다른 노드이면서, 안방문한 노드에 대해
						min = connect[cur][j];
						idx = j; //다음에 방문한 노드  j기억
					}
				}
			}
			if(!been.contains(idx)) { //방문 한적 없는 노드만 넣기 
				been.add(idx); //노드 하나씩 더해짐 --> 모든 노드 다볼때까지 반복 
				check[idx]= true;
				answer += min; //간선의 길이만큼 계속 더해주기 
			}
			//이거 했는데 아직도 안간 노드 있으면 
		}
	
		//if(answer>999999) System.out.println(-1); //만약 하나라도 연결안되는 섬이 있으면 Maxvalue를 한번이라도 더함 
		//else System.out.println(answer);
		
		if(been.size()<count-1) System.out.println(-1);
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
