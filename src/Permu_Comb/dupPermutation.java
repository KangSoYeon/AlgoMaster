package Permu_Comb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//5 3
//세가지 자리에 0~5까지 중복을 허용하면서 넣는거 
//N개중에 R개를 뽑는 경우 : 총경우의수 : n의 r제곱 

public class dupPermutation {
	static int N, R;
	static int[] selected;
	static int count;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tk.nextToken());
		R = Integer.parseInt(tk.nextToken());
		selected = new int[R];

		pick(0);
		System.out.println(count);
		
	}
	
	public static void pick(int r) {
		if(r==R) {
			count++;
			System.out.println(Arrays.toString(selected));
			return;
		}
		
		for(int i=0; i<N; i++) { 
			//이미 썼는지 안썼는지 확인해주는게 필요없는 퍼뮤테이션 
			selected[r] = i; 
			pick(r+1);
		}	
	}
}
