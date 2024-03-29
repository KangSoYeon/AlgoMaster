package Permu_Comb;

import java.util.Arrays;
import java.util.Scanner;
/*
nCr
10 4
10개중에 4개 뽑기 
 */
public class Combination { //nCr
	static int N, R; //1~N까지의 수
	static int[] numbers; //R개의 수 

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		R = sc.nextInt();
		numbers = new int[R]; //조합에 뽑힌 수들 저장하는 배열

		combination(1,0);
	}

	private static void combination(int n, int r) { // index 시작수
		if(r == R) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		if(n <= N) {
			//뽑고 , 안뽑아야함 
			numbers[r] = n; //덮어쓰니까 checked 배열이 필요가 없음
			combination(n+1, r+1); //선택하던
			combination(n+1, r); //선택하지 않던 number(정답배열)에 넣을 인덱스를 그대로 주면
		}
		
	}

}
