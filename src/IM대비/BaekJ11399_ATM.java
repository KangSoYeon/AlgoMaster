package IM대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJ11399_ATM {
	static int N, min;
	static int answer;
	static boolean[] check;
	static int[] line, arr;
	
	public static void per(int idx) {
		if(idx==N) {
			answer=0;
			for(int i=0; i<N; i++) { //줄세운 line배열에서 하나씩 꺼내서 
				for(int j=0; j<=i; j++) {
					answer += arr[line[j]];
				}
			}
			
			if(min>answer)	min=answer;
			return;
		}
		
		for(int i=1; i<=N; i++) {
			if(!check[i]) {
				line[idx]= i;
				check[i]=true;
				per(idx+1);
				check[i]=false;
			}
		}
		
	}
	
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		arr = new int[N+1];
		check = new boolean[N+1];
		line = new int[N];
		min = Integer.MAX_VALUE;
		answer =0;
		for(int i=1; i<=N; i++) {
			arr[i]=Integer.parseInt(tk.nextToken());
		}
		
		Arrays.sort(arr);
		for(int i=1; i<=N; i++) { //줄세운 line배열에서 하나씩 꺼내서 
			for(int j=1; j<=i; j++) {
				answer += arr[j];
			}
		}	
		
		//per(0);		
		System.out.println(answer);

	}

}
