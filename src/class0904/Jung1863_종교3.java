package class0904;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Jung1863_종교3 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		int count=0;
		int N = Integer.parseInt(tk.nextToken());
		int M = Integer.parseInt(tk.nextToken());

		ArrayList<Integer>[] arr = new ArrayList[N+1];

		int[][] all = new int[M][2];
		
		for(int i=0; i<N+1; i++) {
			arr[i] = new ArrayList<>();
		}
	
		if(M==0) count=N;
	
		for(int i=0; i<M; i++) {
			tk = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(tk.nextToken());
			int b = Integer.parseInt(tk.nextToken());
			
			arr[a].add(b);
			arr[b].add(a);
		}
		
		
		
		
		
		
		
		System.out.println(count);
		
	}

}
