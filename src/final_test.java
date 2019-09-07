import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class final_test {

	static int[] arr;
	static int N;
	static int R;
	public static void main(String[] args) throws IOException {
		N = 3;
		R = 2;
		arr = new int[] {3,4,1};
		selected = new int[R];
		perSelected = new int[N];
		dupSelected = new int[N];
		checked = new boolean[N];
		System.out.println("========combination========");
		comb(0, 0);
		System.out.println("=====permu========");
		permutation(0);
		System.out.println("=====nextPermu=====");
		Arrays.sort(arr);
		do {
			System.out.println(Arrays.toString(arr));
			
		}while(next_permutation());
		System.out.println("=====dupPermu=====");
		dupPermu(0);
	}

	static int[] selected;
	public static void comb(int n, int r) {
		
		if(r==R) {
			System.out.println(Arrays.toString(selected));
			return;
		}
		if(n<N) {
			selected[r] = arr[n];
			comb(n+1, r+1);
			comb(n+1, r);
		}
		
	}
	
	static int[] perSelected;
	static boolean[] checked;
	public static void permutation(int idx) {
		if(idx==N) {
			System.out.println(Arrays.toString(perSelected));
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(!checked[i]) { //사용안했으면   
				perSelected[idx] =arr[i];
				checked[i] = true;
				permutation(idx+1);
				checked[i]= false;
			}
		}
	}
	static int[] dupSelected;
	public static void dupPermu(int idx) {
		
		if(idx==N) {
			System.out.println(Arrays.toString(dupSelected));
			return;
		}
		
		for(int i=0; i<N; i++) {
			dupSelected[idx]=i;
			dupPermu(idx+1);
		}	
	}
	
	public static boolean next_permutation() {
		int i = N-1;
		while(i>0 && arr[i] <= arr[i-1]) i--;
		
		if(i==0) return false;
		
		int j = N-1;
		while(arr[j]<= arr[i-1]) j--;
		
		swap(i-1, j);
		
		j = N-1;
		while(j>i) swap(j--, i++);
		return true;
		
	}

	private static void swap(int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;	
	}
}
