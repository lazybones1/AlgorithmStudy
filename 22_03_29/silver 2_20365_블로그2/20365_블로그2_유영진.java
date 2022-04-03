package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_20365_블로그2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int bcnt=0;
		int rcnt=0;
		int first=0;
		int ans=0;
		ArrayList<String> bstr = new ArrayList<>();
		ArrayList<String> rstr = new ArrayList<>();
		int n = Integer.parseInt(bf.readLine());
		String s =bf.readLine();
		StringTokenizer st = new StringTokenizer(s,"R");
		
		while(st.hasMoreTokens()) {
			
			bstr.add(st.nextToken());
		}
		
		st = new StringTokenizer(s,"B");
		
		while(st.hasMoreTokens()) {
			rstr.add(st.nextToken());
		}
		if(bstr.size()<=rstr.size()) {
			ans=1+bstr.size();
		}else {
			ans=1+rstr.size();
		}
		
		System.out.println(ans);
		
	}
	
}
