package IM대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJ8958_OX퀴즈 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		
		for(int i=0; i<N; i++) {
			String str = bf.readLine();
			int answer =0;
			int point=0;
			for(int j=0; j<str.length(); j++) {
				
				if(str.charAt(j)=='O') {
					point++;
					answer+=point;
				}else {
					point=0;
				}
				
			}
			
			System.out.println(answer);
		}
		

	}

}
