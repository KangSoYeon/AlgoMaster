package Permu_Comb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
5
3 2 6 9 1
 */
public class nextPermutation {
	static int N, totalCount;
	static int[] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		arr = new int[N];
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(tk.nextToken());
		}
		Arrays.sort(arr);
		
		System.out.println(Arrays.toString(arr));
		while(nextPer()) {
			System.out.println(Arrays.toString(arr));
		}
		System.out.println(totalCount);
		
	}

	private static boolean nextPer() {  //그다음에 올 큰 순열 만들기
		totalCount++;
		//1. 뒷쪽부터 왼쪽으로 탐색하며 교환이 필요한 위치를 찾는다.
		int i = N-1;
		while(i>0 && arr[i-1]>=arr[i]) i--;
		
		if(i == 0) return false; //찾은 위치가 0이면 이미 내림차순 된 순열이므로 다음순열은 없다.
		
		//2. i-1위치 : 교환이 필요한 위치
		// i-1위치값과 교환할 뒷쪽에서 다음 큰 수 찾기
		int j = N-1;
		while(arr[i-1]>=arr[j])	j--;
		
		//3. i-1위치값과 j위치값 교환
		swap(i-1, j);
		
		//4. i위치부터 끝까지의 순열을 오름차순으로 재조정
		j = N-1;
		while(i<j)	swap(i++, j--);
		
		return true;
	}
	
	private static void swap(int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
