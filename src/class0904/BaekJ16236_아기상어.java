package class0904;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
3
9 2 2
2 2 3
1 3 1
-->2 
 */
public class BaekJ16236_아기상어 {
	static int N;
	static int[][] arr, check;
	static ArrayList<int[]> fishs;
	static int size, x, y, time, eat, min; //아기상어의 크기 
	static int[][] dir = { {0,1}, {1,0}, {-1,0}, {0,-1}};
	static boolean flag;
	
	public static void getFish() {
		fishs = new ArrayList<>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(arr[i][j]!=0 && arr[i][j] < size) fishs.add(new int[] {i,j}); //지금 타겟이 될 수 있는 물고기들 넣기 
			}
		}
	}
	
	public static int[] findNear(int sx, int sy) { 
		//x,y에서 가장가까운 갈수있는 먹이의 위치로 현재 상어의 위치를 바꾸는 함수 
		//bfs로 check채우고 
		check = new int[N][N]; //체크는 계속 초기화 
		int[] answer = new int[2];
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {sx, sy});
		check[sx][sy]=1;
		
		while(!q.isEmpty()) {
			
			int[] t = q.poll();
			
			for(int k=0; k<dir.length; k++) {
				int nx = t[0] + dir[k][0];
				int ny = t[1] + dir[k][1];
				
				if(nx>=0 && ny>=0 && nx<N && ny<N
						 && arr[nx][ny]<=size && check[nx][ny]==0) { //같을때 갈수는 있지만 먹지는 못함 
					q.offer(new int[] {nx, ny});
					check[nx][ny] = check[t[0]][t[1]]+1;
				}
				
			}
		}

		for(int i=0; i<fishs.size(); i++) { //모든 물고기에 대해 가장 가까운거 찾기 
			int[] t1 = fishs.get(i); //물고기의 위치 
			if(check[t1[0]][t1[1]]==0) continue; //못가는 경우면 그냥 패스
			flag = true; //는 있지만 하나도 못먹는 경우 따지기 
			
			//체크배열에서 물고기들의 위치 값들을 다 들고와서 가장 작은 걸로 이동
			if(min > check[t1[0]][t1[1]]) {
				min = check[t1[0]][t1[1]];
				answer[0] = t1[0];
				answer[1] = t1[1]; //새로운 물고기의 위치 
			}else if(min == check[t1[0]][t1[1]]) {
				if(answer[0]>t1[0]) { //새로들어온게 더 위쪽에 있으면 바꾸기
					answer[0]=t1[0];
					answer[1]=t1[1];
				}else if(answer[0]==t1[0]) {
					if(answer[1]>t1[1]) { //새로들어온게 더 왼쪽에 있으면 바꾸기
						answer[0]=t1[0];
						answer[1]=t1[1];
					}
				}
			}
		}
		return answer;
	}
	
		
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk;
		N = Integer.parseInt(bf.readLine());
		arr = new int[N][N];
	
		size = 2;
		eat = 0;
		min = Integer.MAX_VALUE;

		for(int i=0; i<N; i++) {
			tk = new StringTokenizer(bf.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(tk.nextToken());
				if(arr[i][j]==9) {
					x=i;
					y=j; //아기상어의 좌표 
					arr[i][j]=0; //아기상어의 좌표 기억하고 그냥 0으로 돌려놓기 
				}
			}
		}
		
		
		while(true) {
			flag = false;
			min = Integer.MAX_VALUE; //min 초기화 안해서 그랬다,,,,
			getFish();
			
			if(fishs.isEmpty())  break;
		
			int[] a = findNear(x,y);
			x=a[0];
			y=a[1];
		
			//System.out.println(x +","+ y+" : "+arr[x][y]);
			//System.out.println("min:" + min);
			if(!flag) {
				time=0;
				break;
			}
			arr[x][y]=0; //먹은 물고기 0으로 바꿔주기 
			time += (min-1); //얼마나 걸렸는지 
			eat++;
			if(eat == size) {
				size++;
				eat=0;
			}
			
			System.out.println("arrr");
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					System.out.print(arr[i][j]+" ");
				}
				System.out.println();
			}
			System.out.println("check");
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					System.out.print(check[i][j]+" ");
				}
				System.out.println();
			}
			System.out.println(size);
			System.out.println("시간"+time);
			
			
			
			
		}
		//if(time==-1) System.out.println(0);
		System.out.println(time);
		
	}

}
