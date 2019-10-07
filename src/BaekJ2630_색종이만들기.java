import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJ2630_색종이만들기 {

	static int N, blue, whites;
	static int[][] arr ;
	
	static void rec(int n, int sx, int sy) { //시작하는 x좌표, y좌표
		//n만큼의 크기가 하나의 색종이로 칠해져있는지 확인하는 함수
		int s = n/2;
		if(s<=1) {
			return;
		}
		int b =0;
		int w =0;
		for(int i=sx; i<s+sx; i++) {
			for(int j=sy; j<s+sy; j++) {
				if(arr[i][j]==1) {
					b++;
				}else{
					w++;
				}
			}
		}
		
		if(b==s*s) {
			
		}else if(w==s*s) {
			
		}
		
		
		
	}
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		StringTokenizer tk;
		arr = new int[N][N];
		for(int i=0; i<N; i++) {
			tk = new StringTokenizer(bf.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(tk.nextToken());
			}
		}
		
	
			
		
		
		
		

	}
}
