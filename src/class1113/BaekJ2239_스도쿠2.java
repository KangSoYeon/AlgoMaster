package class1113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BaekJ2239_스도쿠2 {

	
	static int[][] arr;
	static boolean[][] row, col, sq, check;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		arr = new int[9][9];
		row = new boolean[9][9];
		col = new boolean[9][9];
		sq = new boolean[9][9];
		check = new boolean[9][9];
		
		for(int i=0; i<9; i++) {
			String str = bf.readLine();
			for(int j=0; j<9; j++) {
				arr[i][j] = str.charAt(j)-'0';
				
				if(arr[i][j]!=0) {
					row[i][arr[i][j]-1] = true;
					col[j][arr[i][j]-1] = true;
					sq[(i/3)*3+(j/3)][arr[i][j]-1] = true;
				}
				
			}
		}
		
		/*for(int i=0; i<9; i++) {
			System.out.println(Arrays.toString(row[i]));
			System.out.println(Arrays.toString(col[i]));
			System.out.println(Arrays.toString(sq[i]));
			System.out.println("----------");
		}*/
		backtrack(0);
		
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
		return;
		
	}
	static boolean flag;
	private static void backtrack(int times) {
		
		if(times >= 81) {
			flag = true;
			return;
		}
		int i = times/9;
		int j = times%9;
/*		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {*/
				if(arr[i][j]==0) {
					check[i][j] = true;
					for(int w=0; w<9; w++) {
						if(!row[i][w] && !col[j][w] && !sq[(i/3)*3+(j/3)][w]) {
							row[i][w] = true;
							col[j][w] = true;
							sq[(i/3)*3+(j/3)][w] = true;
							arr[i][j] = w+1;
							backtrack(times+1);
							if(flag) return; //이미 끝까지 한번 돌았으면 끝내기 
							arr[i][j] = 0;
							row[i][w] = false;
							col[j][w] = false;
							sq[(i/3)*3+(j/3)][w] = false;
						}
					}
				}else {
					backtrack(times+1);
				}
	/*		}
		}*/
		
	}
}
