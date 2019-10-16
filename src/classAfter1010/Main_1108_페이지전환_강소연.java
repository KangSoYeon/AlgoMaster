package classAfter1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main_1108_페이지전환_강소연 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		StringTokenizer tk;
		int[][] D= new int[N][N];
		
		int max = Integer.MIN_VALUE;
		for(int i=0; i<N; i++) {
			tk = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(tk.nextToken());
			int b = Integer.parseInt(tk.nextToken());
			D[a-1][b-1] = 1; //a-1에서 b-1에서 갈수 있다 
			if(max<a) max = a;
			if(max<b) max = b;
			
		}
		
		for(int i=0; i<max; i++) {
			for(int j=0; j<max; j++) { //연결 안된 선끼리의 값은 가장 큰 값으로 채우기 
				if(D[i][j]==0 && !(i==j)) D[i][j] = 9999999;
			}
		}
		
		for(int k=0; k<max; k++) { //경유지
			for(int i=0; i<max; i++) { //출발지
				if(i==k) continue; //경유지와 출발지가 같다면 패스
				for(int j=0; j<max; j++) { //도착지
					if(j==k || i==j) continue; //경유지와 도착지가 같거나 출발지와 도착지가 같다면 패스 
					if(D[i][k] + D[k][j] < D[i][j]) {
						D[i][j] = D[i][k] + D[k][j];
					}
				}
			}
		}

		float answer =0;
		for(int i=0; i<max; i++) {
			for(int j=0; j<max; j++) {
				answer += D[i][j];
			}
		}
		
		System.out.printf("%.3f", (float)(answer/(max*(max-1))));
	}
}
