package NHN0929;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class nhn3 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		int L = tk.countTokens();
		int[] p = new int[N]; //정답 배열  
		int player=0;
		for(int i=0; i<L; i++) {
			char a = tk.nextToken().charAt(0); //지금 turn
			if(a=='A') {
				
			}else if(a=='J') {
				
			}else if(a=='K') {
				
			}else if(a=='Q') {
				
			}
			
		}
		//K나오면 뒤에 숫자
		
		
	}

}
