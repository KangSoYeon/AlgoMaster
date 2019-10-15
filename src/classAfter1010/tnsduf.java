package classAfter1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import sun.util.locale.StringTokenIterator;

// 5 3
// N R

public class tnsduf {
	
	static int N;
	static int R;
	static int arr[];
	static boolean visited[];
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		arr = new int[R];
		visited = new boolean[N+1];
		NpR(0);
		//5C2
		
	}
	private static void NpR(int index) {
		if(index == R) {
			
			System.out.println(Arrays.toString(arr));
			return;
		}
		
		for(int i = 1; i <= N; i++) {
			if(!visited[i]) {
				arr[index] = i;
				visited[i] = true;
				NpR(index+1);
				visited[i] = false;
			}
		}
	}
}
