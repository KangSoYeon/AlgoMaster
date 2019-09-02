package 삼성기출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJ17142_연구소3 {
	static int N, M, min;
	static int[][] arr, save;
	static boolean[][] check;
	static ArrayList<int[]> virus;
	static int[][] selected;
	static int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}};
	
	public static void bfs() {
		
		Queue<int[]> q = new LinkedList<>();
		
		for(int i=0; i<M; i++) {
			int[] t = selected[i];
			arr[t[0]][t[1]]=1; //���̷��� �� �κ��� 1 �ΰɷ� 
			check[t[0]][t[1]] = true;
			q.offer(selected[i]); //2�� �� ������ �ϴ� �ְ� 
		}
		
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			
			for(int k=0; k<dir.length; k++) {
				int nx = temp[0] + dir[k][0];
				int ny = temp[1] + dir[k][1];
				
				if(nx>=0 && ny>=0 && nx<N && ny<N
						&& arr[nx][ny]==0 && !check[nx][ny]) {
					arr[nx][ny] = arr[temp[0]][temp[1]]+1; //���� �ð� 
					q.offer(new int[] {nx, ny});
					check[nx][ny] = true;
				}
				
				
			}
		}
	}
	
	public static int findMax() {
		int max = Integer.MIN_VALUE;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(arr[i][j]>max) {
					max = arr[i][j];
				}
			}
		}
		
		return max;
	}
	
	
	public static void pick(int idx, int r, int target) {
		//idx는 구한 원소의 갯수
		//r개를 뽑을 때  
		//target은몇번째 요소부터 선택할건지 
		if(r>=M) {
			//������ selected�� ���̷��� ���� bfs

//			for(int i=0; i<N; i++) {
//				for(int j=0; j<N; j++) {
//					arr[i][j] = save[i][j]; //다시초기화 
//				}
//			}
			bfs();
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					System.out.print(arr[i][j]+" ");
				}
				System.out.println();
			}
			
			System.out.println();
//			for(int i=0; i<M; i++) {
//				System.out.print(Arrays.toString(selected[i])+" ");
//			}
			int temp = findMax();
			if(temp<min) {
				min= temp;
			}
			return;
		}else if(target==virus.size()) return;
		else {
			selected[idx]=virus.get(target);
			pick(idx+1, r+1, target+1);
			pick(idx, r, target+1);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		
		N=Integer.parseInt(tk.nextToken());
		M=Integer.parseInt(tk.nextToken());
		arr = new int[N][N];
		save = new int[N][N];
		check = new boolean[N][N];
		virus = new ArrayList<int[]>();
		selected = new int[M][]; //M���� ���̷��� ��ġ �̱� 
		min = Integer.MAX_VALUE;
		
		for(int i=0; i<N; i++) {
			tk = new StringTokenizer(bf.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j]=Integer.parseInt(tk.nextToken());
				save[i][j]=arr[i][j];
				if(arr[i][j]==2) {
					virus.add(new int[] {i,j});
					arr[i][j]=-1; //벽이랑 다를거 없는 상태 해놓기 
					save[i][j]=-1;
				}else if(arr[i][j]==1) {
					arr[i][j]=-1; //���� -1�� �ٲٱ� 
					save[i][j]=-1;
				}
			}
		}
		
		pick(0, 0, 0);
		System.out.println(min-1);
		
	}

}
