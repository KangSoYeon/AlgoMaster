package classAfter1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BaekJ1786_찾기 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		char[] T = bf.readLine().toCharArray();
		char[] P = bf.readLine().toCharArray();
		
		int tLength = T.length;
		int pLength = P.length;

		int[] fail = new int[pLength];
		for(int i=1, j=0; i<pLength; i++) { //i를 접미사 포인터, j를 접두사 포인터
			while(j>0 && P[i] != P[j]) {
				j = fail[j-1];
			}
			if(P[i] == P[j]) {
				fail[i] = ++j; //일치하는 애가 있을 때만 j증가 
			}	
		}
		
		int cnt = 0;
		ArrayList<Integer> an = new ArrayList<>();
		for(int i=0, j=0; i<tLength; i++) { //i:텍스트 포인터, j:패턴포인터
			while(j>0 && T[i] != P[j]) {  //불일치 할 경우
				j = fail[j-1]; //비교가능한 위치로 j를 이동(j-1까지는 맞은거니까)
			}
			if(T[i] == P[j]) { //두글자 일치
				if(j==pLength-1) {//맞는 패턴 찾으면
					cnt++; //결과 카운트 증가 
					an.add(i-pLength +2);
					j = fail[j]; //j위치까지 맞았을 떄 이동해야하는 포인터 위치로 이동한다.
				}else {
					j++; //패턴의 끝이 아니면 j이동 
				}
			}
		
		}
		
		System.out.println(cnt);
		for(int i=0; i<an.size(); i++) {
			System.out.print(an.get(i)+" ");
		}
		
	}

}
