package classAfter1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_16235_나무재테크_최현정 {
	public static class M {
		int foods;
		ArrayList<Integer> trees; //나무 나이가 들어옴  list의 길이는 나무의 총 개수
		ArrayList<Integer> dead;
		public M() {
			this.foods = 5;
			trees = new ArrayList<>();
		}
		public int getFoods() {
			return foods;
		}
		public void setFoods(int foods) {
			this.foods = foods;
		}
		public ArrayList<Integer> getTrees() {
			return trees;
		}
		public void setTrees(ArrayList<Integer> trees) {
			this.trees = trees;
		}
		public ArrayList<Integer> getDead() {
			return dead;
		}
		public void setDead(ArrayList<Integer> dead) {
			this.dead = dead;
		}
		@Override
		public String toString() {
			return "M [foods=" + foods + ", trees=" + trees + ", dead=" + dead + "]";
		}
		
		
		
	}
	
	static int[][] food;
	static M[][] map;
	static int N;
	static int[] dx = {-1,-1,-1,0,0,1,1,1};
	static int[] dy = {-1,0,1,-1,1,-1,0,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		food = new int[N][N];
		map = new M[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				food[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = new M();
			}
		}// 양분 정보
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int tmp = Integer.parseInt(st.nextToken());
			map[r][c].getTrees().add(tmp);
		}//나무 정보
		
		for(int i=0; i<K; i++) { //K년
			spring();
			summer();
			fall();
			winter();
		}
		
		int cnt =0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				cnt+= map[i][j].getTrees().size();
			}
		}
		
		System.out.println(cnt);
	}
	private static void winter() {
		//땅에 양분 추가
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int foods = map[i][j].getFoods()+food[i][j];
				map[i][j].setFoods(foods);
			}
		}
	}
	private static void fall() {
		//나무 번식
		//번식하는 나무는 나이가 5의 배수
		//인접한 8개의 칸에 나이가 1인 나무가 생김
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				ArrayList<Integer> list = map[i][j].getTrees();
				for(int k=0, size=list.size(); k<size; k++) {
					if(list.get(k)%5==0) {
						
						for(int d=0; d<8; d++) {
							int nr = i+dx[d];
							int nc = j+dy[d];
							if(nr>=0 && nc>=0 && nr<N && nc<N) {
								map[nr][nc].getTrees().add(1);
							}
						}
						
					}
				}
			}
		}
	}
	private static void summer() {
		//봄에 죽은 나무가 양분으로 변함
		//각각의 죽은 나무마다 나이/2 값이 양분으로 추가됨
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int foods = map[i][j].getFoods();
				ArrayList<Integer> dead = map[i][j].getDead();
				for(int k=0, size=dead.size();k<size; k++) {
					foods += (dead.get(k)/2);
				}
				map[i][j].setFoods(foods);
			}
		}
	}
	private static void spring() {
		//나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가
		//각각의 나무는 나무가 있는 크기의 칸에 있는 양분만 먹을 수 있음
		//여러 나무가 있다면 나이가 어린 나무부터 양분 먹음
		//자신의 나이만큼 양분을 먹을 수 없으면 나무가 죽음
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int foods = map[i][j].getFoods();
				ArrayList<Integer> list = map[i][j].getTrees();
				ArrayList<Integer> dead = new ArrayList<>();
				Collections.sort(list);
				for(int k=0; k<list.size();k++) {
					int old = list.get(k);
					if(old<=foods) { //양분 먹을 수 있음
						list.set(k, old+1);
						foods -=old;
					}else {
						list.remove(k--); // 나무 리스트에서 삭제
						dead.add(old); // 죽은 나무 리스트에 추가
					}
				}
				map[i][j].setDead(dead);
				map[i][j].setFoods(foods);
			}
		}
	}
	

}
