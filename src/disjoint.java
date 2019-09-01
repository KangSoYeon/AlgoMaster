

import java.util.Arrays;
import java.util.Scanner;

public class disjoint {
	
	private static int parents[];
	
	static void make() { //make set : 모든 원소를 개별적인 집합으로 생성
		Arrays.fill(parents, -1); //더이상 루트가 없다는 뜻 
	}
	
	static int find(int a) {
		if(parents[a]<0)	return a; //자신이 루트이면 자신 리턴
		return parents[a] = find(parents[a]); //나의 부모한테 또 그 루트 찾아오라고 시킴
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int cnt = sc.nextInt();
		parents = new int[N];
		
		make(); //1. make set
		for(int i=0; i<cnt; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			System.out.println(union(a,b));
		}
		
		System.out.println(find(0)+"/"+find(1));
		System.out.println(find(3)+"/"+find(4));
		System.out.println(find(2)+"/"+find(3));
		

	}
}

/*
5
3
0 3
1 4
3 4 
 */

