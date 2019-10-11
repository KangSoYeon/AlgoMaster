package classAfter1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
/*


8 8
0 0 0 0 0 1 0 1
0 1 0 1 0 1 1 0
1 1 1 1 1 1 0 0
0 0 1 0 0 1 0 0
1 1 0 0 0 1 1 0
0 0 0 1 1 1 1 1
0 1 1 0 0 1 1 0
0 0 1 0 1 0 0 1
output: 11
correct answer: -1

10 5
0 1 0 0 0
1 1 1 0 0
1 0 1 1 1
0 1 1 1 0
1 0 0 0 1
1 1 1 0 1
0 0 0 0 1
0 0 0 1 0
0 0 0 1 1
0 0 0 0 0
output: 6
correct answer: -1

10 6
0 0 0 1 0 0
0 0 0 1 0 0
0 1 0 0 0 1
0 0 0 0 0 0
1 1 0 1 1 0
1 0 0 0 1 0
1 1 0 0 1 0
0 0 0 0 1 1
0 0 0 0 0 0
0 1 0 0 0 0
output: -1
correct answer: 13
 */
public class BaekJ17472_다리만들기2 {
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
		
		//좌우 섬 연결 여부 검사 
		for(int i=0; i<N; i++) {
			int dis =0;
			int s = 0;
			for(int j=0; j<M; j++) {
				if(check[i][j] == 0) {
					dis++;
				}else if(s!=0 && check[i][j]!=0 && s!= check[i][j]){ //그전에 섬을 발견하고 두번째 섬을 발견한 경우(같은 섬이 아니어야함)
					int x = s-1;
					int y = check[i][j]-1;
					if(connect[x][y] > dis && dis>=2) { //최소의 dis로 섬간의 거리 채우기 
						connect[x][y] = dis;
						connect[y][x] = dis;
					}
					dis = 0; //거리 계산 했으면 거리 초기화 
					s = check[i][j]; //s도 다음 섬으로 옮기기 
				}else if(s==0 && check[i][j]!=0) { //첫번째 섬을 발견한 경우 
					s = check[i][j];
					dis=0;
				}
			}
		}
		
		//상하 섬 연결 여부 검사 
		for(int i=0; i<M; i++) {
			int dis =0;
			int s = 0;
			for(int j=0; j<N; j++) {
				if(check[j][i] == 0) {
					dis++;
				}else if(s!=0 && check[j][i]!=0 && s!= check[j][i]){ //그전에 섬을 발견하고 두번째 섬을 발견한 경우(같은 섬이 아니어야함)
					int x = s-1;
					int y = check[j][i]-1;
					if(connect[x][y] > dis && dis>=2) { //최소의 dis로 섬간의 거리 채우기 
						connect[x][y] = dis;
						connect[y][x] = dis;
					}
					dis = 0; //거리 계산 했으면 거리 초기화 
					s = check[j][i]; //s도 다음 섬으로 옮기기 
				}else if(s==0 && check[j][i]!=0) { //첫번째 섬을 발견한 경우 
					s = check[j][i];
					dis = 0;
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
//		for(int a=0; a<list.size(); a++)
//			System.out.println(Arrays.toString(list.get(a)));
		
		int answer =0;
		int cnt=0;
		for(int i=0; i<list.size(); i++) {
			int[] a = list.get(i);
			
			if(union(a[0], a[1])) {
				answer += a[2];
				//System.out.println(a[0] +" "+ a[1] +" "+ a[2]);
				cnt++; //연결하는 걸 전체 간선 수 보다 1만큼 작게돌면 답 
			}
		}
		System.out.println(cnt);
		System.out.println(count-1);
		
		System.out.println(Arrays.toString(parent));
		
		if(cnt!=count-2) System.out.println("-1");
		else System.out.println(answer);
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(check[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("----------------------------------------");
		for(int i=0; i<count-1; i++) {
			for(int j=0; j<count-1; j++) {
				System.out.print(connect[i][j]+" ");
			}
			System.out.println();
		}

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
