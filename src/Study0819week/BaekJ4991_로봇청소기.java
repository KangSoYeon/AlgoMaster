package Study0819week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJ4991_로봇청소기 {
	static int[][] dir = {{0,1}, {1,0}, {-1,0}, {0,-1}};
	static int w, h, sx, sy;
	static ArrayList<int[]> dirts;
	static char[][] arr;
	static int[][] check;
	
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
	
	

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		w = Integer.parseInt(tk.nextToken());
		h = Integer.parseInt(tk.nextToken());
		dirts = new ArrayList<>();
		
		if(w==0 && h==0)	return;
		
		arr = new char[w][h];
		check = new int[w][h]; //최단 거리를 저장할 배열  
		
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
		
		int[][] cleanW = new int[12][12]; //더러운지점과 깨끗한 지점 가는 최단경로 배열
		
		bfs(sx, sy);
		
		
		for(int i=0; i<dirts.size(); i++) {
			int[] temp;
			for(int i=0; i<dirts.size(); i++) {
				temp = dirts.get(i);
				cleanW[0][i+1] = arr[temp[0]][temp[1]];
			}
			bfs(temp[0], temp[1]);
			check = new int[w][h]; //최단 거리를 저장할 배열  
						
	
		}
		

		
	}

}
