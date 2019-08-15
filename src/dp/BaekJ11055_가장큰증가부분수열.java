package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJ11055_가장큰증가부분수열 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		int[] arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(tk.nextToken());
		}
		
		int[] memo = new int[N];
	
	
		memo[0]=arr[0]; //해당 인덱스 까지의 증가수열 최대합 
		int max = 0; // 더해줄때 바로 더할수도 있으므로  
		for(int i = 1; i<N; i++) { //구하고자하는 memo값의 인덱스  
			max =0; //해당 인덱스에서의  맥스를구해야되니까 초기화 !!!!!!!! 
			for(int j=0; j<i; j++) { //memo되있는전의 값들 중에 가장 큰값 찾기    
				if(arr[i]>arr[j] && max<memo[j]) {	//증가하고 있고, 그때까지의 합이 제일 큰거  
					max = memo[j];
				}
			}
			memo[i] = max + arr[i]; //i까지의 가장 큰 증가 부분 수열의 합 
		}
		
		max = 0;
		for(int a: memo)
			if(a>max)	max = a;
		
		System.out.print(max);
		//각인덱스까지의 부분증가수열의 합이므로 마지막 인덱스 값이 아닌 memo한 값들중에 가장 큰 값 출력해야함  
		
	}

}
