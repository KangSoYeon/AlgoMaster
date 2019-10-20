package classAfter1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class BaekJ1647_도시분할계획_prim {

	static int N, M, arr[][];
	static ArrayList<Integer> been;
	static boolean[] check;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tk.nextToken()); //home
		M = Integer.parseInt(tk.nextToken()); //road
		//집과 집을 연결하는 인접행렬 만들기  
		arr = new int[N][N];
		int answer = 0;
		for(int i=0; i<M; i++) {
			tk = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(tk.nextToken())-1;
			int b = Integer.parseInt(tk.nextToken())-1;
			arr[a][b] = arr[b][a] = Integer.parseInt(tk.nextToken()); //집과집을 연결하는다리 유지
		}
		
		//그룹을 두그룹으로 나누고
		//각각을 prim으로 돌리기
		//가능한 경우에만-> 세운 다리유지비 비교, 최소값 구하기
		
		ArrayList<Integer> been = new ArrayList<>();
		check = new boolean[N];
		
		check[0] = true;
		been.add(0);
		int min = Integer.MAX_VALUE;
		int maxRoad = Integer.MIN_VALUE;
		int idx = 0;
		for(int t=0; t<N-1; t++) { //아직 안 방문한 노드 수만큼   
			min = Integer.MAX_VALUE;
			for(Integer i: been) {
				for(int j=0; j<N; j++) {
					if(arr[i][j]!=0 && !check[j] && arr[i][j]<min) {
						min = arr[i][j];
						idx = j;
					}
				}
			}
			
			if(!been.contains(idx)) {
				been.add(idx);
				check[idx]=true;
				answer += min;
				if(min>maxRoad) {
					maxRoad = min; //추가된 로드중에 가장 값이 큰길 찾기  
				}
			}
			
		}
		
		System.out.println(answer - maxRoad);
		
	}	




}
