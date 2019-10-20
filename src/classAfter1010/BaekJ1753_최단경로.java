package classAfter1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJ1753_최단경로 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		int V = Integer.parseInt(tk.nextToken()); //정점수 
		int E = Integer.parseInt(tk.nextToken()); //간선수 
		int[][] arr = new int[V][V]; 
		
		int K = Integer.parseInt(bf.readLine());
		for(int i=0; i<E; i++) {
			tk = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(tk.nextToken())-1;
			int b = Integer.parseInt(tk.nextToken())-1;
			arr[a][b] = Integer.parseInt(tk.nextToken());
		}
		
		int[] distance = new int[V];
		boolean[] visited = new boolean[V];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[0] = 0; //시작점은 0  
		
		for(int i=0; i<V; i++) {
			
			//모든 정점들중에서 가장 짧은 distance 값 찾기 
			int min = Integer.MAX_VALUE;
			int idx=0;
			for(int j=0; j<V; j++) {
				if(!visited[j] && distance[j]<min) {
					min = distance[j];
					idx = j;
				}
			}
			
			visited[idx] = true;
			
			if(idx==V-1) break;
			
			//idx를 경유지로 해서 j에 가는게,
			//시작점에서 바로 j에 가는거보다 짧으면 업데이트   
			for(int j=0; j<V; j++) {
				if(!visited[j] && arr[idx][j] !=0 
						&& min + arr[idx][j] <distance[j]) {
					distance[j] = min+arr[idx][j];
				}
					
			}
			
		}
		for(int i: distance) {
			if(i == Integer.MAX_VALUE)
				System.out.println("INF");
			else
				System.out.println(i);
			
		}
				

	}

}
