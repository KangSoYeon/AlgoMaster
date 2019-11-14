package class1114;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1953_탈주범검거 {
	
	static int N, M, R, C, L, arr[][], answer;
	static boolean check[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk;
		int T = Integer.parseInt(bf.readLine());
		for(int test=1; test<=T; test++) {
			answer = 0;
			tk = new StringTokenizer(bf.readLine());
			N= Integer.parseInt(tk.nextToken());
			M= Integer.parseInt(tk.nextToken());
			R= Integer.parseInt(tk.nextToken());
			C= Integer.parseInt(tk.nextToken());
			L= Integer.parseInt(tk.nextToken()); //탈출에 소요된 시간
			
			arr = new int[N][M];
			check = new boolean[N][M];
			
			for(int i=0; i<N; i++) {
				tk = new StringTokenizer(bf.readLine());
				for(int j=0; j<M; j++) {
					arr[i][j] = Integer.parseInt(tk.nextToken());
				}
			}
			
			bfs();
			
			System.out.println("#"+test+" "+answer);
		}
	}

	private static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {R, C}); //시작점
		check[R][C] = true;
		int time = 0;
		
		while(!q.isEmpty()) {
			int turn = q.size();
			answer+=turn;
			if(++time>=L) break; //L만큼의 시간만 돌기
			
			while(--turn>=0) {
				int[] t = q.poll();
				
				int[][] dir = retdir(arr[t[0]][t[1]]);
				
				for(int d=0, size = dir.length; d<size; d++) {
					int nx = t[0] + dir[d][0];
					int ny = t[1] + dir[d][1];
					
					if(nx<0 || ny<0 || nx>=N || ny>=M || arr[nx][ny]==0) continue;
					
					int[][] dir2 = retdir(arr[nx][ny]);
					
					boolean flag = false;
					if(dir[d][1]==1) {
						for(int i=0; i<dir2.length; i++) {
							if(dir2[i][1]==-1) {
								flag = true;
							}
						}
					}else if(dir[d][1]==-1){
						for(int i=0; i<dir2.length; i++) {
							if(dir2[i][1]==1) {
								flag = true;
							}
						}
					}else if(dir[d][0]==1) {
						for(int i=0; i<dir2.length; i++) {
							if(dir2[i][0]==-1) {
								flag = true;
							}
						}
					}else if(dir[d][0]==-1) {
						for(int i=0; i<dir2.length; i++) {
							if(dir2[i][0]==1) {
								flag = true;
							}
						}
					}
					
					if(!flag) continue; //다음꺼랑 현재꺼랑  연결 안되면
					
					if(!check[nx][ny]) { //파이프가 있고, 안간데면
						q.add(new int[] {nx, ny});
						check[nx][ny] = true; //이미 방문한데라고 체크 
					}
				}
				
			}
		}
		
		
	}
	private static int[][] retdir(int a) {
		int[][] dir;
		switch (a) { //현재 좌표가 무슨 터널이느냐에 따라 이동 다름
		case 1:
			dir = new int[][]{{-1,0}, {1,0}, {0,-1}, {0,1}};
			break;
		case 2:
			dir = new int[][]{{-1,0}, {1,0}};
			break;
		case 3:
			dir = new int[][]{{0,-1}, {0,1}};
			break;
		case 4:
			dir = new int[][]{{-1,0}, {0,1}};
			break;
		case 5:
			dir = new int[][]{{1,0}, {0,1}};
			break;
		case 6:
			dir = new int[][]{{1,0}, {0,-1}};
			break;
		case 7:
			dir = new int[][]{{-1,0}, {0,-1}};
			break;
		default:
			//0인경우
			dir = new int[0][0]; //하나도 안돌면 됨
			break;
		}
		return dir;
	}
}
