import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class nextPer3 {
	static int N;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(tk.nextToken());
		}
		
		Arrays.sort(arr);
		do {
			System.out.println(Arrays.toString(arr));
		}while(next_permutation());
		
		
	}
	
	static boolean next_permutation() {
		int i = N-1;
		while(i>0 && arr[i-1]>=arr[i]) i--;
		
		if(i==0) return false;
		
		int j = N-1;
		while(arr[j] <= arr[i-1]) j--;
		
		swap(i-1, j);
		
		j = N-1;
		while(i<j) swap(i++, j--);
		return true;
		
	}
	
	static void swap(int x, int y) {
		int temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
		
		
	}
	
	

}
