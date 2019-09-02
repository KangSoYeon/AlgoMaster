package 삼성기출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJ17144_미세먼지안녕 {
	static int R,C,T;
	static int[][] arr;
	static ArrayList<int[]> dusts;
	static int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}};
	static int[] x;
	
	public static void getDust() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(arr[i][j] != 0 && arr[i][j] != -1) {
					dusts.add(new int[] {i, j, arr[i][j]});
				}
			}
		}
	}
	
	
	public static void spreadDust() {
		for(int i=0; i<dusts.size(); i++) {
			int[] temp = dusts.get(i);
			int sp = temp[2]/5;
			
			for(int k=0; k<dir.length; k++) {
				int nx = temp[0]+dir[k][0];
				int ny = temp[1]+dir[k][1];
				
				if(nx>=0 && ny>=0 && nx<R && ny<C
						&& arr[nx][ny]!=-1) { //공기청정기가 아니면  
					arr[nx][ny] += sp;
					arr[temp[0]][temp[1]] -= sp;
				}
			}
		}
	}
	
	public static void air() {
		
		//위에 공기청정기 
		int temp = arr[x[0]][C-1];
		for(int i=C-2; i>0; i--) { //오른쪽으로 
			arr[x[0]][i+1] = arr[x[0]][i];
		}
		arr[x[0]][1]=0; //청소됨  
		arr[x[0]-1][C-1] = temp; //방향전환  
		
		temp = arr[0][C-1]; //저장  
		
		for(int i=x[0]-2; i>0; i--) { //위쪽으로  
			arr[i-1][C-1] = arr[i][C-1];
		}
		
		arr[0][C-2]=temp;
		temp = arr[0][0];
		
		for(int i=C-2; i>0; i--) {
			arr[0][i-1] = arr[0][i];
		}
		
		arr[1][0]=temp;
		
		for(int i=1; i<x[0]-1; i++) {
			arr[i+1][0] = arr[i][0];
		}
		
		////////
		temp = arr[x[1]][C-1];
		for(int i=C-2; i>0; i--) {
			arr[x[1]][i+1] = arr[x[1]][i];
		}
		arr[x[1]][1]=0;
		
		arr[x[1]+1][C-1] = temp;
		temp = arr[R-1][C-1];
		
		for(int i=R-2; i>=x[1]+1; i--) {
			arr[i+1][C-1] = arr[i][C-1];
		}
		
		arr[R-1][C-2]= temp;
		temp = arr[R-1][0];
		
		for(int i=0; i<=C-3; i++) {
			arr[R-1][i] = arr[R-1][i+1];
		}
		
		arr[R-2][0] = temp;
		
		for(int i=x[1]+1; i<=R-3; i++) {
			arr[i][0] = arr[i+1][0];
		}
		
		
	}
	
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		
		R = Integer.parseInt(tk.nextToken());
		C = Integer.parseInt(tk.nextToken());
		T = Integer.parseInt(tk.nextToken());
		arr = new int[R][C];
		x = new int[2];
		dusts = new ArrayList<>();
		int idx=0;
		for(int i=0; i<R; i++) {
			tk = new StringTokenizer(bf.readLine());
			for(int j=0; j<C; j++) {
				arr[i][j]= Integer.parseInt(tk.nextToken());
				if(arr[i][j]==-1) {
					x[idx++] = i;
				}
			}
		}
		
		Arrays.sort(x);
		//x[0] --> 위에공기청정기 : 반시계방향
		//x[1] --> 아래 공기청정기 : 시계방향 
		
		
		for(int t=0; t<T; t++) {
			//t번반복  
			dusts = new ArrayList<>();
			getDust();
			spreadDust();
			System.out.println(t+" 먼지 뿌린후");
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					System.out.print(arr[i][j]+" ");
				}
				System.out.println();
			}
			System.out.println(t+" 공기청정기 ");
			air();
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					System.out.print(arr[i][j]+" ");
				}
				System.out.println();
			}
			System.out.println();
		}
		int total =0;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				total += arr[i][j];
			}
		}
		total-=2;
		
		System.out.println(total);
		
		
	}

}
