
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class dijkstra {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		int[][] arr = new int[N][N];
		
		StringTokenizer tk;
		for(int i=0; i<N; i++) {
			tk = new StringTokenizer(bf.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(tk.nextToken());
			}
		} //인접행렬 생성 
		
		int start = 0;
		int end = N-1;
		int[] distance = new int[N];
		int[] path = new int[N];
		boolean[] visited = new boolean[N];
		
		Arrays.fill(distance, Integer.MAX_VALUE); //처음 거리는 무한값으로 초기화 
		distance[start]=0; //시작점에서이미 간거리는 0  
		
		int min = Integer.MAX_VALUE;
		int minIdx=0;
		for(int i=0; i<N; i++) {
			//step1 미방문정점 중 최적비용이 최소인 정점 찾기 
			min = Integer.MAX_VALUE;
			for (int j = 0; j < N; j++) {
				if(!visited[j] && distance[j] <min) {
					min = distance[j];
					minIdx = j;
				}
			}
			//step2 선택정점 방문하여 경유지로 고려해본다.
			// 미방문 정점들로 가는 최적비용을 최적화
			
			visited[minIdx] = true;
			if(minIdx == end) break;
			
			for(int j=0; j<N; j++) {
				if(!visited[j] && arr[minIdx][j]!=0
						&& min + arr[minIdx][j] < distance[j]) {
					distance[j] = min + arr[minIdx][j];
					path[j] = minIdx; //j정점으로 온 그전 index는 path
				}
			}
			
		}
		System.out.println(distance[end]);
		System.out.println(Arrays.toString(path));

	}

}
/*
5
0 2 2 5 9
2 0 3 4 8
2 3 0 7 6
5 4 7 0 5
9 8 6 5 0
==> 8
4
0 94 53 16 
79 0 24 18 
91 80 0 98 
26 51 92 0
==> 16
7
0   2   8   5   9  15  20
2   0   5   4   7  10  16
8   5   0   7   6   4  10
5   4   7   0  15   8   9
9   7   6  15   0  11  13
15 10   4   8  11   0   6
20 16  10   9  13   6   0
==> 14
*/