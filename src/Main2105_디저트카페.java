import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main2105_디저트카페 {
	static int N, arr[][], answer;
	static int[][] dir = {{1,1}, {1,-1}, {-1,-1}, {-1,1}};
	static ArrayList<Integer> sweet;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk;
		int T = Integer.parseInt(bf.readLine());
		for(int test=1; test<=T; test++) {
			int answer =0;
			N = Integer.parseInt(bf.readLine());
			sweet = new ArrayList<>();
			
			for(int i=0; i<N; i++) {
				tk = new StringTokenizer(bf.readLine());
				for(int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(tk.nextToken());
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					rec(i, j, 0, 0);
				}
			}
			
			
			System.out.println("#"+test+" "+answer);
		}
	}
	private static void rec(int x, int y, int l, int t) {
		if(t>=4) { //네번째 꺽음이면 끝내기
			
			return;
		}
		
		for(int d=0, size = dir.length; d<N; d++) { //이만큼의 크기만큼 
			//t만큼 반복ㄴ
			int nx = x;
			int ny = y;
			for(int j=0; j<t; j++) { //t만큼 반복
				nx += dir[j][0];
				ny += dir[j][1];
				
				if(nx<0 || ny<0 || nx>=N || ny>=N) return; //범위넘어가면 끝내기
				
				if(sweet.contains(arr[nx][ny])) return; //아예 안되는 경우함수를 끝내기 
				
				sweet.add(arr[nx][ny]);
				
				
			}
		}
		
		
	}

}
