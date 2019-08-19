package Study0819week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJ4991_로봇청소기 {
	static int[][] dir = {{0,1}, {1,0}, {-1,0}, {0,-1}};
	static int dirtC, cleanedC;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		int w = Integer.parseInt(tk.nextToken());
		int h = Integer.parseInt(tk.nextToken());
		
		if(w==0 && h==0)	return;
		
		char[][] arr = new char[w][h];
		int sx=0, sy=0;
		for(int i=0; i<w; i++) {
			String str = bf.readLine();
			for(int j=0; j<h; j++) {
				arr[i][j] = str.charAt(j);
				if(arr[i][j]=='o') {
					sx = i;
					sy = j;
				}else if(arr[i][j]=='*') {
					dirtC++; //지울때마다 이걸 감소시키고 이게 0되면 다청소 끝!
				}
			}
		}
		
		for(int i=0; i<w; i++) {
			for(int j=0; j<h; j++) {
				sx 
			}
		}
		
		
		
		
		
		
		
	}

}
