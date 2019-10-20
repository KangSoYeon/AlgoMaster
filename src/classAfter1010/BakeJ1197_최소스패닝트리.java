package classAfter1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BakeJ1197_최소스패닝트리 {
	static int V, E, arr[][], parents[];
	static int find(int a) {
		if(parents[a]<0) return a; //return a임 !!!
		return parents[a] = find(parents[a]); //a의 부모를 찾아서 넣기!!!
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		parents[aRoot] = bRoot;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		V = Integer.parseInt(tk.nextToken());
		E = Integer.parseInt(tk.nextToken());
		arr = new int[E][];
		parents = new int[V];
		Arrays.fill(parents, -1);
		int answer = 0;
		for(int i=0; i<E; i++) {
			tk = new StringTokenizer(bf.readLine());
			arr[i] = new int[] { Integer.parseInt(tk.nextToken())-1,
								Integer.parseInt(tk.nextToken())-1,
								Integer.parseInt(tk.nextToken())};
		
			
		}
		//sorting잊지마세용~~~
		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2]-o2[2];
			}
		});
		
		for(int t=0; t<E; t++) {
			if(union(arr[t][0], arr[t][1])) {
				answer += arr[t][2];
			}
		}
		
		System.out.println(answer);
		
	}
}
