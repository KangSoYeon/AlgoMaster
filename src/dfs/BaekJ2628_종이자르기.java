package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BaekJ2628_종이자르기 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		
		int N = Integer.parseInt(tk.nextToken());
		int M = Integer.parseInt(tk.nextToken());
		
		ArrayList<Integer> ver = new ArrayList<>();
		ArrayList<Integer> col = new ArrayList<>();

		int C = Integer.parseInt(bf.readLine());
		for(int i=1; i<=C; i++) {
			tk = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(tk.nextToken()); //0이면 가로--> M, y좌표 쪼개기 
			int b = Integer.parseInt(tk.nextToken()); //1이면 세로--> N, x좌표 쪼개기
	
			if(a==0) { 
				ver.add(b);
			}else {
				col.add(b);
			}
			
		}
		
		ver.add(0);
		col.add(0);
		ver.add(M); //마지막 영역도 넣기 
		col.add(N);
		
		Collections.sort(ver);
		Collections.sort(col);
		
		int maxV =1;
		int maxC =1;
			
		for(int i=0; i<ver.size()-1; i++) {
			int temp = ver.get(i+1)-ver.get(i);
			if(temp>maxV)	maxV = temp;
		}
		
		for(int i=0; i<col.size()-1; i++) {
			int temp = col.get(i+1)-col.get(i);
			if(temp>maxC)	maxC = temp;
		}
				
		System.out.println(maxV*maxC);

	}
}
