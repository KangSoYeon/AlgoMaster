package classAfter1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class BaekJ17471_게리멘더링 {
	static int N;
	static int[] arr;
	static int[] people;
	static int[][] connect;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		arr = new int[N];
		people = new int[N];
		connect = new int[N][N];
		
		for(int i=0; i<N; i++) {
			people[i] = Integer.parseInt(tk.nextToken()); //각 구역의 인구
			arr[i] = i; //순서대로 
		}
		
		for(int i=0; i<N; i++) { //인접행렬 만들기 
			tk = new StringTokenizer(bf.readLine());
			int t = Integer.parseInt(tk.nextToken());
			for(int j=0; j<t; j++) {
				connect[i][j]= Integer.parseInt(tk.nextToken());
			}
		}
		
		
		do {
			//System.out.println(Arrays.toString(arr));
			
			ArrayList<Integer> e1 = new ArrayList<>(); //첫번째 구역
			ArrayList<Integer> e2 = new ArrayList<>(); //두번쨰 구역
			
			for(int t=1; t<N; t++) {
				
				e1 = new ArrayList<>(); //첫번째 구역
				e2 = new ArrayList<>(); //두번쨰 구역
				
				for(int i=0; i<t; i++) {
					e1.add(arr[i]);
				}
				for(int j=t; j<N; j++) {
					e2.add(arr[j]);
				}
				
				//System.out.println(e1);
				//System.out.println(e2);
				//첫번째 구역 연결 테스트 --> bfs
				//두번쨰 구역 연결 테스트 
				
				
			}
			
			
			
		}while(next_permutation());
		
	}
	
	//e1에 있는 배열들 서로 연결되어 있는지 확인하는 함수 
	
	
	
	
	static boolean next_permutation() {
		int i= N-1;
		while(i>0 && arr[i] <= arr[i-1]) i--;
		
		if(i==0) return false;
		
		int j = N-1;
		while(arr[i-1]>= arr[j]) j--;
		
		swap(i-1, j);
		
		j = N-1;
		while(i<j) swap(i++, j--);
		
		return true;
	}

	private static void swap(int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
