package first10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJ1654_랜선자르기 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		int K = Integer.parseInt(tk.nextToken());
		int N = Integer.parseInt(tk.nextToken());
		int[] arr = new int[K];
		for(int i=0; i<K; i++) {
			arr[i] = Integer.parseInt(bf.readLine());
		}
		Arrays.sort(arr);
		
		long s = 1;
		long e = (long)arr[K-1]; //최대 길이 구하기 
		
		while(s <= e) {
			long mid = (s+e)/2;
			long sum = 0;
			for(int i=0; i<arr.length; i++) {
				sum += arr[i] /mid;
			}
			if(sum>=N) { //쪼개는게 가능하면 
				s = mid+1;
			}else {
				e = mid-1;
			}
		}
		System.out.println(e);
	}
}
