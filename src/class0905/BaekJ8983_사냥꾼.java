package class0905;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJ8983_사냥꾼 {
	static int M, N, L;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		int count =0;
	
		M = Integer.parseInt(tk.nextToken());
		N = Integer.parseInt(tk.nextToken());
		L = Integer.parseInt(tk.nextToken());
		int[] shoot = new int[M];
		
		tk = new StringTokenizer(bf.readLine());

		for(int i=0; i<M; i++) {
			shoot[i] = Integer.parseInt(tk.nextToken());
		}
		Arrays.sort(shoot);
		
		for(int i=0; i<N; i++) {
			tk = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(tk.nextToken());
			int b = Integer.parseInt(tk.nextToken());
			//받을때마다 가장 작은 사대와 거리 계산하고 그게 사정거리보다 작으면 count++
			for(int j=0; j<M-1; j++) { //가장 절댓값이 작은 곳 
				if((shoot[j]<a && shoot[j+1]>=a) || (shoot[j]<=a && shoot[j+1]>a)) { //a값이 고 사이가 되는때 
					if(Math.abs(shoot[j]-a) < Math.abs(shoot[j+1]-a)) {
						if(Math.abs(shoot[j]-a)+b <=L) { //
							count++;
							break; //잡았으면 끝 
						}
					}else {
						if(Math.abs(shoot[j+1]-a)+b <=L) {
							count++;
							break;
						}
					}
				}
			}
			
		}
		
		
		System.out.println(count);
	}
}
