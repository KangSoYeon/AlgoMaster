package class0904;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution1258_행렬찾기 {
	
	static int N, count;
	static int[][] arr, check;
	static int[][] dir = { {1,0}, {-1,0}, {0,1}, {0,-1}};
	static int[][] answer;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		StringTokenizer tk;
		
		for(int test=1; test<=T; test++) {
			N = Integer.parseInt(bf.readLine());
			arr= new int[N][N];
			check = new int[N][N];
			answer = new int[200][4];
			count = 0;
			
			ArrayList<int[]> ans = new ArrayList<>();		
			for(int i=0; i<N; i++) {
				tk = new StringTokenizer(bf.readLine());
				for(int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(tk.nextToken());
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(arr[i][j]!=0 && check[i][j]==0) {
						answer[count] = new int[] {i, j, 0, 0}; //마지막꺼의 좌표 
						bfs(i,j);
						count++;
					}
				}
			}
			
			
			for(int i=0; i<count; i++) {
				int a = answer[i][2]- answer[i][0]+1; //행
				int b = answer[i][3]- answer[i][1]+1; //열 
			
				ans.add(new int[] {a,b});
			}
			
			Collections.sort(ans, new Comparator<int[]>() { //크기순으로 정렬 
				@Override
				public int compare(int[] o1, int[] o2) {
					if(o1[0]*o1[1] == o2[0]*o2[1]) {
						return o1[0]-o2[0];
					}else {
						return o1[0]*o1[1] - o2[0]*o2[1];
					}
				}
			});
			
			
			System.out.print("#"+test+" "+count);
			for(int i=0; i<ans.size(); i++) {
				System.out.print(" "+ans.get(i)[0]+" "+ ans.get(i)[1]);
			}
			System.out.println();
		}

	}
	private static void bfs(int i, int j) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {i, j});
		check[i][j] = 1; 
		
		while(!q.isEmpty()) {
			
			int[] t = q.poll();
			int nx =0, ny=0;
			for(int k=0; k<dir.length; k++) {
				nx = t[0] + dir[k][0];
				ny = t[1] + dir[k][1];
				
				if(nx>=0 && ny >=0 && nx<N && ny<N
						&& arr[nx][ny] != 0 && check[nx][ny]==0) {
					q.offer(new int[] {nx, ny});
					check[nx][ny] = check[t[0]][t[1]] + 1;
				}
			}
			
			if(q.isEmpty()) { //마지막꺼 뽑을 때 , 마지막 요소 저장 
				answer[count][2] = t[0];
				answer[count][3] = t[1];
			}
		}
	}

}
