import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Prim {
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
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		boolean[] visited = new boolean[N];
		
		visited[0] = true; //첫번째 정점 선택
		list.add(0);
		int result=0;
		
		int min = Integer.MAX_VALUE; //max값 넣고 출발 
		int minIdx = 0;
		for(int i=0; i<N-1; i++) { //이미 정점 하나 선택했으므로 정점수-1 만큼 돌게 
			min = Integer.MAX_VALUE;
			// 1. 이미 방문한 모든 정점 기준으로 방문하지 않은 정점으로의 최소비용을 갖는 경우를 선택
			for(int cur: list) {
				//cur을 기준으로 인접행렬 보며 방문하지 않은 정점중에 최소값 찾기 
				for(int j=0; j<N; j++) {
					if(!visited[j] && arr[cur][j]!=0 && arr[cur][j] <min) {
						min = arr[cur][j];
						minIdx = j;
					}
			
				}	
			}
			visited[minIdx] = true;
			list.add(minIdx);
			result += min;
			
		}
		
		System.out.println(result);
		
	}
}

/*
5
0 5 10 8 7 
5 0 5 3 6 
10 5 0 1 3 
8 3 1 0 1 
7 6 3 1 0
==> 10
7
0 3 17 6 0 0 0
3 0 0 5 0 0 12
17 0 0 0 10 8 0
6 5 0 0 9 0 0 
0 0 10 9 0 4 2 
0 0 8 0 4 0 14
0 12 0 0 2 14 0 
==>31
 */