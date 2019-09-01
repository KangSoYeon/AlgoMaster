package IM대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJ2564_경비원 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		
		int N = Integer.parseInt(tk.nextToken());
		int M = Integer.parseInt(tk.nextToken());
		
		int T = Integer.parseInt(bf.readLine());
		int[][] arr = new int[T][];
		for(int i=0; i<T; i++) {
			tk = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(tk.nextToken()); //1234 : 북남서동  
			int b = Integer.parseInt(tk.nextToken()); 
			//북남 : 왼쪽으로부터 거리, 동서: 위쪽으로부터의 거리 
			arr[i] = new int[] {a, b};
		}
		
		tk = new StringTokenizer(bf.readLine());
		int ma = Integer.parseInt(tk.nextToken());
		int mb = Integer.parseInt(tk.nextToken());
		
		for(int i=0; i<T; i++) {
			int[] t = arr[i];
			if(ma==1) {//북   
				if(t[0]==2) {
					
				}
			}else if(ma ==2) {//
				if(t[0]==1) {
					
				}
			}else if(ma==3) {
				if(t[0]==4) {
					
				}
			}else if(ma==4) {
				if(t[0]==3) {
					
				}
			}
			
		}
		
		
		

	}

}
