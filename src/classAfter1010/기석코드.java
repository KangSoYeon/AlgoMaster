package classAfter1010;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 기석코드 {
	static boolean[] visited, check;
	static int min, N, map[][], ningGen[], count, aPeople;
	static ArrayList<Integer> A, B;
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 구역개수
		ningGen = new int[N]; // 구역별 인구수
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			ningGen[i] = Integer.parseInt(st.nextToken());
		}
// ArrayList<Integer>[] list = new ArrayList[N];
// for (int i = 0; i < N; i++) {
// list[i] = new ArrayList<>();
// }
//
// for (int i = 0; i < N; i++) {
// st = new StringTokenizer(br.readLine()," ");
// int num1 = Integer.parseInt(st.nextToken());
// }
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int num1 = Integer.parseInt(st.nextToken());
			for (int j = 0; j < num1; j++) {
				int num2 = Integer.parseInt(st.nextToken()) - 1;
				map[i][num2] = 1;
				map[num2][i] = 1;
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		min = Integer.MAX_VALUE;
		visited = new boolean[N];
		for (int i = 1; i < 1 << N; i++) {
			for (int k = 0; k < N; k++) {
				if ((i & 1 << k) != 0) {
// System.out.print(k+" ");
					visited[k] = true;
				}
			}
// System.out.println(Arrays.toString(visited));
			check= new boolean[N];
			A = new ArrayList<>();
			B = new ArrayList<>();
			for(int j=0; j<N; j++) {
				if(visited[j]) {
					A.add(j);
				}else {
					B.add(j);
				}
			}
			
			
			dfsA(A.get(0));
			
			visited = new boolean[N];
		}
		System.out.println(min);
	}

// private static void check() {
// int cnt=0; // true갯수
// for (int i = 0; i < N; i++) {
// if(visited[i]) cnt++;
// }
//
// if(cnt==0||cnt==N) return;
//
// if(cnt==1) {
// if(!dfsf()) return;
// int tsum = 0;
// int fsum = 0;
// int cha=0;
// for (int i = 0; i < N; i++) {
// if(visited[i]) tsum+=ningGen[i];
// if(!visited[i]) fsum+=ningGen[i];
// }
// cha=Math.abs(tsum-fsum);
// System.out.println(cha);
// if(cha<min) min=cha;
// }else if(cnt==N-1) {
// if(!dfst()) return;
// int tsum = 0;
// int fsum = 0;
// int cha=0;
// for (int i = 0; i < N; i++) {
// if(visited[i]) tsum+=ningGen[i];
// if(!visited[i]) fsum+=ningGen[i];
// }
// cha=Math.abs(tsum-fsum);
// System.out.println(cha);
// if(cha<min) min=cha;
//
// }else {
// if(!dfst()) return;
// if(!dfsf()) return;
//
// int tsum = 0;
// int fsum = 0;
// int cha=0;
// for (int i = 0; i < N; i++) {
// if(visited[i]) tsum+=ningGen[i];
// if(!visited[i]) fsum+=ningGen[i];
// }
// cha=Math.abs(tsum-fsum);
// System.out.println(cha);
// if(cha<min) min=cha;
//
// }
//
// }

	//A그룹검사 
	private static void dfsA( int n) {
		check[n]= true;
		count++;
		aPeople += ningGen[n];
		
		for(int i=0; i<A.size(); i++) {
			int next = A.get(i);
			if(map[next][n]==1 && !check[n]) {
				dfsA(next);
			}
		}
	}
	
	
	private static boolean dfsf() {
		boolean ff = false;
		int cntf = 0;
		if (!visited[0])
			cntf = 1;
		for (int i = 1; i < N; i++) {
			if (visited[0] != visited[i])
				ff = true;
			if (!visited[i])
				cntf++;
		}
		if (!ff)
			return false;
		if (cntf == 1)
			return true;
		int num = -1;
		boolean flag = true;
		out: for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				num = i;
				for (int j = 0; j < N; j++) {
					if (num != j && !visited[j] && map[num][j] != 0) {
						flag = false;
						break out;
					}
				}
			}
		}
		return flag;
	}

	private static boolean dfst() {
		boolean ff = false;
		int cntt = 0;
		if (visited[0])
			cntt = 1;
		for (int i = 1; i < N; i++) {
			if (visited[0] != visited[i])
				ff = true;
			if (visited[i])
				cntt++;
		}
		if (!ff)
			return false;
		if (cntt == 1)
			return true;
		int num = -1;
		boolean flag = true;
		out: for (int i = 0; i < N; i++) {
			if (visited[i]) {
				num = i;
				for (int j = 0; j < N; j++) {
					if (num != j && visited[j] && map[num][j] != 1) {
						flag = false;
						break out;
					}
				}
			}
		}
		return flag;
	}

	private static boolean dfs1(int node) {
		boolean ff = false;
		int cntt = 0;
		if (visited[0])
			cntt = 1;
		for (int i = 1; i < N; i++) {
			if (visited[0] != visited[i])
				ff = true;
			if (visited[i])
				cntt++;
		}
		if (!ff)
			return false;
		if (cntt == 1)
			return true;
		int num = -1;
		boolean flag = true;
		out: for (int i = 0; i < N; i++) {
			if (visited[i]) {
				num = i;
				for (int j = 0; j < N; j++) {
					if (num != j && visited[j] && map[num][j] != 1) {
						flag = false;
						break out;
					}
				}
			}
		}
		return flag;
	}
}