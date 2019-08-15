package dp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution3307_최장증가부분수열 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		
		StringTokenizer tk;
		for(int test =1; test<=T; test++) {
			int N = Integer.parseInt(bf.readLine());
			tk = new StringTokenizer(bf.readLine());
			int[] arr = new int[N];
			int answer =0;
			
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(tk.nextToken());
			}
			
			
			
			System.out.println("#"+test+" "+answer);
			
			
		}
		
		
	}

}
