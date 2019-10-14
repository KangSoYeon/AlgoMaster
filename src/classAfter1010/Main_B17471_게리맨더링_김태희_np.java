package classAfter1010;



import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B17471_게리맨더링_김태희_np {

	static int N,half,countA,countB,pA,pB,visited ,P[],MIN;
	static int[][] map;
	static ArrayList<Integer> aList,bList;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		
		N = Integer.parseInt(in.readLine());
		half = N/2;
		map = new int[N][N];
		P = new int[N];
		MIN = Integer.MAX_VALUE;
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for (int i = 0; i < N; i++) {
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0,j=0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int cnt =  Integer.parseInt(st.nextToken());
			for (int c = 0; c < cnt; c++) {
				j =  Integer.parseInt(st.nextToken())-1;
				map[j][i] = map[i][j] = 1;
			}
		}
		
		for (int i = 1; i <= half; i++) {
			divide(i);
		}
		System.out.println(MIN==Integer.MAX_VALUE?-1:MIN);
	}

	static int np[];
	private static void divide(int target) {
		np = new int[N];
		int c = 0, i=N-1;
		while(c++<target) np[i--] = 1; //갯수만큼 1넣고 
		
		do {
			compare();			
		}while(nextPermutation());
		
	}

	private static boolean nextPermutation() {
		
		int i = N-1;
		while(i>0 && np[i-1]>= np[i]) --i;
		
		if(i==0) return false;
		
		int j = N-1;
		while (np[i-1] >= np[j]) --j;
		
		int temp = np[i-1];
		np[i-1] = np[j];
		np[j] = temp;
		
		j = N-1;
		while(i<j) {
			temp = np[i];
			np[i] = np[j];
			np[j] = temp;
			++i; --j;
		}
		return true;
	}
	
	private static void compare() {
		aList = new ArrayList<Integer>();
		bList = new ArrayList<Integer>();
		for (int i = 0; i < N; i++) {
			if(np[i]>0) aList.add(i);
			else	bList.add(i);
		}
		countA = countB = pA = pB = 0;
		visited = 0;
		dfs(aList,aList.get(0),true);
//		bfs(aList,true);
		if(countA != aList.size()) return;
		
		visited = 0;
		dfs(bList,bList.get(0),false);
//		bfs(bList,false);
		if(countB != bList.size()) return;
		
		if(MIN > Math.abs(pA - pB)) MIN = Math.abs(pA - pB);
		
	}

	private static void dfs(ArrayList<Integer> list,int v,boolean type) {
		visited |= 1<<v;
		if(type) {
			countA++;
			pA += P[v];
		}else {
			countB++;
			pB += P[v];
		}
		
		for (int i = 0; i < N; i++) {
			if(list.contains(i) && map[v][i]>0 && (visited&1<<i)==0) {
				dfs(list,i,type);
			}
		}
	}
	private static void bfs(ArrayList<Integer> list,boolean type) {
		
		Queue<Integer> queue = new LinkedList<Integer>();
		int start = list.get(0);
		queue.offer(start);
		visited |= 1<<start;
		
		while(!queue.isEmpty()) {
			int v = queue.poll();
			if(type) {
				countA++;
				pA += P[v];
			}else {
				countB++;
				pB += P[v];
			}
			
			for (int i = 0; i < N; i++) {
				if(list.contains(i) && map[v][i]>0 && (visited&1<<i)==0) {
					visited |= 1<<i;
					queue.offer(i);
				}
			}
		}
	}
}
