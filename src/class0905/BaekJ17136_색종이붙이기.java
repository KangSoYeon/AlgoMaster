package class0905;

import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.util.Arrays;

import java.util.LinkedList;

import java.util.Queue;

import java.util.StringTokenizer;

public class BaekJ17136_색종이붙이기 {

	static int[][] arr, check;
	static int[][] dir = {{0,1},{1,1},{1,0}};
	static int[] paper;
	static int answer;

	public static void bfs(int x, int y) {

		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {x,y});
		check[x][y]=1;
		int n=0;
		boolean flag = false;
		int size =0;
		while(!q.isEmpty()) {

			size = q.size(); //해당 노드에 인접한 1의 갯수 
			System.out.println("size:"+ size+"   n:"+n);
			if(size != (2*n)+1) { //더큰 색종이는 못넣음 
				flag = true;
				if(paper[n-1]==0) {
					answer=-1;
					break;
				}
				paper[n-1]--; // 그 이전 색종이 더 못쓴다고 표시, n전에 색종이를 사용한거니까
				answer++;
				if(paper[0]-size <= 0) answer=-1; //0짜리 색종이 다쓰면
				else {
					paper[0]-=size;
					answer+=size;
				}

				break; //끝내기 
			}
			
			if(n>=4) { //최대  /크기 되면 끝내야함  
				paper[4]--;
				answer++;
				break;
			}

			while(size-- >0) { //레벨별로 돌리기 

				int[] t = q.poll();
				for(int k=0; k<dir.length; k++) {

					int nx = t[0] + dir[k][0];
					int ny = t[1] + dir[k][1];

					if(nx>=0 && ny>=0 && nx<10 && ny<10
							&& arr[nx][ny]==1 && check[nx][ny]==0) {

						q.offer(new int[] {nx, ny});
						check[nx][ny] = check[t[0]][t[1]]+1;

					}	
				}
			}
			
			if(q.isEmpty()) { //하나의붙어있는 1그룹 다돌았을 떄  
				paper[n]--;
				answer++;
			}
			n++;
		
		}
	}


	public static void main(String[] args) throws IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk;
		arr = new int[10][10];
		paper = new int[5];
		check = new int[10][10];

		for(int i=0; i<5; i++) {
			paper[i]=5; //1~5까지의 색종이에 5값 넣어놓기 
		}

		for(int i=0; i<10; i++) {
			tk = new StringTokenizer(bf.readLine());
			for(int j=0; j<10; j++) {
				arr[i][j]= Integer.parseInt(tk.nextToken());
			}
		}

		

		//1*1, 2*2, 3*3, 4*4, 5*5 가 각각 5개씩 

		

		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				if(arr[i][j]==1 && check[i][j]==0) {
					bfs(i,j);
					for(int ii=0; ii<10; ii++) {
						for(int jj=0; jj<10; jj++) {
							System.out.print(check[ii][jj]+" ");
						}
						System.out.println();
					}
					System.out.println(Arrays.toString(paper));
				}
			}
		}


		System.out.println(answer);
	}
}
