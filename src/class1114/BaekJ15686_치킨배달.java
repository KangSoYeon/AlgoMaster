package class1114;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJ15686_치킨배달 {
	static int N, M, arr[][], among[], chsize;
	static ArrayList<int[]> ch, home;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());
		arr = new int[N][N];
		ch = new ArrayList<>();
		home = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			tk = new StringTokenizer(bf.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(tk.nextToken());
				if(arr[i][j] == 2) { //치킨집 배열 만들기 
					ch.add(new int[] { i, j }); //치킨집 위치 좌표
				}else if(arr[i][j]==1) {
					home.add(new int[] {i, j});
				}
			}
		}
		chsize = ch.size();
		among = new int[chsize]; //이중에M개의 치킨집 고르기 
		//M개의 치킨집을 고르고, 가장 치킨거리가 짧은거 
		for(int i=chsize-1; i>chsize-1-M; i--) { //넥퍼할 배열 만들기 
			among[i] = 1;
		}
		int[][] sch = new int[M][2];  //선택된 치킨집 배열
		int minA = Integer.MAX_VALUE;
		do {
			//M*집만큼 
			//System.out.println(Arrays.toString(among));
			int idx = 0;
			for(int i=0; i<chsize; i++) {
				if(among[i]==1) {
					sch[idx++] = ch.get(i); //선택된 치킨집 좌표만 넣기 
				}
			}
			//sch와 home의 모든 경우 다해보기
			int total = 0;
			for(int i=0, s = home.size(); i<s; i++) { //집
				int min = Integer.MAX_VALUE;
				int[] nh = home.get(i); //현재 집 
				//각 집마다 모든 치킨집과 거리를 구했을 때 최소가 되는 min값 갱신함.
				for(int j=0; j<M; j++){ //치킨집
					int dis = Math.abs(nh[0]-sch[j][0]) + Math.abs(nh[1]-sch[j][1]);
					if(dis<min) { //i
						min = dis;
					}
				}
				//i번쨰 집에서 가장 가까운 치킨집과의 거리 min
				total += min;
			}
			
			if(total<minA) {
				minA = total;
			}
			
		}while(next_permu());
		
		System.out.println(minA);
		
	}
	
	//among을 넥퍼 돌리기
	private static boolean next_permu() {
		int i= chsize-1;
		
		while(i>0 && among[i-1] >=among[i]) i--;
		
		if(i==0) return false;
		
		int j = chsize-1;
		
		while(among[j] <= among[i-1]) j--;
		
		swap(i-1, j);
		
		j=chsize-1;
		
		while(j>i) swap(j--, i++);
		
		return true;
	}

	private static void swap(int j, int i) {
		int temp = among[i];
		among[i] = among[j];
		among[j] = temp;
	}
}
