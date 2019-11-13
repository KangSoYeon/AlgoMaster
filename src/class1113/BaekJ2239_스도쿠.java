package class1113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BaekJ2239_스도쿠 {
	
	static class pos{
		//아직 안쓰인건 true, 쓰인건 false
		boolean[] able = new boolean[9]; //이미 숫자가 있는 칸은 기본적으로 다 false로 선언돼있음
	}
	
	static pos[][] arr;
	static int[][] board;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		arr = new pos[9][9];
		board = new int[9][9];
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				arr[i][j] = new pos();
			}
		}
		for(int i=0; i<9; i++) {
			String str = bf.readLine();
			for(int j=0; j<9; j++) {
				board[i][j] = str.charAt(j)-'0';
			}
		}
		
		//9개의 상자로 생각 
		boolean[] not = new boolean[9];
		for(int ss=0; ss<=6; ss+=3) {
			for(int m=0; m<=6; m+=3) {
				//시작 좌표가 바뀔때마다 boolean초기화 
				Arrays.fill(not, true);
				for(int i=ss; i<ss+3; i++) { //하나의 네모마다 이미사용한 숫자를 지우고
					for(int j=m; j<m+3; j++) {
						not[board[i][j]-1] = false;//이미 사용된거 false
					}
				}
				
				for(int i=ss; i<ss+3; i++) {
					for(int j=m; j<m+3; j++) {
						if(board[i][j]==0) { //0인칸에 다 넣어준다.
							arr[i][j].able = not;
						}
					}
				}
			}
		}
		
		
		for(int i=0; i<9; i++) { //가로 세로 못쓰는 것들 체크 
			for(int j=0; j<9; j++) {
				if(board[i][j]==0) {
					for(int t=0; t<9; t++) {
						if(board[i][t]!=0) {
							arr[i][t].able[board[i][t]-1] = false;
						}
						if(board[t][j]!=0) {
							arr[t][j].able[board[t][j]-1] = false;
						}
					}
					
				}
			}
		}
		
		rec();
		
	}
	private static void rec() {
		
		
		
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				if(board[i][j]==0) {
					for(int t=0; t<9; t++) {
						if(arr[i][j].able[t]) { //넣을 수 있으면 바로 넣고
							board[i][j] = t+1;
							break;
						}
					}
					
					
					
					
				}
			}
		}
		
	}

}
