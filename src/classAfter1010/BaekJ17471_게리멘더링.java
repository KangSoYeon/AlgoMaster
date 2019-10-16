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
	static ArrayList<Integer> e1;
	static ArrayList<Integer> e2;
	static boolean[] check;
	
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
				int temp = Integer.parseInt(tk.nextToken())-1;
				connect[i][temp] = 1 ;
				connect[temp][i] = 1;
			}
		}
		int min = 200;
		boolean flag = false;


		
		do {
			if(arr[0] > N/2) {//첫번째 수가 N/2면 그만 하기 
				break;
			}
			System.out.println(Arrays.toString(arr));
			for(int t=1; t<N; t++) {
				
				e1 = new ArrayList<>(); //첫번째 구역
				e2 = new ArrayList<>(); //두번쨰 구역
				
				for(int i=0; i<t; i++) {
					e1.add(arr[i]);
				}
				for(int j=t; j<N; j++) {
					e2.add(arr[j]);
				}
				int p1=0, p2=0;
				
				check = new boolean[N];
				flag = false;
				dfs1(e1.get(0));
				for(int i=0; i<e1.size(); i++) {
					if(!check[e1.get(i)]) { //하나라도 false이면 끝내기, 하나라도 방문 안했으면 
						flag = true;
					}
					p1 += people[e1.get(i)];
				}
				
				if(flag) continue; //다음 케이스로 넘어가기 
				
				check = new boolean[N];
				flag = false;
				dfs2(e2.get(0));
				for(int i=0; i<e2.size(); i++) {
					if(!check[e2.get(i)]) {
						flag = true;
					}
					p2 += people[e2.get(i)];
				}
				
				if(flag) continue;

				//Math.abs(p1-p2)의 최소값 구하기 
				if(min>Math.abs(p1-p2)) {
					min = Math.abs(p1-p2);
				}
			}
			
		}while(next_permutation());
		
		if(min != 200) System.out.println(min);
		else System.out.println(-1);
		
	}
	
	//e1에 있는 배열들 서로 연결되어 있는지 확인하는 함수 
	static void dfs1(int n) {
		check[n] = true;
		for(int i=0; i<N; i++) {
			if(e1.contains(i) && connect[n][i]==1 && !check[i]) { //n과 연결되어 있고, check간적 없으면 
				check[i] = true;
				dfs1(i); //여기도 간다 
			}
		}		
	}
	
	static void dfs2(int n) {
		check[n] = true;
		for(int i=0; i<N; i++) {
			if(e2.contains(i) && connect[n][i]==1 && !check[i]) { //n과 연결되어 있고, check간적 없으면 
				check[i] = true;
				dfs2(i); //여기도 간다 
			}
		}		
	}

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
