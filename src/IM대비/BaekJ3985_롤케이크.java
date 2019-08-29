package IM대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJ3985_롤케이크 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(bf.readLine());
		int N = Integer.parseInt(bf.readLine());
		StringTokenizer tk;
		
		int[] roll = new int[L+1];
		int maxE=Integer.MIN_VALUE, maxC = Integer.MIN_VALUE;
		int idxE=0, idxC=0;
		int count=0;
		for(int i=1; i<=N; i++) { //1번방청객부터 들어옴 
			count=0;
			tk = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(tk.nextToken());
			int b = Integer.parseInt(tk.nextToken());
			for(int j=a; j<=b; j++) { //원하는 양 
				if(roll[j]==0) {
					roll[j]=i;
					count++; //실제로 받은 양
				}
			}
			
			if(maxE < b-a+1) { //예상한 받을양
				maxE = b-a+1;
				idxE = i;
			}
			if(maxC < count) { //실제로 받은양 
				maxC = count;
				idxC = i;
			}
		}
		
		System.out.println(idxE);
		System.out.println(idxC);
	}
}
