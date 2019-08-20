package Study0819week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJ5569_출근경로 {
	static int W, H;
	
	public static void findWay(int h, int w) {
		//0,0 --> h,w 가는 경로
		if(h>=H && w >=W)	return;
		
		//가로한칸 가고 시작하는 경우의수 h,w-1에 도착
		
		
		
		//세로 한칸 가고 시작하는 경우의수 h-1, w에 도착 
		
		
	}
	static StringBuilder sb = new StringBuilder();
	static int[] arr;
	static int idx;
	static ArrayList<String> answers = new ArrayList<>();
	public static void divide(int n, int idx) {
		if(n==0) {
			for(int i=0; i<idx; i++) {
				sb.append(arr[i]);
			}
			answers.add(sb.toString());
			return;
		}
		
		for(int i=1; i<n; i++) {
			arr[idx] = i;
			divide(n-i, idx+1);
			arr[idx] = 0;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		W = Integer.parseInt(tk.nextToken());
		H = Integer.parseInt(tk.nextToken());
		arr = new int[100];
		W--;
		H--;
		//0,0에서 출발해 h, w에 도착 
		divide(5,3);
		System.out.println(answers);
		
		
	}

}
