package day0207;

import java.util.Scanner;
import java.util.Stack;

public class Main_17608_����� {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		int N = sc.nextInt();
		Stack<Integer> st = new Stack<>();
		
		for(int i=0;i<N; i++) {
			int temp = sc.nextInt();
			st.push(temp);
		}
		
		
		int last = st.pop();
		int cnt = 1;
		int current;
		
		while(!st.isEmpty()) { //size()==0 ���� �ð��ʰ� 
			current = st.pop();
			if(current>last) {
				last = current;
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}

}
