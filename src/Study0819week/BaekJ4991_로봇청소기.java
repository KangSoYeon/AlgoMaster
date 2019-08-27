package Study0819week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJ4991_로봇청소기 {
	static int[][] dir = {{0,1}, {1,0}, {-1,0}, {0,-1}};
<<<<<<< HEAD
	static int h,w, sx, sy, min;
=======
	static int h,w, sx, sy, max, min;
>>>>>>> branch 'master' of https://github.com/KangSoYeon/AlgoMaster.git
	static ArrayList<int[]> dirts;
	static char[][] arr;
	static int[][] check, cleanW;
<<<<<<< HEAD
=======
	static int[] line;
>>>>>>> branch 'master' of https://github.com/KangSoYeon/AlgoMaster.git
	
	public static void bfs(int x, int y) { //i,j 를 기준으로 최단경로 구하는 bfs
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x,y});
		check[x][y]=1;
		
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			
			for(int k=0; k<dir.length; k++) {
				int sx = temp[0] + dir[k][0];
				int sy = temp[1] + dir[k][1];
				
				if(sx>=0 && sx<w && sy>=0 && sy<h
						&& check[sx][sy]==0 && arr[sx][sy]!='x') {
					check[sx][sy] = check[temp[0]][temp[1]]+1;
					q.offer(new int[] {sx, sy});
					
				}
			}
		}
	}
	
<<<<<<< HEAD
	//dirts.size만큼 갯수를 선택하는거
	static boolean[] used;
	static int[] selected;
	public static void maxLen(int idx) {
		//permutation 
		if(idx==dirts.size()+1) { //모두 선택이 되었으면 
			//0인덱스는 무조건 0으로 선택되었음 
			int len = 0;
			for(int i=0; i<dirts.size(); i++) {
				len += cleanW[selected[i]][selected[i+1]];
			}
		
			if(len<min)  min = len;
			
			return;
		}
		
		for(int i=1; i<=dirts.size(); i++) {
			if(used[i]) continue;
			
			used[i]=true;
			selected[idx] = i; //선택한 배열
			maxLen(idx+1);
			
			used[i]=false;
			
		}
	}
=======
	public static void maxLen(int idx, int count) {
		//permutation 
		if(count==dirts.size()+1) {
			int len=0;
			len += cleanW[0][line[0]];
			for(int i=1; i<line.length-1; i++) {
				len+= cleanW[line[i]][line[i+1]];
			}
			if(min < len) min = len;
			return;
		}
		
		line[idx]=count;
		maxLen(idx+1, count+1);
		maxLen(idx, count+1);
		
	}
	
	
>>>>>>> branch 'master' of https://github.com/KangSoYeon/AlgoMaster.git

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk;
		
		while(true) {
			
			tk = new StringTokenizer(bf.readLine());
			h = Integer.parseInt(tk.nextToken());
			w = Integer.parseInt(tk.nextToken());
			dirts = new ArrayList<>();
			if(w==0 && h==0)	return;
			
			arr = new char[w][h];
			check = new int[w][h]; //최단 거리를 저장할 배열 
<<<<<<< HEAD
=======
			line = new int[dirts.size()];
>>>>>>> branch 'master' of https://github.com/KangSoYeon/AlgoMaster.git
			
			for(int i=0; i<w; i++) {
				String str = bf.readLine();
				for(int j=0; j<h; j++) {
					arr[i][j] = str.charAt(j);
					if(arr[i][j]=='o') { //시작점 
						sx = i;
						sy = j;
					}else if(arr[i][j]=='*') {
						dirts.add(new int[] {i,j});
					}
				}
			}
			
			cleanW = new int[dirts.size()+1][dirts.size()+1]; //더러운지점과 깨끗한 지점 가는 최단경로 배열
			
			bfs(sx, sy); //출발지와의 거리들 
<<<<<<< HEAD
			
			boolean never = false;
=======
	//		
	//		for(int i=0; i<arr.length; i++) {
	//			System.out.println(Arrays.toString(check[i]));
	//		}
>>>>>>> branch 'master' of https://github.com/KangSoYeon/AlgoMaster.git
			
			for(int i=0; i<dirts.size(); i++) {
				int[] temp = dirts.get(i);
<<<<<<< HEAD
				cleanW[0][i+1] = check[temp[0]][temp[1]]-1;
				cleanW[i+1][0] = check[temp[0]][temp[1]]-1;
				if(cleanW[0][i+1]==-1) never = true;
=======
				cleanW[0][i+1] = check[temp[0]][temp[1]];
				cleanW[i+1][0] = check[temp[0]][temp[1]];
>>>>>>> branch 'master' of https://github.com/KangSoYeon/AlgoMaster.git
			}
			
			for(int i=0; i<dirts.size(); i++) {
<<<<<<< HEAD
				check = new int[w][h]; //체크 계속 쓰니까 초기화 
				int[] t = dirts.get(i);
				bfs(t[0], t[1]);
				for(int j=i+1; j<dirts.size(); j++) {
					int[] t1 = dirts.get(j);
					cleanW[i+1][j+1] = check[t1[0]][t1[1]]-1;
					cleanW[j+1][i+1] = check[t1[0]][t1[1]]-1;
					if(cleanW[i+1][j+1]==-1) never = true;
				}
			}

			
			if(never) {
				min = -1;
			}else {
				min = 1000000;
				used = new boolean[dirts.size()+1];
				selected = new int[dirts.size()+1];
				maxLen(1);
			}
			
			System.out.println(min);
=======
				int[] t = dirts.get(i);
				bfs(t[0], t[1]);
				for(int j=i+1; j<dirts.size(); j++) {
					int[] t1 = dirts.get(j);
					cleanW[i+1][j+1] = check[t1[0]][t1[1]];
					cleanW[j+1][i+1] = check[t1[0]][t1[1]];
				}
			}
			
//			for(int i=0; i<cleanW.length; i++) {
//				System.out.println(Arrays.toString(cleanW[i]));
//			}
			min =1000000;
			maxLen(0,1);
			
			System.out.println(min);
			
			
			
>>>>>>> branch 'master' of https://github.com/KangSoYeon/AlgoMaster.git
			
		}
		
	}

}