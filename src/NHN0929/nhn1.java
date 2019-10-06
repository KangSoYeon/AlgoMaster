package NHN0929;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

public class nhn1 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		StringTokenizer tk = new StringTokenizer(bf.readLine());
		HashMap<String, Integer> map = new HashMap<>();
		int size = 0;
		String answer = "Y";
		for(int i=0; i<N; i++) {
			String str = tk.nextToken();
			if(map.containsKey(str)){
				int t = map.get(str);
				map.put(str, t+1);
			}else {
				map.put(str, 1);
			
			}
		}
		//System.out.println(map);
		
		Iterator<String> keys = map.keySet().iterator(); 
		Iterator<String> keys2 = map.keySet().iterator();
		while(keys.hasNext()){ 
			String key = keys.next(); 
			int a = map.get(key);
			while(keys2.hasNext()) {
				String key2 = keys2.next();
				int b = map.get(key2);
				if(a != b) {
					if(a<b) {
						map.put(key, map.get(key)+1);
					}else if(b<a) {
						map.put(key2, map.get(key2)+1);
					}
				}
			}
			
		}
		
		keys = map.keySet().iterator(); 
		keys2 = map.keySet().iterator();
		while(keys.hasNext()){ 
			String key = keys.next(); 
			int a = map.get(key);
			while(keys2.hasNext()) {
				String key2 = keys2.next();
				int b = map.get(key2);
				if(a != b) {
					answer = "N";
				}
			}
			size += a;
		}

		System.out.println(answer);
		if(answer.charAt(0)=='N') System.out.println(size-1);
		else System.out.println(size);
		System.out.println(map.size()); //3

	}

}
