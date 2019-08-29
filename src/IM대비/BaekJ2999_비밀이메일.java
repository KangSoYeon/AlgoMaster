package IM대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJ2999_비밀이메일 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String str = bf.readLine();
		StringBuilder bd = new StringBuilder();
		int N = str.length();
		int R = 0;
		int C = 0;
		
		for(int i=1; i<N; i++) {
			if(N % i == 0) {
				C = i;
				R = N/i;
				if(C>=R) break;
			}
		}
		char[][] arr = new char[R][C];
		
		int index =0;
		for(int i=0; i<C; i++) {
			for(int j=0; j<R; j++) {
				arr[j][i] = str.charAt(index++);
			}
		}
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				bd.append(arr[i][j]);
			}
		}
		
		System.out.println(bd.toString());

	}

}
