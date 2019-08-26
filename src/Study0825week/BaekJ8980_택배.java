package Study0825week;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
5 4
5
2 4 1
4 5 3
1 5 1
3 4 2
1 2 2

 */
public class BaekJ8980_택배 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		
		int N = Integer.parseInt(tk.nextToken());
		int C = Integer.parseInt(tk.nextToken());
		
		int T = Integer.parseInt(bf.readLine());
		ArrayList<int[]> arr = new ArrayList<>();
		for(int t = 0; t<T; t++) {
			tk = new StringTokenizer(bf.readLine());
			int pick = Integer.parseInt(tk.nextToken());
			int drop = Integer.parseInt(tk.nextToken());
			int load = Integer.parseInt(tk.nextToken());
			arr.add(new int[] {pick, drop, load});
			
		}
				
		//받은 배열 정렬 --> 빨리 배송받는거 먼저 정렬 
		Collections.sort(arr, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				if(o1[1] == o2[1]) { //같을때는 출발점으로 정렬 
					return o1[0] - o2[0];
				}else //도착지점이 1번과 가까울수록 빨리 처리해야한다 
					return o1[1] - o2[1];
			}
		});
		
//		for(int i=0; i<T; i++) {
//			System.out.println(Arrays.toString(arr.get(i)));
//		}
		
//		int answer = 0;
//		int max = 0;
//		
//		
//		for(int i=1; i<=N; i++) { //1부터마을수만큼 배송 시작 
//			for(int j=0; j<T; j++) {
//				if(arr.get(j)[1]==i) { //배송부터 함 
//					max -= arr.get(j)[2];
//					answer += arr.get(j)[2]; //배송한 갯수 세기 
//				}
//				if(arr.get(j)[0]==i) { //물건 싣기 
//					if(max + arr.get(j)[2]<=C) { //아직 화물공간 있으면 
//						max += arr.get(j)[2]; //화물 다 싣고 
//					}else {
//						//해당 지점에서 픽업한 갯수로 원래 배열을 바꿔줌 
//						arr.set(j, new int[] {arr.get(j)[0], arr.get(j)[1], C-max});
//						max = C;
//					}
//				}
//	
//			}
//		
//		}
		
		int[] max = new int[N+1]; //해당 마을에 들렀을때 적재할수 있는 최대량
		int answer=0;
		for(int j=0; j<T; j++) { //정렬되어있는 순서로 처리
			int pick = arr.get(j)[0];
			int drop = arr.get(j)[1];
			int load = arr.get(j)[2];
					
			if(max[pick]+load >= C || max[drop]- load <= -C) {
				max[drop] -= (C-max[pick]);
				answer += (C-max[pick]);
				max[pick]=C; //꽉참 
			}else {
				max[drop] -= load;
				answer += load;
				max[pick] += load;
			}
	
		}
		
		System.out.println(Arrays.toString(max));
		
		System.out.println(answer);
	}

}
