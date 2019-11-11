import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJ3055_탈출 {
	static int R, C, sx, sy, ex, ey, answer;
	static char arr[][];
	static boolean check[][], check2[][];
	static int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}};
	static ArrayList<int[]> water;
	static boolean flag;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		R = Integer.parseInt(tk.nextToken());
		C = Integer.parseInt(tk.nextToken());
		arr = new char[R][C];
		check = new boolean[R][C];
		check2 = new boolean[R][C];
		water = new ArrayList<>();

		
		for(int i=0; i<R; i++) {
			String str = bf.readLine();
			for(int j=0; j<C; j++) {
				arr[i][j]= str.charAt(j);
				if(arr[i][j]=='*') {
					water.add(new int[] { i, j } );
				}else if(arr[i][j] =='D') { //도착지 
					ex=i;
					ey=j;
				}else if(arr[i][j] =='S') { //출발
					sx=i;
					sy=j;
					arr[i][j] = '.'; //출발지는 그냥 ㅇ렇게 바꾸기 
				}
			}
		}
		
		
		//S--> D로 가는거 
		//* --> . 로 확장돼서 퍼짐  
		//물부터 차고,고슴도치 이동 
		/*Queue<int[]> c = new LinkedList<>();
		c.offer(new int[] {sx, sy}); //처음 시작 좌표 
		if(water.isEmpty()) {
			justbfs();
		}else {
			bfs();
		}*/
		bfs();
		if(flag) System.out.println(answer);
		else System.out.println("KAKTUS");
		
	}
	
	private static void justbfs() {
		Queue<int[]> c = new LinkedList<>();
		c.offer(new int[] {sx, sy}); //처음 시작 좌표 
		check2[sx][sy] = true;
		loop:
		while(!c.isEmpty()) {//고슴도치 이동
			answer++;
			int sizeC = c.size();
			while(--sizeC>=0) {
				int[] temp = c.poll();
				
				for(int i=0; i<dir.length; i++) {
					int nx = dir[i][0] + temp[0];
					int ny = dir[i][1] + temp[1];
					
					if(nx==ex && ny==ey) { //목적지에 도착  끝내기 
						flag = true;
						break loop; //전부 끝내기 
					}
					
					if(nx>=0 && ny>=0 && nx<R && ny<C && arr[nx][ny]=='.' && !check2[nx][ny]) {//갈수 있는 곳이면 
						c.add(new int[] {nx, ny});
						check2[nx][ny] = true;
					}
				}
			}
		}
	}
	
	private static void bfs() {
		Queue<int[]> q = new LinkedList<int[]>();
		Queue<int[]> c = new LinkedList<>();
		
		c.offer(new int[] {sx, sy}); //처음 시작 좌표 
		check2[sx][sy] = true;
		for(int i=0; i<water.size(); i++) { //처음 물인데 먼저 큐에 넣기 
			int[] t = water.get(i);
			q.add(t);
			check[t[0]][t[1]] = true;
		}
		
		loop2:
		while(!q.isEmpty()) {
			answer++; //한 타임 도는 거임 이게 
			int size = q.size();
			while(--size>=0) {
				int[] temp = q.poll();
				
				for(int i=0; i<dir.length; i++) {
					int nx = temp[0] + dir[i][0];
					int ny = temp[1] + dir[i][1];
					
					if(nx<0 || ny<0 || nx>=R || ny>=C) continue;
					
					if(arr[nx][ny]=='.' && !check[nx][ny]) {//빈공간이
						arr[nx][ny]='*'; //*로 바꾸고
						check[nx][ny]=true;
						q.add(new int[] { nx, ny }); //다음 물로 바꾸고 
					}
				}
			}

			//한타임 물 채우기 끝났으니까 고슴도치 이동 
			while(!c.isEmpty()) {//고슴도치 이동
				int sizeC = c.size();
				while(--sizeC>=0) {
					int[] temp = c.poll();
					
					for(int i=0; i<dir.length; i++) {
						int nx = dir[i][0] + temp[0];
						int ny = dir[i][1] + temp[1];
						
						if(nx==ex && ny==ey) { //목적지에 도착  끝내기 
							flag = true;
							break loop2; //전부 끝내기 
						}
						
						if(nx>=0 && ny>=0 && nx<R && ny<C && arr[nx][ny]=='.' && !check2[nx][ny]) {//갈수 있는 곳이면 
							c.add(new int[] {nx, ny});
							check2[nx][ny] = true;
						}
					}
				}
				break; //고슴도치가 한칸만 이동하면 끝내기 >> 한칸 이동한 좌표를 모두 큐에 넣어놓기 
			}
		/*	
			
			System.out.println("----------------");
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					System.out.print(arr[i][j]+" ");
				}
				System.out.println();
			}*/
			
		}
		
		loop:
		while(!c.isEmpty()) {
			answer++;
			int sizeC = c.size();
			while(--sizeC>=0) {
				int[] temp = c.poll();
				
				for(int i=0; i<dir.length; i++) {
					int nx = dir[i][0] + temp[0];
					int ny = dir[i][1] + temp[1];
					
					if(nx==ex && ny==ey) { //목적지에 도착  끝내기 
						flag = true;
						break loop;
					}
					
					if(nx>=0 && ny>=0 && nx<R && ny<C && arr[nx][ny]=='.' && !check2[nx][ny]) {//갈수 있는 곳이면 
						c.add(new int[] {nx, ny});
						check2[nx][ny] = true;
					}
				}
			}
		}
	}
}
