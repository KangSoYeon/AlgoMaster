package first10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJ2933_미네랄 {
	static char[][] arr;
	static boolean[][] check;
	static int R, C;
	
	public static void gravity() {
		    
		
		
	}
	
	private static void bfs(int i, int j) {
		
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		R = Integer.parseInt(tk.nextToken());
		C = Integer.parseInt(tk.nextToken());
		arr= new char[R][C];
		check = new boolean[R][C];
		
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
			for(int i=0; i<R; i++) {
				for(int j=C-1; j>=0; j--) { //밑에서부터 
					if(!check[i][j] && arr[i][j]=='x') {
						bfs(i, j); 
					}
				}
			}
			
			
		}
		
	}

}
