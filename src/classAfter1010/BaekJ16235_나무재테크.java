package classAfter1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BaekJ16235_나무재테크 {
	static class mm { //map의 한칸 한칸 
		ArrayList<Integer> tree= new ArrayList<>();; //한칸에 있는 나무, 각 나무는 나이 값을 가지고 있음
		ArrayList<Integer> dead = new ArrayList<>(); //한칸에 죽은 나무 수 , 각 나무는 나이 값을 가지고 있음 
		int food;
				
		public mm(){
			food = 5; //처음에 생성했을 때 하나의 땅에는 5의 양분이 맥스
		}
		public ArrayList<Integer> getTree() {
			return tree;
		}
		public void setTree(ArrayList<Integer> tree) {
			this.tree = tree;
		}
		public ArrayList<Integer> getDead() {
			return dead;
		}
		public void setDead(ArrayList<Integer> dead) {
			this.dead = dead;
		}
		public int getFood() {
			return food;
		}
		public void setFood(int food) {
			this.food = food;
		}
		@Override
		public String toString() {
			return "mm [tree=" + tree + ", dead=" + dead + ", food=" + food + "]";
		}
		
	}
	static int N, M, K, feed[][];
	static mm[][] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());
		K = Integer.parseInt(tk.nextToken());
		feed = new int[N][N]; //겨울에 추가되는 양분의 양
		arr = new mm[N][N]; //arr칸 하나하나가 mm형
		
		for(int i=0; i<N; i++) {
			tk = new StringTokenizer(bf.readLine());
			for(int j=0; j<N; j++) {
				feed[i][j] = Integer.parseInt(tk.nextToken());
				arr[i][j] = new mm(); //한칸 한칸 생성하기 
			}
		}
		
		for(int i=0; i<M; i++) {
			tk = new StringTokenizer(bf.readLine());
			int[] temp = {Integer.parseInt(tk.nextToken())-1 //x좌표
						, Integer.parseInt(tk.nextToken())-1 //y좌표
						, Integer.parseInt(tk.nextToken())};//age
						
			arr[temp[0]][temp[1]].getTree().add(temp[2]); //해당 좌표에 존재하는 나무를 추가하며 나무의 나이를 넣음 
		}
		
		for(int year=0; year<K; year++) { //각해가 지날때
			spring();
			summer();
			fall();
			winter();
		}
		
		int answer=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				answer += arr[i][j].getTree().size();
			}
		}
		
		System.out.println(answer);
	}


	private static void spring() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				ArrayList<Integer> trees = arr[i][j].getTree(); //현재 자리에 있는 tree의 갯수들 
				ArrayList<Integer> dead = arr[i][j].getDead();
				int food = arr[i][j].getFood(); //현재 위치에 있는 양분의 양 
				//Collections.sort(trees); //나이가 어린 순으로 정렬
				
				for(int t=trees.size()-1; t>=0; t--) { //tree의 갯수만큼, 뒤에서부터 , 무조건 뒤에 
					int nt = trees.get(t); //현재트리 부터 양분을 먹자 
					if(food>=nt) {
						trees.set(t, nt+1); //해당 트리가 나이를 한살 더먹는다~
						food -= nt;
					}else {
						dead.add(nt); //얘는 이제 죽는다~
						trees.remove(t); //원래있던 트리에서 제거한다~빠지고 하나씩 밀리니까 1제거해주기 , 뒤에서부터하면 할필요 없음 
					}
				}
				
				//arr[i][j].setTree(trees); //변경된 tree로 다시 설정 --> reference라서 안바꿔줘도 됨
				arr[i][j].setDead(dead); //변경된 dead로 다시 설정
				arr[i][j].setFood(food);
				
			}
		}
		
	}

	private static void summer() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				ArrayList<Integer> dead = arr[i][j].getDead(); //죽은 나무들 가져오기 
				int food = arr[i][j].getFood(); //현재 양분
				for(int d=0; d<dead.size(); d++) {
					food += dead.get(d)/2; //죽은 나무들의 나이 가져오기
				}
				arr[i][j].setFood(food); //마지막에 현재 양분 바꿔주기
				dead = new ArrayList<>();
				arr[i][j].setDead(dead); //죽은 나무 사라지기 
			}
		}
	}
	
	static int[][] dir = {{1,1}, {1,-1}, {-1,-1}, {-1,1}, {-1,0}, {0,-1}, {1,0}, {0,1}};
	private static void fall() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				ArrayList<Integer> trees = arr[i][j].getTree();
				for(int t=0; t<trees.size(); t++) {
					int age = trees.get(t); //해당 나무의 나이
					if(age%5==0 && age!=0) { //5의 배수이면 
						for(int d=0; d<dir.length; d++) { //팔방에 하나짜리 나무 생긴다 
							int nx = i+dir[d][0];
							int ny = j+dir[d][1];
							if(nx>=0 && ny>=0 && nx<N && ny<N) { //범위안에 들면
								arr[nx][ny].getTree().add(1); //1짜리 나무들 생김 
							}
							
						}
					}
				}
			}
		}
	}
	
	private static void winter() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int now = arr[i][j].getFood();
				arr[i][j].setFood(now+feed[i][j]); //양분 추가한다
			}
		}
	}
}
