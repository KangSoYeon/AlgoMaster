import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16235

public class BaekJ16235_나무제태크_남의코드 {

	private static int N;
	private static int M;
	private static int K;
	private static int[][] A; //현재 양분
	private static int[][] plusA; //매년 더해줄 양분
	private static int[][] dir = {{-1,-1},{-1,0},{-1,1}
								 ,{ 0,-1}       ,{ 0,1}
								 ,{ 1,-1},{ 1,0},{ 1,1}};

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken()); // <=  10 땅.
		M = Integer.parseInt(st.nextToken()); // <= 100 나무.
		K = Integer.parseInt(st.nextToken()); // <=1000 연도.
		
		A = new int[N+1][N+1];
		for (int i = 1; i < N+1; i++) { //양분 처음에 5
			Arrays.fill(A[i], 5);
		}
		plusA = new int[N+1][N+1]; // 겨울에 양분
		ArrayList<Integer>[][] ground = new ArrayList[N+1][N+1];
//		for (int r = 1; r < N+1; r++) {
//			for (int c = 1; c < N+1; c++) {
//			}
//		}
		for (int i = 1; i < N+1; i++) { // 양분 입력
			st = new StringTokenizer(in.readLine());
			for (int j = 1; j < N+1; j++) {
				ground[i][j] = new ArrayList<>();
				plusA [i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < M; i++) { // 나무 입력
			st = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			ground[x][y].add(age);
		}
		//K년 후.
		for (int year = 0; year < K; year++) {
			int[][] newTree = new int[N+1][N+1];
			// 봄 : 나무가 자신의 나이만큼 양분 먹고, 나이 1 증가.
			//		1X1에 있는 양분만 먹기 가능.
			//		하나의 칸에 여러개의 나무가 있다면 나이 어린 나무부터..먹고 나이만큼 못먹는 나무는 양분 못먹고 죽는다.
			for (int r = 1; r < N+1; r++) {
				for (int c = 1; c < N+1; c++) {
					ArrayList<Integer> list = ground[r][c];
					int tmp = 0;
					for (int i = 0; i < list.size(); i++) {
						int age = list.get(i);
						if( A[r][c] >= age) {
							A[r][c] -= age;
							ground[r][c].set(i, age+1);
							if((age+1)%5==0) {//번식할 나무 나중에 계산...
								newTree[r][c]++;
							}
						}else {
							tmp += list.get(i)/2;
							list.remove(i); //죽을 나무 기억..
							i--;
						}
					}
					A[r][c] += tmp + plusA[r][c];
				}
			}
			
			for (int r = 1; r < N+1; r++) {
				for (int c = 1; c < N+1; c++) {
					if(newTree[r][c]==0) continue;
					for (int i = 0; i < 8; i++) {
						int nr = r + dir[i][0];
						int nc = c + dir[i][1];
						if(nr>0 && nr<N+1 && nc>0 && nc<N+1) {
							for (int k = 0; k < newTree[r][c]; k++) {
								ground[nr][nc].add(0,1);
							}
						}
					}
				}
			}
		}
//		for (int r = 1; r < N+1; r++) {
//			for (int c = 1; c < N+1; c++) {
//				System.out.print(A[r][c]+" ");
//			}
//			System.out.println();
//		}
		int count = 0;
		for (int r = 1; r < N+1; r++) {
			for (int c = 1; c < N+1; c++) {
				count += ground[r][c].size();
			}
		}
		//나무 갯수 출력.
		System.out.println(count);
	}

}
/**
5 2 1000
2 3 2 3 2
2 3 2 3 2
2 3 2 3 2
2 3 2 3 2
2 3 2 3 2
2 1 3
3 2 3

*/