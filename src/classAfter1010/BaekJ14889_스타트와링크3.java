package classAfter1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJ14889_스타트와링크3 {
	static int N, arr[][], min;
	static int pick[];
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
		pick = new int[N];
		for(int i = N-1; i>=N/2; i--) { //뽑아야하는 갯수만큼 뒤에 1을 넣기  
			pick[i] = 1;
		}
		min = Integer.MAX_VALUE;
		
		do {
			if(pick[0]==1) break; //반돌았으면 끝내기  
			compare();
		}while(next_permu());
		
		System.out.println(min);

	}
	
	static boolean next_permu() {
		int i = N-1;
		while(i>0 && pick[i-1]>=pick[i]) i--;
		
		if(i==0) return false;
		
		int j = N-1;
		while(pick[j] <= pick[i-1]) j--;
		
		swap(i-1, j);
		
		j = N-1;
		while(j>i) swap(i++, j--);
		
		return true;
		
	}

	private static void swap(int i, int j) {
		int temp = pick[i];
		pick[i] = pick[j];
		pick[j] = temp;
		
	}

	private static void compare() {
		int[] a = new int[N/2];
		int[] b = new int[N/2];
		int idxa=0, idxb=0;
		for(int i=0; i<N; i++) {
			if(pick[i]==1) a[idxa++] = i;
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
