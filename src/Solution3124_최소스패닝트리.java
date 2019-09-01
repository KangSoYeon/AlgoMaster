import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution3124_최소스패닝트리 {
	
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

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		StringTokenizer tk;
		
		for(int test =1; test<=T; test++) {
			int answer =0;
			tk = new StringTokenizer(bf.readLine());
			int V = Integer.parseInt(tk.nextToken());
			int E = Integer.parseInt(tk.nextToken());
			parents = new int[V];
			make();
			ArrayList<int[]> arr = new ArrayList<>();
		
			for(int i=0; i<E; i++) {
				tk = new StringTokenizer(bf.readLine());
				int a = Integer.parseInt(tk.nextToken());
				int b = Integer.parseInt(tk.nextToken());
				int c = Integer.parseInt(tk.nextToken());
				
				arr.add(new int[] {a, b, c});
				
			}
			
			//가중치로 sort
			Collections.sort(arr, new Comparator<int[]>() {
				public int compare(int[] o1, int[] o2) {
					return o1[2]-o2[2];
				}
			});
			
			
			for(int i=0; i<E; i++) { //가중치가 낮은 간선부터 트리 증가 
				//이미 연결되어 있으면 
				if(find(arr.get(i)[0]) == find(arr.get(i)[1])) continue;
				
				answer += arr.get(i)[2]; //맨처음 제일 작은 값 넣고 
				union(arr.get(i)[0], arr.get(i)[1]); //두개 잇는다  
				
			}
			
			System.out.println("#"+test+" "+answer);
			
		}

	}

}
