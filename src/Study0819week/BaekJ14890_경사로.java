package Study0819week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJ14890_경사로 {
	static int N, L, count;
	static int[][] arr;
	static int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}};
	
	public static boolean isBoundary(int i, int j) {
		if(i<0 || i>=N || j<0 || j>=N)	return false;
		return true;
	}
	
	public static void findRoad() {
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N-1; j++) {
				
				
//				if(arr[i][j+1] - arr[i][j] ==1 ) { //다음칸이 하나 커졌으면
//					if(j+1>=L) { // 그전꺼에 경사로 놓을수 있으면 
//						count++;
//					}else continue;
//						//if(isBoundary(nx, ny) && )
//						
//				}else if(arr[i][j] - arr[i][j+1] ==1) { //다음칸이 하나 작아졌으면 
//					if(N-1-j >=L) { //그 이후꺼에 경사로 놓을 수 있으면 
//						count++;
//					}else continue;
//				
//				}else if(Math.abs(arr[i][j] - arr[i][j+1]) >=2 ) {
//					//연속된 블럭의 갭이 2이상인경우 --> 무조건 이줄 안됨
//					continue;
//				}
			}
		}
		
		
		
	}
	
	
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tk.nextToken());
		L = Integer.parseInt(tk.nextToken());
		
		for(int i=0; i<N; i++) {
			tk = new StringTokenizer(bf.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(tk.nextToken());
			}
		}
		
		//한줄의 시작부터 조회
		//오른쪽으로 가면서 조회 --> 다른수를 만나면
		//다른수에 대해 줄의 다른방향으로 L+1만큼 하나 작거나 큰수가 있으면 ok
		//다른수와의 차이가 2이상이면 바로 빠꾸
		
		
		
		

	}

}
