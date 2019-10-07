import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Jung2247_도서관 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		int[][] arr = new int[N][2];
		for(int i=0; i<N; i++) {
			arr[i][0] = scan.nextInt();
			arr[i][1] = scan.nextInt();
		}
		Arrays.sort(arr, new Comparator<int[]>() {

			public int compare(int[] o1, int[] o2) {
				return o1[0]-o2[0]; //시작시간순으로 정렬 
			}
		});
		
		int empS=arr[0][0];//처음꺼 시작시
		int empE=arr[0][1];
		int peoS=0;
		int peoE=0;
		int maxP = Integer.MIN_VALUE;
		int maxA = Integer.MIN_VALUE;
		for(int i=1; i<N; i++) {
			int[] time = arr[i];
			if(time[0]>empE) { //연속으로 사람있는게 끝남  
				int com = empE-empS;
				peoS = empE;
				peoE = time[0];
				if(com>maxP) maxP = com;
				if(peoE-peoS >maxA) maxA = peoE-peoS;
			}else if(time[0]<= empE) { //연속으로 사람 있음 
				if(time[1]>empE) empE = time[1];
			}
			
		}
		System.out.println(maxP);
		System.out.println(maxA);
		
	}

}
