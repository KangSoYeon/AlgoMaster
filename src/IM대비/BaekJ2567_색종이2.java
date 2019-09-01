package IM대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJ2567_색종이2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		int[][] arr = new int[100][100];
		StringTokenizer tk;
		for(int test=0; test<N; test++) {
			tk = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(tk.nextToken());
			int b = Integer.parseInt(tk.nextToken());
			
			for(int i=a; i<a+10; i++) {
				for(int j=b; j<b+10; j++) {
					arr[j][i]=1;
				}
			}
		}
		int ver =0;
		int hor =0;
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(arr[i][j]==1) {
					ver++;
					break;
				}
				if(arr[j][i]==1) {
					hor++;
					break;
				}
			}
		}
		System.out.println((ver*2) + (hor*2));

	}

}
