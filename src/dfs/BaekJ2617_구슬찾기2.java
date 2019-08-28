package dfs;

import java.io.BufferedReader;
/*
5 4
2 1
4 3
5 1
4 2 
 */
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJ2617_구슬찾기2 {
	static int N, M, limit, smallC, bigC;
	static ArrayList<Integer>[] big ; //인덱스 보다 더큰 수가 저장된 배열의 집합
	static ArrayList<Integer>[] small;
	static boolean[] check;
	
	public static void recsmall(int a) { //a노드보다 작은 갯수들 모두 세기  
		
		check[a]=true;
		if(small[a].isEmpty()) {
			return;
		}
		
		for(int j=0; j<small[a].size(); j++) { //a보다 작은수가 들어간 배열에 
			int temp = small[a].get(j); 
			if(!check[temp]) {
				recsmall(temp); //이거보다 작은걸 또 찾으러간다.
				//smallC++; //그냥 갯수세면 됨(왜 그렇게 add해주는거에 집착했을까,,,,)
			}
			
			for(int i=0; i<small[temp].size();i++) {
				if(!small[a].contains(small[temp].get(i))) {
					small[a].add(small[temp].get(i));
				}
			}
		
		}
	}
	
	public static void recbig(int a) {
		
		check[a]=true; //이거 안넣어서 런타임에러 났따고???????
		//아 check는 밖에 넣어주고,, 아 상관없음 밖에 넣든 안에넣든..
		if(big[a].isEmpty()) {
			return;
		}
		
		for(int j=0; j<big[a].size(); j++) { 
			int temp = big[a].get(j); 
			if(!check[temp]) {
				recbig(temp); 
				//bigC++; //보낸 갯수 = 더 큰수의 갯수  
				//그냥 해당 노드에서 dfs보낸 횟수가 큰수의 갯수인데,,,
			}
			for(int i=0; i<big[temp].size();i++) {
				if(!big[a].contains(big[temp].get(i))) {
					big[a].add(big[temp].get(i));
				}
			}
		
		}
	}



	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		
		 N = Integer.parseInt(tk.nextToken()); //동전의 갯수
		 M = Integer.parseInt(tk.nextToken());
		
		limit = N/2; //limit넘어가는 갯수만큼 크거나 작은게 있으면 절대로 될수 없는 동전 
	
		big = new ArrayList[N+1]; //인덱스 보다 더큰 수가 저장된 배열의 집합
		small = new ArrayList[N+1];
		
		for(int i=0; i<N+1; i++) {
			big[i] = new ArrayList<>();
			small[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			tk = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(tk.nextToken());
			int b = Integer.parseInt(tk.nextToken());
			
			//a>b
			big[b].add(a); //b보다 큰 a
			small[a].add(b); //a보다 큰 b
		}

		int answer=0;
		
		
		for(int i=1; i<=N; i++) {
			check = new boolean[N+1];
			recsmall(i);
					
			check = new boolean[N+1];
			recbig(i);
		}
		
		
//		for(int i=0; i<big.length; i++)
//			System.out.print(big[i]);
//		
//		System.out.println();
//		
//		for(int i=0; i<small.length; i++)
//			System.out.print(small[i]);
		for(int i=0; i<N+1; i++) {
			if(big[i].size()>limit) answer++;
			if(small[i].size()>limit) answer++;
		}
		
		
		System.out.println(answer);
			
	}

}
