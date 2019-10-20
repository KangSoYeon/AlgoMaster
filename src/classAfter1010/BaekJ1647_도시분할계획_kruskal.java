package classAfter1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class BaekJ1647_도시분할계획_kruskal {

	static int N, M;
	static ArrayList<int[]> arr;
	static boolean[] check;
	
	static int parents[];
	
	static int find(int a) {
		if(parents[a]<0) return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) {
			return false;
		}
		parents[bRoot] = aRoot;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tk.nextToken()); //home
		M = Integer.parseInt(tk.nextToken()); //road
		arr = new ArrayList<>();
		//집과 집을 연결하는 인접행렬 만들기  
		
		int answer = 0;
		for(int i=0; i<M; i++) {
			tk = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(tk.nextToken())-1;
			int b = Integer.parseInt(tk.nextToken())-1;
			arr.add(new int[] {a, b, Integer.parseInt(tk.nextToken())});
		}
		
		parents = new int[N];
		Arrays.fill(parents, -1);
		Collections.sort(arr, new Comparator<int[]>() { //세번째 요소로 정렬 
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2]-o2[2];
			}
		});
		
		int size = arr.size();
		int max = Integer.MIN_VALUE;
		for(int i=0; i<size; i++) {
			int a = arr.get(i)[0];
			int b = arr.get(i)[1];
			int val = arr.get(i)[2];
			
			if(union(a, b)) { //a,b둘이 합쳐질 수 있으면  
				answer += val;
				if(val>max) max = val;
			}
		}
		System.out.println(answer-max);
	}	
}
