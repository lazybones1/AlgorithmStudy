package day0207;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_9375_ÆÐ¼Ç¿Õ½ÅÇØºó {
	static boolean[] select;
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(bf.readLine());
		for(int t=0;t<tc;t++) {
			n =Integer.parseInt(bf.readLine());
			Map<String,Integer> map = new HashMap<>();
			for(int i=0;i<n;i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine());
				String name = st.nextToken();
				String type = st.nextToken();
				
				if (map.containsKey(type))
				    map.put(type, map.get(type) + 1);
				else
				    map.put(type, 1);
			}
			int ans =1;
			for(int val : map.values()) {
				ans *= (val+1);
				
			}
			System.out.println(ans-1);
	
		}

	}
	
	
	
	

}
