package class0905;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1873_상호의배틀필드 {
	
	static int H,W, N;
	static char[][] arr;	
	static int a,b,d;
	static int[][] dir = {{-1,0}, {0,1}, {1,0}, {0,-1}}; //상우하좌
	
	public static void findCar() {
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				if(arr[i][j]=='^') {
					a=i;b=j;
					d=0;
				}else if(arr[i][j]=='>') {
					a=i;b=j;
					d=1;
				}else if(arr[i][j]=='v') {
					a=i;b=j;
					d=2;
				}else if(arr[i][j]=='<') {
					a=i;b=j;
					d=3;
				}
			}
		}
	}
	
	public static void move() {
		//a,b위치에서 d방향으로 한칸 전진 
		int nx = a + dir[d][0];
		int ny = b + dir[d][1];
		
		if(nx>=0 && ny>=0 && nx<H && ny<W && arr[nx][ny]=='.') { //전진할수 있으면 전진 
			arr[a][b]='.'; //전진했으면 .으로 바꾸기 
			a = nx;
			b = ny;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		
		for(int test=1; test<=T; test++) {
			StringTokenizer tk = new StringTokenizer(bf.readLine());
			H = Integer.parseInt(tk.nextToken());
			W = Integer.parseInt(tk.nextToken());
			arr= new char[H][W];
					
			for(int i=0; i<H; i++) {
				String str = bf.readLine();
				for(int j=0; j<W; j++) {
					arr[i][j] = str.charAt(j);
				}	
			}
			
			N = Integer.parseInt(bf.readLine());
			String order = bf.readLine();
			
			for(int i=0; i<N; i++) {
				char c = order.charAt(i);
				findCar();
				
				if(c=='U') {
					d=0;
					move();
					arr[a][b]='^';
				}else if(c=='D') {
					d=2;
					move();
					arr[a][b]='v';
				}else if(c=='L') {
					d=3;
					move();
					arr[a][b]='<';
				}else if(c=='R') {
					d=1;
					move();
					arr[a][b]='>';
				}else if(c=='S') {
					int ta = a;
					int tb = b;
					while(true) {
						int sx = ta + dir[d][0];
						int sy = tb + dir[d][1];
						
						if(sx<0 || sy<0 || sx>=H || sy>=W || arr[sx][sy]=='#') break;
						
						if(arr[sx][sy]=='*') { //벽돌로 만들어진 벽을 만나면 
							arr[sx][sy]='.'; //깨고 
							break;
						}
						
						ta = sx;
						tb = sy;
						
					}	
				}
			}
			
			System.out.print("#"+test+" ");
			for(int i=0; i<H; i++) {
				for(int j=0; j<W; j++) {
					System.out.print(arr[i][j]);
				}
				System.out.println();
			}
		}
	}
}
