import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class nextPer{
	static int N;
	static int[] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
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
		}while(next_permu(arr));
		
		
	}
	private static boolean next_permu(int[] arr) {
		
		int i = N-1;
		while(i>0 && arr[i]<= arr[i-1]) i--;
		
		if(i==0) return false;
		
		int j = N-1;
		while(arr[j] <= arr[i-1]) j--;
		
		swap(i-1, j);
		
		j = N-1; //반만 리벌스 하는겅 
		while(j>i) swap(i++, j--);
		
		return true;
	}
	
	public static void swap(int x, int y) {
		int temp = arr[x];
		arr[x]=arr[y];
		arr[y]= temp;
	}
	
}