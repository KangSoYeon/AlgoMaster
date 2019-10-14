package first10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJ2933_미네랄 {
	static char[][] arr;
	static int[][] check;
	static int R, C;
	static int[][] dir = {{1,0}, {0,1}, {-1, 0}, {0,-1}};
	
	public static void gravity() {
		//중력 필요없는건 1
		//2가 칠해진  덩어리는 중력이 작용해야함  
		
		
		
		
	}
	
	private static void bfs(int i, int j, int c) {
		//c로 check배열 색칠ㅈ하기
		
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {i, j});
		check[i][j] = c;
		
		while(!q.isEmpty()) {
			int[] t = q.poll();
			
			for(int d=0; d<dir.length; d++) {
				int nx = t[0] + dir[d][0];
				int ny = t[1] + dir[d][1];
				if(nx>=0 && ny>=0 && nx<R && ny<C
						&& arr[nx][ny]=='x' && check[nx][ny]==0) {
					q.add(new int[] {nx, ny});
					check[nx][ny]=c;
				}
			}
			
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		R = Integer.parseInt(tk.nextToken());
		C = Integer.parseInt(tk.nextToken());
		arr= new char[R][C];
		check = new int[R][C];
		
		for(int i=0; i<R; i++) {
			String str = bf.readLine();
			for(int j=0; j<C; j++) {
				arr[i][j] = str.charAt(j);
			}
		}
		
		int N = Integer.parseInt(bf.readLine());
		tk = new StringTokenizer(bf.readLine());
		int[] th = new int[N];
		for(int i=0; i<N; i++) {
			th[i] = R-Integer.parseInt(tk.nextToken());
		}
		
		//왼오왼오왼오...
		
		for(int t=0; t<N; t++) {
			//1. 한번 던져서 깨사고 (th의 x좌표를 왼쪽에서 온거 깨사고 오른쪽에서 온거 깨사고 )
			if(t%2 ==0) { //왼쪽에서 던지기
				for(int i=0; i<C; i++) {
					if(arr[th[t]][i]=='x') { //미네랄 뿌사기 
						arr[th[t]][i]='.';
						break;
					}
				}
			}else { //오른쪽에서 던지기 
				for(int i=C-1; i>=0; i--) {
					if(arr[th[t]][i]=='x') { //미네랄 뿌사기 
						arr[th[t]][i]='.';
						break;
					}
				}
			}
			
			//2. 미네랄 중력작용하면 체크해서 떨어뜨리고
			int count = 1;
			for(int i=0; i<R; i++) {
				for(int j=C-1; j>=0; j--) { //밑에서부터 
					if(check[i][j]==0 && arr[i][j]=='x') {
						bfs(i, j, count++); //덩어리가 여러개 이면
						//바닥에 붙어있는건 일단 1  
					}
				}
			}
			
			if(count>=2) { //파편이 있으면 
				gravity();
			}
			
			
		}
		
	}

}
