package class1113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJ17070_파이프옮기기1 {

	static int N, arr[][], count;
	static int dir[][] = { { 1, 0 }, { 1, 1 }, { 0, 1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk;
		N = Integer.parseInt(bf.readLine());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			tk = new StringTokenizer(bf.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(tk.nextToken());
			}
		}
		// 파이프는 0,0/ 0,1 차지
		// 가로, 세로, 대각선 구분해서
		// 가로면 이동 0, (1: 이동할떄 세방향 다 비어있는지 봐야함)
		// 세로면 이동 1, (2: 이동할떄 세방향 다 비어있는지 봐야함)
		// 대각선이면 이동 0,1,(2: 이동할떄 세방향 다 비어있는지 봐야함)
		rec(0, 0, 0, 1);
		System.out.println(count);
	}

	private static void rec(int sx, int sy, int ex, int ey) {
		if (ex == N - 1 && ey == N - 1) {
			count++; // 도달한 경우의 수 세기
			return;
		}

		if (sx == ex) { // 파이프가 가로로 놓여져있음
			// 오른쪽으로 밀어보기
			if (ey + 1 < N && arr[ex][ey + 1] == 0) { // 옮길 수 있으면
				rec(ex, ey, ex, ey + 1); // 오른쪽으로 밀고 재귀
			}
		} else if (sy == ey) { // 파이프가 세로로 놓여져 있음
			if (ex + 1 < N && arr[ex + 1][ey] == 0) {
				rec(ex, ey, ex + 1, ey);
			}
		} else { // 파이프가 대각선으로 놓여져있음
			if (ey + 1 < N && arr[ex][ey + 1] == 0) { // 옮길 수 있으면
				rec(ex, ey, ex, ey + 1); // 오른쪽으로 밀고 재귀
			}
			if (ex + 1 < N && arr[ex + 1][ey] == 0) {
				rec(ex, ey, ex + 1, ey); // 아래로 밀기
			}
		}

		if (check(ex, ey)) {// 대각선으로 밀기 --> 이건 모두 공통
			rec(ex, ey, ex + 1, ey + 1);
		}

	}

	private static boolean check(int x, int y) {
		for (int i = 0; i < dir.length; i++) {
			int nx = x + dir[i][0];
			int ny = y + dir[i][1];

			if (nx >= N || ny >= N || arr[nx][ny] == 1)
				return false;

		}
		return true;
	}
}
