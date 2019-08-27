package Permu_Comb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
nPr
10 4
10개중에 4개 선택해서 줄세우기 
 */
public class Permutation{ 
	static int N, R ,numbers[];
	static boolean[] selected;
	static int totalCount;
	public static void main(String[] args) throws IOException, NumberFormatException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(tk.nextToken());
		R = Integer.parseInt(tk.nextToken());
		numbers = new int[R];
		selected = new boolean[N+1]; //각 number가 나올때마다 flag배열을 사용했다고 표시
		
		permutation(0); 
		System.out.println("경우의수: "+ totalCount);
	}
	
	private static void permutation(int index) { //index는 몇번째 수를 뽑고 있는지
		
		if(index == R) {
			totalCount++;
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		for(int i=1; i<=N; i++) {
			if(selected[i]) continue; //이조건 되면 다음 i로 넘어가기
			//해당수가 선택되지 않았다면
			
			numbers[index] = i;
			selected[i] = true; //현재 선택한 수 사용한 플래그 처리
			permutation(index+1); //다음자리수로 돌아감
			selected[i] = false; //내 자리로 다시 돌아왔을 때, 계속 교체해보면서 시도하는거. 시도했던 선택지를 사용했다고 했으니까 다시 안썼다고 돌려놔야함
			
		}
	}
}
