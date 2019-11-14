package class1113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJ15961_회전초밥 {
	static int N, D, K, C, arr[];
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tk.nextToken());
		D = Integer.parseInt(tk.nextToken());
		K = Integer.parseInt(tk.nextToken());
		C = Integer.parseInt(tk.nextToken());
		
		arr = new int[N];
		
		for(int i=0; i<N;i++) {
			arr[i] = Integer.parseInt(bf.readLine());
		}
		
		
	}

}
