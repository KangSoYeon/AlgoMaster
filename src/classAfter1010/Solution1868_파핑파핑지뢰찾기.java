package classAfter1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution1868_파핑파핑지뢰찾기 {
	static int N;
	static char arr[][];
	static int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}, {1,1}, {1,-1}, {-1,1}, {-1, -1}};
	static boolean check[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		
		for(int test=1;test<=T; test++) {
			N = Integer.parseInt(bf.readLine());
			arr = new char[N][N];
			check = new boolean[N][N];
			int answer = 0;
			
			for(int i=0; i<N; i++) {
				String str = bf.readLine();
				for(int j=0; j<N; j++) {
					arr[i][j] = str.charAt(j);
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(arr[i][j] =='.') {
						int count =0;
						for(int d=0; d<dir.length; d++) {
							int nx = i + dir[d][0];
							int ny = j + dir[d][1];
							if(nx>=0 && ny>=0 && nx<N && ny<N && arr[nx][ny]=='*') {
								count++;
							}
						}
						arr[i][j] = (char)(count + '0'); //int to char
					}
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(arr[i][j]=='0' && !check[i][j]) { //0인데 누르면 그 주변 지뢰있는데까지 다터지기 
						bfs(i, j);
						answer++;
					}
				}
			}
			
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(arr[i][j]!='*' && !check[i][j]) {
						answer++;
					}
				}
			}
			
			System.out.println("#"+test+" "+answer);
		}
	}

	private static void bfs(int i, int j) {
		
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {i,j});
		check[i][j] = true;
		
		while(!q.isEmpty()) {
			int[] t = q.poll();
			
			for(int d=0; d<dir.length; d++) {
				int nx = t[0] + dir[d][0];
				int ny = t[1] + dir[d][1];
				
				if(nx>=0 && ny>=0 && nx<N && ny<N 
						&& arr[nx][ny]!='*' && !check[nx][ny]) { //일단 *가 아니면 가는데
					check[nx][ny]= true; //갔다고 표시만 하고
					
					if(arr[nx][ny]=='0') { //0일때만 넣기 
						q.add(new int[] {nx, ny});
					}
					
				}
				
			}
		}
	}
}
