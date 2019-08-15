package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJ11722_가장긴감소하는부분수열 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		int[] arr = new int[N];
		int[] memo = new int[N];
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(tk.nextToken());
		}
		memo[0] = 1;
		
		int max =0;
		int answer =1;
		for(int i=1; i<N; i++) {
			max =0;
			for(int j=0; j<i; j++) { //j중에서의 max를 구하는거니까 i바뀔때마다 초기화  
				if(arr[i]<arr[j] && memo[j]>max) {
					max = memo[j];
				}
			}
			memo[i] = max + 1;
			if(memo[i]>answer)	answer = memo[i];
		}
		
		System.out.println(answer);

	}

}
