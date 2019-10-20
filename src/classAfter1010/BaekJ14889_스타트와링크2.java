package classAfter1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJ14889_스타트와링크2 {
	static int N, arr[][], min;
	static boolean pick[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		
		arr = new int[N][N];
		StringTokenizer tk;
		for(int i=0; i<N; i++) {
			tk = new StringTokenizer(bf.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(tk.nextToken());
			}
		}
		pick = new boolean[N];
		min = Integer.MAX_VALUE;
		
		subset(0, 0);
		
		System.out.println(min);

	}
	
	static void subset(int current, int index) { //부분집합 
		if(index==N/2) {
			compare();
			return;
		}
		
		for(int i=current; i<N; i++) {
			pick[i] = true;
			subset(i+1, index+1);
			pick[i] = false;
		}
		
	}

	private static void compare() {
		int[] a = new int[N/2];
		int[] b = new int[N/2];
		int idxa=0, idxb=0;
		for(int i=0; i<N; i++) {
			if(pick[i]) a[idxa++] = i;
			else b[idxb++] = i;
		}
		int asum=0;
		int bsum=0;
		for(int i=0; i<N/2; i++) {
			for(int j=i+1; j<N/2; j++) {
				asum += arr[a[i]][a[j]];
				asum += arr[a[j]][a[i]];
			}
		}
		for(int i=0; i<N/2; i++) {
			for(int j=i+1; j<N/2; j++) {
				bsum += arr[b[i]][b[j]];
				bsum += arr[b[j]][b[i]];
			}
		}
		if(min>Math.abs(asum-bsum)) min = Math.abs(asum-bsum);
		
	}

}
