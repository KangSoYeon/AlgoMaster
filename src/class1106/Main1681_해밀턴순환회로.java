package class1106;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main1681_해밀턴순환회로 {

	static int N, arr[][], min, route;
	static boolean checked[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk;
		N = Integer.parseInt(bf.readLine());
		checked = new boolean[N];
		min = Integer.MAX_VALUE;
	
		//입력받는거 
		arr = new int[N][N];
		for(int i=0; i<N; i++) {
			tk = new StringTokenizer(bf.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(tk.nextToken());
			}
		}
		permu(0, 0, 0);
		System.out.println(min);
	}
	//순열뽑기
	static void permu(int idx, int route, int before) {
		if(route>min) return; //더커지면 거기서 끝내기 --> 이걸로 많이 도는거 줄여준다.
		if(idx>=N-1) {
			if(arr[before][0]!=0) route += arr[before][0]; //돌아가는루트 길이 추가 
			else return;
			
			if(min>route) min = route;
			return;
		}
		
		for(int i=1; i<N; i++) {
			if(checked[i] || arr[before][i]==0) continue;
			//route+=arr[before][i]; //before에서 다음 껄로 이동--> 이러면 재귀라서 안됨
			checked[i] = true;
			permu(idx+1, route + arr[before][i], i);
			checked[i] = false;
		}
	}
}
