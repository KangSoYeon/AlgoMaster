package Study0825week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJ15683_감시 {
	
	static int N, M,min;
	static int[][] arr, save;
	static int[][][][] dir = {
			{ {{1,0}}, {{-1,0}}, {{0,1}}, {{0,-1}} }, //1번돌리는 경우
			{ {{0,1}, {0,-1}},   {{1,0}, {-1,0}},   {{0,1}, {0,-1}},   {{1,0}, {-1,0}} }, //2번 돌리는 경우(그냥 두번더하기)
			{ {{-1,0}, {0,1}},   {{-1,0},{0,-1}},   {{1,0}, {0,-1}},   {{1,0}, {0,1}} }, //3번돌리기
			{ {{-1,0}, {0,1}, {0,-1}},   {{1,0}, {-1,0}, {0,1}},   {{1,0}, {0,1}, {0,-1}},   {{1,0}, {-1,0}, {0,-1}}} }; //4번돌리기
	
	static ArrayList<int[]> cctv;
	static int[] selected;

	public static int notC() {
		int count=0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(arr[i][j]==0) count++;
			}
		}
		return count;
	}
	
	
	//중복순열
	//n파이r
	public static void pick(int idx) {
		
		if(idx==cctv.size()) { //다 뽑았으면 
			//각 레이저에 돌려볼 경우의 수 출력 
			//레이저 수만큼 돌리면서
			//selected배열 처음ㅂ터
			
			for(int i=0; i<N; i++) { //초기화 
				for(int j=0; j<M; j++) {
					arr[i][j] = save[i][j];
				}
			}
			
				
			for(int i=0; i<cctv.size(); i++) {
				int r = cctv.get(i)[2]; //몇번 cctv인지 
				r--;
				for(int k=0; k<dir[r][selected[i]].length; k++) {
					int x = cctv.get(i)[0];
					int y = cctv.get(i)[1];
					
					while(true) {
						int nx = x + dir[r][selected[i]][k][0];
						int ny = y + dir[r][selected[i]][k][1];
						
						if(nx<0 || ny<0 || nx>=N || ny>=M || arr[nx][ny]==6) break;
						
						if(arr[nx][ny] == 0) arr[nx][ny] = 9;
						
						x = nx;
						y = ny;
												
					}
				}
			}
			
//			System.out.println(Arrays.toString(selected));
//			for(int i=0; i<N; i++) {
//				for(int j=0; j<M; j++){
//					System.out.print(arr[i][j]+" ");
//				}
//				System.out.println();
//			}
			min = Math.min(min, notC());
			//System.out.println(min);
		
			return;
		}
		
		for(int i=0; i<4; i++) { //각 요소마다 4가지 경우의수 
			selected[idx] = i;
			pick(idx+1);
		}
		
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());
		arr = new int[N][M];
		save = new int[N][M];
		cctv = new ArrayList<>();
		min = Integer.MAX_VALUE;
		
		ArrayList<int[]> cctv5 = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			tk = new StringTokenizer(bf.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j]=Integer.parseInt(tk.nextToken());
				save[i][j] = arr[i][j];
				if(arr[i][j] == 5) {
					cctv5.add(new int[] {i, j, arr[i][j]});
				}else if(arr[i][j] != 6 && arr[i][j] != 0 && arr[i][j] != 5) {
					cctv.add(new int[] {i, j, arr[i][j]}); //레이저의 좌표와, 어떤레이저인지 넣음 
				}
			}
		}
		
		selected = new int[cctv.size()]; //뽑은 배열들 
		
		
		for(int i=0; i<cctv5.size(); i++) { //5번레이저는 쏴놓고 시작하기 
			for(int k=0; k<dir[0].length; k++) {
				int x = cctv5.get(i)[0]; //다시 시작점으로 초기화 
				int y = cctv5.get(i)[1];
				
				while(true) {
					int nx = x + dir[0][k][0][0]; //1번이나 5번이나 같음 
					int ny = y + dir[0][k][0][1];
					
					if(nx<0 || ny<0 || nx>=N || ny>=N || arr[nx][ny]==6) break;
					
					//그냥 허공일때만 
					if(arr[nx][ny]==0) {
						arr[nx][ny] = 9; //감시영역 9 로 칠하고
						save[nx][ny] = 9;
					}
					
					x = nx;
					y = ny; //다음순서로 넘어가기 
				}
			}
		}
		
		
		pick(0);
		
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<N; j++){
//				System.out.print(arr[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		System.out.println(min);
		
	}

}
