import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Test0827_원자소멸3 {
	
	static int N, answer;
	static int[][] dir = {{-1,0}, {1,0}, {0,-1}, {0,1}}; //상하좌우
	static ArrayList<int[]> arr;
	static int[][] pick;
	static boolean[] check;
	
	public static void per(int idx) { //모든 원소중에 2개 뽑은걸 줄세워서 그 두개가 충돌할건지 안할건지 보는 함수
		
		if(idx==2) {
			//pick에 두개 뽑았음 
			int[] a = pick[0];
			int[] b = pick[1];
			boolean flag = false;
			if(a[0]==b[0] && a[1]>b[1] && a[2]==1 && b[2]==0) { //x좌표가 같을 때
				//a의 y좌표가 크다 가정
				//어차피 ab, ba모두 나올거니까 상관없음 
				flag = true;
			}else if(a[1]==b[1] && a[0]>b[0] && a[2]==2 && b[2]==3) { //y좌표가 같을 때
				//a의 x좌표가 크다 가정
				flag = true;
			}
			//a가 a,b
			//b가 c,d라 가정
			else if(b[0]-a[0] == a[1]-b[1] && a[2]==3 && b[2]==0) {
				flag = true;
			}else if(b[0]-a[0] == a[1]-b[1] && a[2]==1 && b[2]==2) {
				flag = true;
			}else if(a[0]-b[0] == a[1]-b[1] && a[2]==2 && b[2]==0) {
				flag = true;
			}else if(a[0]-b[0] == a[1]-b[1] && a[2]==1 && b[2]==3) {
				flag = true;
			}
			
			if(flag) answer += a[3]+b[3];
			
			return;
		}
		
		
		for(int i=0; i<arr.size(); i++){
			pick[idx] = arr.get(i);
			check[i] = true;
			per(idx+1);
			check[i]=false;
		}
		
	}
	
	
	
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk;
		int T = Integer.parseInt(bf.readLine());
		
		for(int test=1; test<=T; test++) {
			
			N = Integer.parseInt(bf.readLine());
			answer =0;
			arr = new ArrayList<>();
			pick = new int[2][];
			check= new boolean[N];
			
			for(int l=0; l<N; l++) {
				tk = new StringTokenizer(bf.readLine());
				int x = Integer.parseInt(tk.nextToken());
				int y = Integer.parseInt(tk.nextToken());
				int d = Integer.parseInt(tk.nextToken());
				int k = Integer.parseInt(tk.nextToken());
				
				arr.add(new int[] {x, y, d, k});
			}
			
			per(0);
						
			System.out.println("#"+test+" "+ answer);

		}
	}

}
