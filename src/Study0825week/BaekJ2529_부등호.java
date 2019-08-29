package Study0825week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJ2529_부등호 {
	static int N;
	static boolean[] check;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		char[] arr = new char[N];
		for(int i=0; i< N; i++) {
			arr[i]=tk.nextToken().charAt(0);
		}
		
		int[] small = new int[N+1];
		int[] big = new int[N+1];
		boolean[] check = new boolean[10];
		
		//작은거 구하는거 
		int j=0;
		int idx=0;
		
		while(j<arr.length) {
			if(arr[j]=='<') {
				for(int i=0; i<10; i++) {
					if(!check[i]) {
						small[idx++]=i;
						check[i]= true;
						break;
					}
				}
			}else if(arr[j]=='>') {
				int t = 0;
				while(j<arr.length && arr[j]=='>') {
					j++;
					t++;
				}
				j-=t; //j 늘어난것만큼 다시 되돌리기
				int temp = 0;
				for(int i=0; i<10; i++) {
					if(!check[i] && t>=0) {
						t--;
						temp=i;
					}
				}
				
				small[idx++] = temp;
				check[temp] =true;
			}
			j++;
		}
		
		for(int i=0; i<10; i++) { //마지막값 넣어주기 
			if(!check[i]) {
				small[idx]= i;
				break;
			}
		}
		
		//큰거 구하는거 
		j=0;
		idx=0;
		check = new boolean[10];
		while(j<arr.length) {
			if(arr[j]=='>') {
				for(int i=9; i>=0; i--) {
					if(!check[i]) {
						big[idx++]=i;
						check[i]= true;
						break;
					}
				}
			}else if(arr[j]=='<') {
				int t = 0;
				while(j<arr.length && arr[j]=='<') {
					j++;
					t++;
				}
				j-=t; //j 늘어난것만큼 다시 되돌리기
				int temp = 0;
				for(int i=9; i>=0; i--) {
					if(!check[i] && t>=0) {
						t--;
						temp=i;
					}
				}
				
				big[idx++] = temp;
				check[temp] =true;
			}
			j++;
		}
		
		for(int i=9; i>=0; i--) { //마지막값 넣어주기 
			if(!check[i]) {
				big[idx]= i;
				break;
			}
		}
		
		for(int k : big)
			System.out.print(k);
		
		System.out.println();
		
		for(int k : small)
			System.out.print(k);
	}

}
