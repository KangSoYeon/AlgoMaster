package classAfter1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


// 5 3
// N R

public class whgkq {
	
	static int N;
	static int R;
	static int arr[];
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		arr = new int[R];
		NcR(1,0);
		//5C2
		
	}
	private static void NcR(int current, int index) {
		if(index == R) {
			
			System.out.println(Arrays.toString(arr));
			return;
		}
		
		for(int i = current; i <= N; i++) {
			arr[index] = i;
			NcR(i + 1, index + 1);
		}
	}
}
