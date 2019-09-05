package class0905;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_3109_빵집_강소연 {
	
	static int R, C, count;
	static char[][] arr;
	static boolean flag;
	static int[][] dir = {{-1,1},{0,1},{1,1}};  //오른쪽 위부터, 오른쪽, 오른쪽 아래
	//위쪽이 가장 유리함(greedy) --> 다시 되돌려서 다른경우 해볼 필요가 없음  
	//위부터 dfs돌리니까
	
	public static void dfs(int x, int y) {
	
		//불가능했던 루트들도 다 칠한채로 놔둬야된다.
		if(y==C-1) {
			count++;
			flag = true; //무조건 가능했다면 return쳐야한다.
			return;
		}
				
		for(int k=0; k<dir.length; k++) { //그 이전에꺼에서 모든방향 다해보면 dfs 끝남 
			int nx = x + dir[k][0];
			int ny = y + dir[k][1];	
	
			if(nx>=0 && ny>=0 && nx<R && ny<C && arr[nx][ny] =='.') {
				if(!flag) {
					arr[nx][ny] = '*'; //v파이프 놓은곳 이렇게 바꾸기  
					dfs(nx, ny);
					//선택을 되돌릴 필요가 없는 이유 : 설령 끝까지 못가는 길이어도 --> 어차피 못가는길
					//						갈수 있는 길이어도 --> 이미 간길이니까 다른 애가 다시 못가게 해야함
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		
		R = Integer.parseInt(tk.nextToken());
		C = Integer.parseInt(tk.nextToken());
		arr = new char[R][C];
		
		for(int i=0; i<R; i++) {
			String str = bf.readLine();
			for(int j=0; j<C; j++) {
				arr[i][j] = str.charAt(j);
			}
		}

		for(int i=0; i<R; i++) {
			flag = false;
			dfs(i,0);
		}	
//		for(int i=0; i<C; i++)
//			System.out.println(Arrays.toString(arr[i]));
		
		System.out.println(count);
	
	}
}
