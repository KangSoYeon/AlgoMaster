package NHN0929;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class nhn2 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N =Integer.parseInt(bf.readLine());
		StringTokenizer tk ;
		StringBuilder sb = new StringBuilder();
		Map<Integer, Integer> map = new HashMap<>();
		
		for(int i=0; i<N; i++) {
			tk = new StringTokenizer(bf.readLine());
			String str = tk.nextToken();
			
			if(str.equals("enqueue")) {
				int a = Integer.parseInt(tk.nextToken());
				if(map.containsKey(a)){
					int num = map.get(a);
					map.put(a, num+1);
				}else {
					map.put(a, 1);
				}
			}else if(str.equals("dequeue")) {
				if(map.isEmpty()) {
					sb.append("-1 ");
				}else {
					List<Map.Entry<Integer, Integer>> list = new LinkedList(map.entrySet());
					Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
						@Override
						public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
							return o2.getValue() - o1.getValue();
						}
					});
					
					int tempK = list.get(0).getKey();
					int tempV = list.get(0).getValue();
					sb.append(tempK +" ");
					if(tempV-1 <=0) {
						map.remove(tempK);
					}else {
						map.put(tempK, tempV-1);
					}

				}
			}
			
		}
		
		System.out.println(sb);

	}

}
