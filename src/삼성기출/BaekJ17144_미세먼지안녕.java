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
						&& arr[nx][ny]!=-1) { //����û���Ⱑ �ƴϸ�  
					arr[nx][ny] += sp;
					arr[temp[0]][temp[1]] -= sp;
				}
			}
		}
	}

	public static void airUp() {	
		
		int[][] dir2 = {{0,1}, {-1,0}, {0, -1}, {1, 0}};
		int fx = x[0];
		int fy = 1;
		int[][] map = new int[R][C];
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				map[i][j]=arr[i][j]; //map�׶��׶� �����س��� 
			}
		}
		map[x[0]][1]=0; //����û���� �ٷ� �������� �׻� ġ���� ���� 
		
		for(int k=0; k<dir2.length; k++) {
			while(true) {
				int nx = fx + dir2[k][0];
				int ny = fy + dir2[k][1];
				
				if(nx<0 || ny<0 || nx>=R || ny>=C) break;
				
				if(nx==x[0] && ny==0)	break; //�ٽ� ����û����� ���ƿ����� ������ 
				
				map[nx][ny] = arr[fx][fy]; //���ο� �ʿ� �Ű� ��´�
				fx = nx;
				fy = ny;
				
			}
		}
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				arr[i][j]=map[i][j]; //���ο� ������ ������Ʈ 
			}
		}
	}
	
	public static void airDown() {
		int[][] dir2 = {{0,1}, {1,0}, {0,-1}, {-1, 0}};
		int fx = x[1];
		int fy = 1;
		
		int[][] map = new int[R][C];
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				map[i][j] = arr[i][j];
			}
		}
		map[x[1]][1]=0; //����û���� ���� 0
		
		for(int k=0; k<4; k++) {
			while(true) {
				int nx = fx + dir2[k][0];
				int ny = fy + dir2[k][1];
				
				if(nx<0 || ny<0 || nx>=R || ny>=C) break; //�������� �Ѿ��
				
				if(nx==x[1] && ny==0) break;
				
				map[nx][ny]=arr[fx][fy]; //�迭�ΰ������ �Ǵµ�...
				fx = nx;
				fy = ny;
				
			}
		}
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				arr[i][j] = map[i][j];
			}
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
		//x[0] --> ��������û���� : �ݽð����
		//x[1] --> �Ʒ� ����û���� : �ð���� 
		
		
		for(int t=0; t<T; t++) {
			//t���ݺ�  
			dusts = new ArrayList<>();
			getDust();
			spreadDust();
			airUp();
			airDown();
			
		}
		
		int total =0;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				total += arr[i][j];
			}
		}
		total+=2; //2�������ϱ� 2���ؾ��� 
		
		System.out.println(total);	
	}
}