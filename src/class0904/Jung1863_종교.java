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

public class Jung1863_종교 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		int count=0;
		int N = Integer.parseInt(tk.nextToken());
		int M = Integer.parseInt(tk.nextToken());
		//ArrayList<Integer>[] arr = new ArrayList[N+1];
		HashSet<Integer>[] arr = new HashSet[N+1];
		int[][] all = new int[M][2];
		for(int i=0; i<N+1; i++) {
			arr[i] = new HashSet<>();
		}
		if(M==0) count=N;
	
		for(int i=0; i<M; i++) {
			tk = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(tk.nextToken());
			int b = Integer.parseInt(tk.nextToken());
			
			all[i] = new int[] {a,b};
			
		}
		for(int i=0; i<M; i++) {
			Arrays.sort(all[i]); //앞에께 작은거 뒤에께 큰거 
		}
		Arrays.sort(all, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0]-o2[0];
			}
		});
		
		for(int i=0; i<M; i++) {
			int a = all[i][0];
			int b = all[i][1];
			
			if(i==0) {
				arr[count].add(a);
				arr[count].add(b);
				count++;
			}else {
				boolean flag = false;
				for(int j=0; j<count; j++) {
					if(arr[j].contains(a) || arr[j].contains(b)) {
						arr[j].add(a);
						arr[j].add(b);
						flag = true; //한번도 안돌았으면 
						break;
					}
				}
				
				if(!flag) {
					arr[count].add(a);
					arr[count].add(b);
					count++;
				}
			}
		}
		
//		for(int i=0; i<count; i++) {
//			System.out.println(arr[i]);
//		}
		
		System.out.println(count);
		
	}

}
