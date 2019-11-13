import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_2239_스도쿠_김민경 {
	public static int size;
	public static int[][] sudoku = new int[9][9];
	public static ArrayList<Coordinates> list = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i=0; i<9; i++) {
			char[] line = br.readLine().toCharArray();
			
			for(int j=0; j<9; j++) {
				sudoku[i][j] = line[j] - '0';
				
				if(sudoku[i][j] == 0) list.add(new Coordinates(i, j));
			}
		}
		
		size = list.size();
		fillZero(0);
	}
	
	public static void fillZero(int index) {
		if(index == size) {
			for(int i=0; i<9; i++) {
				for(int j=0; j<9; j++) {
					System.out.print(sudoku[i][j]);
				}
				System.out.println();
			}
			
			System.exit(0);
		}
		else {
			Coordinates c = list.get(index);
			
			for(int i=1; i<=9; i++) {
				sudoku[c.x][c.y] = i;
				if(possible(c.x, c.y)) fillZero(index + 1);
				sudoku[c.x][c.y] = 0;
			}
		}
	}
	
	public static boolean possible(int x, int y) {
		// 세로
		for(int i=0; i<9; i++) {
			if(i == x) continue;
			
			if(sudoku[x][y] == sudoku[i][y]) return false;
		}
		
		// 가로
		for(int j=0; j<9; j++) {
			if(j == y) continue;
			
			if(sudoku[x][y] == sudoku[x][j]) return false;
		}
		
		// 3 * 3
		int iStart = x / 3 * 3;
		int iEnd = iStart + 3;
		int jStart = y / 3 * 3;
		int jEnd = jStart + 3;
		
		for(int i=iStart; i<iEnd; i++) {
			for(int j=jStart; j<jEnd; j++) {
				if(i == x && j == y) continue;
				
				if(sudoku[x][y] == sudoku[i][j]) return false;
			}
		}
		
		return true;
	}
	
	public static class Coordinates {
		int x;
		int y;
		
		public Coordinates(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public String toString() {
			return "Coordinates [x=" + x + ", y=" + y + "]";
		}
	}
}
