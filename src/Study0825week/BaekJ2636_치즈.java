package Study0825week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJ2636_치즈 {
	static int N, M, count, rightB;
	static int[][] arr, check;
	static int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}};
	
	public static void bfsA(int x, int y) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {x, y});
		
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			
			for(int i=0; i<dir.length; i++) {
				int nx = temp[0] + dir[i][0];
				int ny = temp[1] + dir[i][1];
				
				if(nx>=0 && ny>=0 && nx<N && ny<M &&
						(arr[nx][ny] == 0 || arr[nx][ny]==3) && check[nx][ny]==0) {
					q.offer(new int[] {nx, ny});
					check[nx][ny] = 3; 
					arr[nx][ny] = 3; //공기를 3으로 칠함 
				}
			}
		}
	}
	
	public static void bfsC() {
		Queue<int[]> q2 = new LinkedList<int[]>();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(arr[i][j]==1) {  //치즈 찾았는데  
					for(int k=0; k<dir.length; k++) {
						int nx = i + dir[k][0];
						int ny = j + dir[k][1];
					
						if(nx>=0 && ny>=0 && nx<N && ny<M 
								&& arr[nx][ny]==3) { //방향검색시 공기면  
							//arr[nx][ny]=3; //치즈가 녹았음 
							q2.offer(new int[] {i, j}); //녹일치즈 큐에 담아놓고 한번에 녹이기
							//당장 녹이면 영향받을수 있어서  
							break;
						}
					}
				}
			}
		}
		if(count-q2.size()<=0) {
			rightB = count;
		}
		
		count -= q2.size(); //치즈 감소 
		
		for(int i=0; i<q2.size(); i++) {
			int[] temp = q2.poll();
			arr[temp[0]][temp[1]] = 3; //한번에  치즈 녹이고	
		}
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());
		arr = new int[N][M];
		check = new int[N][M];
		count=0; //치즈갯수 저장 
		
		for(int i=0; i<N; i++) {
			tk = new StringTokenizer(bf.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(tk.nextToken());
				if(arr[i][j]==1) count++;
			}
		}
		
		int time=0;
		//while(true) {
			//공기인 부분을 체크해서 칠하기  
			bfsA(0,0); //0,0과 맞닿은 0들은 모두 공기임 
			check = new int[N][M]; //check 초기화 
			for(int i=0; i<arr.length; i++)
				System.out.println(Arrays.toString(arr[i]));
			
			System.out.println();
			bfsC(); //치즈 한번 녹이기 
			
			for(int i=0; i<arr.length; i++)
				System.out.println(Arrays.toString(arr[i]));
			
			System.out.println();
			
//			time++;
//			if(count == 0) {
//				break;
//			}
			
		//}
		
		System.out.println(time);
		System.out.println(rightB);

	}

}
