package day0209;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;


public class Main_1874_스택수열 {
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bf.readLine());
		
		
		ArrayList<Character> ans = new ArrayList<>();
		Stack<Integer> st = new Stack<>();
		
		int start = 0;
		for (int i = 0; i < n; i++) {
			int value = Integer.parseInt(bf.readLine());
			
			if(value>start) {
				for(int j=start +1;j<=value;j++) {
					st.push(j);
					ans.add('+');
				}
				start = value;
			}
			else if(st.peek()!=value) {
				System.out.println("NO");
				return;
			}
			
			st.pop();
			ans.add('-');
		
		}
				
		
		for(int k=0;k<ans.size();k++) {
			System.out.println(ans.get(k));
		}
		
	}
}
