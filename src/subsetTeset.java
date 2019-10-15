import java.util.Scanner;

public class subsetTeset {
//입력받은 수들로 가능한 부분집합 모든 출력 : 재귀로 구현
	public static int N;
	public static boolean[] flag;
	public static int[] arr; //여기는 선언만 생성하면 안되고 
	
/*
5
1 2 3 4 5
*/
	public static void subset(boolean[] flag, int index) { 
		//총길이 N이라고 가정시, N까지의 자릿수중에 선택 했다 안했다 반복
		//여기서 함수안에 있는 flag를 사용해도 되는 이유는 flag는 함수안에서만 쓰이고 더이상 쓰이지 않음
		//굳이 필요없고 전역으로 선언해놓고 flag값은 안들고다녀도 됨
		if(index==N) {
			for(int i=0; i<index; i++) {
				if(flag[i]) System.out.print(arr[i]+" ");
				else 		System.out.print("X" +" ");
			}
			System.out.println();
			return;
		}
		flag[index]=true; //true로 만들고 보내고
		subset(flag, index+1);
		flag[index]=false;	//false로 만들고 보내고
		subset(flag, index+1);
	}
	
	public static void makeSubset(int index) {
		if(index==N) {
			for (int s = 0; s < flag.length; s++) {
				System.out.print((flag[s]? arr[s]:"X")+" "); 
			}
			System.out.println();
			return;
		}
		
		for(int i=1; i>=0; i--) { //두가지 선택지, 재귀안에 반복문 돌리기
			flag[index] = i==1? true:false;
			makeSubset(index+1); //앞에꺼 픽스하고 다음꺼 보내고
			//재귀함수를 이 for문만큼 보내겠다
		}
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		N = scan.nextInt();
		flag = new boolean[N]; //여기서 생성해야 변화된 N값으로 들어감
		arr = new int[N];
		
		for (int i = 0; i < arr.length; i++) {
			arr[i]= scan.nextInt();
		}
		
		//subset(flag, 0);
		makeSubset(0);
	}
}