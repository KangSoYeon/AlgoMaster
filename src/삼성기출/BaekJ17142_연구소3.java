package 삼성기출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJ17142_연구소3 {
	static int N, M, min;
	static int[][] arr, save;
	static boolean[][] check;
	static ArrayList<int[]> virus;
	static int[][] selected;
	static int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}};
	static boolean ff;
	
	public static void bfs() {
		
		Queue<int[]> q = new LinkedList<>();
		check = new boolean[N][N]; //체크 배열도 계속 초기화 
		
		for(int i=0; i<N; i++) { //arr배열도 다시 초기화 
			for(int j=0; j<N; j++) {
				arr[i][j] = save[i][j]; //다시초기화 
			}
		}
		
		for(int i=0; i<M; i++) { //바이러스 선택한 것만큼 배열 조정 
			int[] t = selected[i];
			arr[t[0]][t[1]]=1; //바이러스 들어간 부분이 1 인걸로 
			check[t[0]][t[1]] = true;
			q.offer(selected[i]); //2로 된 지역들 일단 넣고
		}
		
		while(!q.isEmpty()) { //bfs
			
			int[] temp = q.poll();
			
			for(int k=0; k<dir.length; k++) {
				int nx = temp[0] + dir[k][0];
				int ny = temp[1] + dir[k][1];
				
				if(nx>=0 && ny>=0 && nx<N && ny<N
						&& arr[nx][ny] == 0 && !check[nx][ny]) {
					arr[nx][ny] = arr[temp[0]][temp[1]]+1; 
					q.offer(new int[] {nx, ny});
					check[nx][ny] = true;
				}
				
				
			}
		}
	}
	
	public static int findMax() { //색칠된 수중에 가장 큰수 찾기 
		int max = Integer.MIN_VALUE;
		boolean flag = false;
		loop2:
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				
				if(arr[i][j]==0) { //만약 0이 하나라도 있으면 안되는경우 --> 끝내기 
					flag = true;
					break loop2;
				}else if(arr[i][j]>max) {
					max = arr[i][j];
				}
			}
		}
		
		if(flag) return 30000; //색칠 못하는 경우면 제거 (개큰값 넘겨주기 )
		else return max;
	}
	
	
	public static void pick(int r, int n) {
		//idx는 구한 원소의 갯수, r개를 뽑을 때  , target은몇번째 요소부터 선택할건지 
		if(r>=M) {
			bfs();
	
			for(int k=0; k<virus.size(); k++) { //virus 부분은 신경안써줘도 됨 
				int[] t = virus.get(k);
				arr[t[0]][t[1]] = 1; //잠복 바이러스까지 전부 1로 하기 --> 얘는 카운트되면 안됨 
			}
		
			int temp = findMax();
			if(temp!=30000) {
				ff = true;
				if(temp<min) min= temp;
			}
			return;
		}
		
		if(n < virus.size()) {
			//헐 선택부터 해야됨   
			selected[r]=virus.get(n);
			pick(r+1, n+1); //선택한거
			pick(r, n+1); //선택안한거 
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
		selected = new int[M][];
		min = Integer.MAX_VALUE;
		ff = false;
		
		for(int i=0; i<N; i++) {
			tk = new StringTokenizer(bf.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j]=Integer.parseInt(tk.nextToken());
				save[i][j]=arr[i][j];
				if(arr[i][j]==2) {
					virus.add(new int[] {i,j});
					arr[i][j]=0;//공기랑 다름없는 상태로 놔두기 
					save[i][j]=0;
				}else if(arr[i][j]==1) { 
					arr[i][j]=-1; //벽인부분 -1 
					save[i][j]=-1;
				}
			}
		}
		
		pick(0,0);
		
		if(ff) System.out.println(min-1);
		else System.out.println(-1);
		
	}
}