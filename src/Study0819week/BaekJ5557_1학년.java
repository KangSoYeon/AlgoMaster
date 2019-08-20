package Study0819week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJ5557_1학년 {
	static int N, count;
	static int[] arr;
	static ArrayList<Integer> memo[];
		
	public static void per(int n, int a) { //n번째꺼 연산 
		
		if(a<0 || a>20) return;
		if(n == N-1) {
			if(a==arr[N-1])	count++;
			return;
		}
		
		
		if(!memo[n].contains(a)) {
			memo[n].add(a);
			per(n+1, a+arr[n]);
			per(n+1, a-arr[n]);
		}
	}
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		arr = new int[N];
		memo = new ArrayList[N];
		
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		for(int i=0; i<N; i++) {
			memo[i] = new ArrayList<>(); //생성 하나씩은 해놔야함 
			arr[i] = Integer.parseInt(tk.nextToken());
		}
		
		//memo[0].add(arr[0]);
		per(0, arr[0]);
		System.out.println(count);
		
	}

}
