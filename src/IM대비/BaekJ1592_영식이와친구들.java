package IM대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJ1592_영식이와친구들 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(tk.nextToken());
		int M = Integer.parseInt(tk.nextToken());
		int L = Integer.parseInt(tk.nextToken());
		
		int thr = 1;
		int[] arr = new int[N+1];
		arr[1]++;
		int count=0;
		
		while(true) {
			
			if(arr[thr]>=M) break; //M번 받은사람이 한번이라도 생기면 끝 
			count++;
			
			if(arr[thr] % 2 !=0) { //홀수번 받았으면
				thr = (thr+L) % N;
			}else if(arr[thr]%2 ==0) { //짝수번 받았으면 
				thr = (thr + N-L) % N;
			}
			arr[thr]++;
			
		}	
		System.out.println(count);
	
	}
}
