package Study0825week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJ2529_부등호 {
	static int N;
	static boolean[] check;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		char[] arr = new char[N];
		check = new boolean[N];
		for(int i=0; i< N; i++) {
			arr[i]=tk.nextToken().charAt(0);
		}
		
		int[] small = new int[N+1];
		int[] big = new int[N+1];
		
		int sidx=0;
		for(int i=0; i<10; i++) {
			if(!check[i]) {
				small[sidx] = i;
				check[i] =true;
			}
			for(int j=0; j<N; j++) {
				if(arr[j]=='<') {
					sidx++;
				}else if(arr[j]=='>') {
					
					
					
				}
			}
			
			
		}
		
		
		
	}

}
