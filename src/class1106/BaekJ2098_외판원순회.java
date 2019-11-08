package class1106;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJ2098_외판원순회 {

	static int N, arr[][], route, dp[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk;
		N = Integer.parseInt(bf.readLine());
	
		//입력받는거 
		arr = new int[N][N];
		dp = new int[N][1<<N]; //x에서 출발하여 경로
		
		for(int i=0; i<N; i++) {
			tk = new StringTokenizer(bf.readLine());
			Arrays.fill(dp[i], -1); //-1로 초기화 
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(tk.nextToken());
			}
		}
		
		System.out.println(getDis(0, 1)); //0번째 장소부터 간다고 생각, 이미 0번째 장소 갔으므로 비트는 1
	}
	//순열뽑기
	static int getDis(int cur, int visited) {
		if(visited == (1<<N)-1) { //모든 지점 다 방문 했으면
			if(arr[cur][0]!=0)  return arr[cur][0]; //다시 돌아가는 값 리턴
			else   return 987654321; 
		}
		
		if(dp[cur][visited] != -1) { //방문해서 이미 계산 한 곳이면
			return dp[cur][visited];
		}
		int min = 987654321;
		for(int i=0; i<N; i++) {
			//if((visited & (1<<i))==1 || arr[cur][i]==0) continue;
			if((visited &(1<<i)) == 0 && arr[cur][i]!=0) //아직 안갔고 갈수 있는 경로면 
				min = Math.min(min, arr[cur][i] + getDis(i, visited|(1<<i)));
		}
		dp[cur][visited]=min;
		return dp[cur][visited];
	}
}
