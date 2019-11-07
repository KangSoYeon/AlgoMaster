package class1106;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution5644_무선충전 {
	static int M, A, a[], b[];
	static cell[][] arr;
	static int[][] dir = { {0,0}, {-1, 0}, {0,1}, {1,0}, {0,-1} }; //0,1,2,3,4
	
	static class cell{
		ArrayList<int[]> BC = new ArrayList<>();
		
		public cell() {}

		public cell(ArrayList<int[]> bC) {
			super();
			BC = bC;
		}
		public ArrayList<int[]> getBC() {
			return BC;
		}
		public void setBC(ArrayList<int[]> bC) {
			BC = bC;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk;
		int T = Integer.parseInt(bf.readLine());
		for(int test=1; test<=T; test++) {
			int answer =0;
			arr = new cell[10][10]; //cell형으로 이루어진 판 
			
			for(int i=0; i<10; i++) {
				for(int j=0; j<10; j++) {
					arr[i][j] = new cell();
				}
			}
			
			tk = new StringTokenizer(bf.readLine());
			M = Integer.parseInt(tk.nextToken()); //이동시간
			A = Integer.parseInt(tk.nextToken()); //BC 갯수
			a = new int[M];
			b = new int[M];
			
			tk = new StringTokenizer(bf.readLine()); //a의 이동정보
			for(int i=0; i<M; i++) {
				a[i] = Integer.parseInt(tk.nextToken());
			}
			
			tk = new StringTokenizer(bf.readLine()); //b의 이동정보
			for(int i=0; i<M; i++) {
				b[i] = Integer.parseInt(tk.nextToken());
			}
			
			for(int t=0; t<A; t++) { //AP의 정보 들어오기 
				tk = new StringTokenizer(bf.readLine());
				int x = Integer.parseInt(tk.nextToken())-1; //0,0 시작으로 바꿔주기
				int y = Integer.parseInt(tk.nextToken())-1;
				int c = Integer.parseInt(tk.nextToken()); //충전범위
				int p = Integer.parseInt(tk.nextToken()); //성능
				
				//하나의 충전소마다 범위 계산해서 성능값을 그 위치의 어레이 리스트에 넣어주기 
				getArea(t, y ,x, c, p); //t는 충전소의 인덱스 , x,y좌표 반대였음
			}
			
			int ax=0, ay=0;
			int bx=9, by=9; //각각의 시작위치
			
			for(int time=-1; time<M; time++) { //시간의 흐름에 따라 A와 B가 움직인다.
				if(time!=-1) { //맨 처음 좌표도 계산해 줘야함 
					ax += dir[a[time]][0];
					ay += dir[a[time]][1]; //a이동
					
					bx += dir[b[time]][0];
					by += dir[b[time]][1]; //b이동
				}
				
				ArrayList<int[]> aBC = arr[ax][ay].getBC(); //그 둘의 기지국을
				ArrayList<int[]> bBC = arr[bx][by].getBC(); //모두 가져와서
				//둘이 겹치는 기지국에 접속할 수 있는지 확인
				//각각 정렬 
				int asize = aBC.size();
				int bsize = bBC.size();
				
				if(asize==0 && bsize==0) continue; //계속 이동
				
				if(asize>0) {
					Collections.sort(aBC, new Comparator<int[]>() {
						@Override
						public int compare(int[] o1, int[] o2) { //p로 정렬
							return o2[1]-o1[1];
						}
					});
					if(bsize==0) {
						answer += aBC.get(0)[1];
						continue;
					}
				}
				if(bsize>0) {
					Collections.sort(bBC, new Comparator<int[]>() {
						@Override
						public int compare(int[] o1, int[] o2) { //p로 정렬
							return o2[1]-o1[1]; //내림차순 정렬 
						}
					});
					if(asize==0) {
						answer += bBC.get(0)[1];
						continue;
					}
				}
				
				int[] at = aBC.get(0);
				int[] bt = bBC.get(0);
				if(at[0] == bt[0]) { //둘의 제일 큰 기지국이 같으면
					answer += at[1]; //일단 더하고 
					if(asize==1 && bsize>1) {
						answer += bBC.get(1)[1];
					}else if(asize>1 && bsize==1) {
						answer += aBC.get(1)[1];
					}else if(asize>1 && bsize>1){
						if(aBC.get(1)[1]>=bBC.get(1)[1]) {
							answer += aBC.get(1)[1];
						}else {
							answer += bBC.get(1)[1];
						}
					}
				}else { //그냥 각자 젤큰ㄴ거 빼서 사용하면 됨
					answer += at[1];
					answer += bt[1];
				}
			}
			System.out.println("#"+test+" "+ answer);
		}
	}

	private static void getArea(int t,int x, int y, int c, int p) {
		//초기좌표
		arr[x][y].getBC().add(new int[] {t,p}); //해당 좌표의 성능을 어레이리스트에 넣음
		
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				if(x==i && y==j) continue;
				if(Math.abs(x-i)+ Math.abs(y-j) <=c) {
					arr[i][j].getBC().add(new int[] {t,p}); 
				}
			}
		}
	}
}
