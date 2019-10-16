package classAfter1010;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B17472_다리만들기2_김태희 {

	static int C, R ,cnt;
	static int[][] map,adj;
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		
		for (int i = 0 ; i < R; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//  섬찾기
		cnt=0;
		for (int i = 0; i < R; ++i) {
			for (int j = 0; j < C; ++j) {
				if(map[i][j]==1) {
					cnt++;
					dfs(i,j);
				}
			}
		}
		//인접행렬 만들기
		makeAdjMatrix();
		System.out.println(makeMST());
		
	}

	private static void dfs(int x,int y) {
		map[x][y] = cnt+1;
		
		int nx=0,ny=0;
		for (int d = 0; d < 4; ++d) {
			nx = x+dx[d];
			ny = y+dy[d];
			if(nx>=0 && nx<R && ny>=0 && ny<C && map[nx][ny]==1) {
				dfs(nx,ny);
			}
		}
	}	
	private static void makeAdjMatrix() {
		int cur,other,nx,ny,k,temp;
		adj = new int[cnt][cnt];
		for (int x = 0; x < R; ++x) {
			for (int y = 0; y < C; ++y) { //2차원배열 완탐.
				if(map[x][y]>0) { // 섬이면
					cur = map[x][y];
					for (int d = 0; d < 4; ++d) { // 현재 섬 주변 4방 탐색
						k = 0;
						nx = x;
						ny = y;
						while(true) {
							nx += dx[d]; 
							ny += dy[d];
							if(nx<0 || nx>=R || ny<0 || ny>=C || map[nx][ny] == cur) {
								break; // 다음방향
							}else if(map[nx][ny]<1) { //  바다면
								k++;
							}else { // 다른섬을 만났다면
								if(k>1) { // 다리길이가 1보다 크면
									other = map[nx][ny];
									temp = adj[cur-2][other-2];
									if(temp==0 || temp>k) {
										adj[cur-2][other-2] = k;										
									}
								}
								break;
							}
							
						}

					}
				}
			}
		}
	}

	static class Vertex implements Comparable<Vertex>{
		int v;
		int w;
		public Vertex(int v, int w) {
			this.v = v;
			this.w = w;
		}
		@Override
		public int compareTo(Vertex o) {
			return this.w - o.w;
		}
		@Override
		public String toString() {
			return "Vertex [v=" + v + ", w=" + w + "]";
		}
		
	}

	private static int makeMST() {
		boolean[] visited = new boolean[cnt];
		int[] distance = new int[cnt];
		Arrays.fill(distance, Integer.MAX_VALUE);
		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
		
		distance[0] = 0;
		queue.offer(new Vertex(0, 0));
		
		int k = 0,result = 0;
		Vertex cur = null;
		while(!queue.isEmpty()) {
			cur = queue.poll();
			if(visited[cur.v]) continue;
			
			visited[cur.v] = true;
			result += cur.w;
			if(++k==cnt) break;
			
			for (int j = 0; j < cnt; ++j) {
				if(!visited[j] && adj[cur.v][j]>0 && distance[j]>adj[cur.v][j]) {
					distance[j] = adj[cur.v][j];
					queue.offer(new Vertex(j, adj[cur.v][j]));
				}
			}
			
		}
		return result>0&&k==cnt?result:-1;
	}

}












