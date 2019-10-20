package classAfter1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJ14889_스타트와링크 {
	static int N, arr[][], min;
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
		
		min = Integer.MAX_VALUE;
		//0~N-1까지의 사람들을 반반 나눠서 값구하기  
		picked = new int[N/2]; //반 만 뽑기 
		comb(0, 0);
		
		System.out.println(min);

	}
	
	static int picked[];
	private static void comb(int current, int index) {
		if(index==N/2) {
			//뽑힌 Picked로함수 보내기 
			divide();
			return;
		}
		
		for(int i=current; i<N; i++) {
			picked[index]=i;
			comb(i+1, index+1);
		}
		
	}
	private static void divide() {
		int[] second = new int[N/2]; //나머지 반 걸러내기 
		int idx = 0;
		for(int i=0; i<N; i++) {
			boolean flag = false;
			for(int j=0; j<N/2; j++) { //i중에 picked에 안 뽑힌수들을 따로 배열로 하기 
				if(i==picked[j]) {
					flag = true;
				}
			}
			if(!flag) second[idx++]= i;
		}
		
		int sumA=0;
		for(int i=0; i<(N/2)-1; i++) {
			for(int j=i+1; j<N/2; j++) {
				sumA +=	arr[picked[i]][picked[j]];
				sumA += arr[picked[j]][picked[i]];
			}
		}
		
		int sumB=0;
		for(int i=0; i<(N/2)-1; i++) {
			for(int j=i+1; j<N/2; j++) {
				sumB +=	arr[second[i]][second[j]];
				sumB += arr[second[j]][second[i]];
			}
		}
		
		if(min>Math.abs(sumA-sumB)) { //차가 가장 작은거 뽑
			min = Math.abs(sumA-sumB);
		}
		
		
		
	}

}
