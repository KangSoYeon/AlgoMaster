package class0904;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Jung1863_종교2 {
	
	private static int parents[];
    
    static void make() { //make set : 모든 원소를 개별적인 집합으로 생성
        Arrays.fill(parents, -1); //더이상 루트가 없다는 뜻 
    }
     
    static int find(int a) {
        if(parents[a]<0) return a; //자신이 루트이면 자신 리턴
        return parents[a] = find(parents[a]); //나의 부모한테 또 그 루트 찾아오라고 시킴
    }
     
    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
         
    }

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		int count=0;
		int N = Integer.parseInt(tk.nextToken());
		int M = Integer.parseInt(tk.nextToken());
		//ArrayList<Integer>[] arr = new ArrayList[N+1];
		parents = new int[N+1];
		make();

		for(int i=0; i<M; i++) {
			tk = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(tk.nextToken());
			int b = Integer.parseInt(tk.nextToken());
			
			union(a,b);
		}

		//본인이 루트일때 -1 --> -1의 갯수가 그룹의 갯수 
		for(int i=1; i<=N; i++) {
			if(parents[i]==-1) count++;
			//집합 당 루트 노드 하나만 값이 -1, 누구와도 쌍을 이루지 못한 학생들도 값이 -1
			//쌍을 이루지 못한 학생들은 다른 종교를 가지고 있을 수 있으므로 종교의 가지수를 늘려준다.
			//총 종교의 가지수 = 집합의 갯수 + 쌍을 이루지 못한 학생 수
			//parents 배열의 -1값의 갯수 
		}
		
		if(M==0) count = N;
		System.out.println(count);
		
	}
}
