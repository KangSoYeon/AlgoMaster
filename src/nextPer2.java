import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class nextPer2 {
	static int N;
	static int[] arr;
	static int count;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		arr = new int[N];
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(tk.nextToken());
		}
		Arrays.sort(arr);
		
		
		do {
			System.out.println(Arrays.toString(arr));
		}while(next_permutation(arr));
		
		System.out.println(count);
		
	}
	private static boolean next_permutation(int[] arr) {
		count++;
		int i = N-1;
		while(i>0 && arr[i]<=arr[i-1]) i--;
		
		if(i==0) return false;
		
		int j = N-1;
		while(arr[i-1]>=arr[j]) j--;
		
		swap(i-1, j);
		
		j = N-1;
		while(i<j)	swap(j--, i++);
		
		return true;
		
	}
	
	public static void swap(int x, int y) {
		int temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}

}
