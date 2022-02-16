package day0208;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_2161_Ä«µå1 {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		int n = sc.nextInt();
		
		Queue<Integer> q = new LinkedList<>();
		
		for(int i=1; i<=n;i++) {
			q.add(i);
		}
		Queue<Integer> ans = new LinkedList<>();
		while(!q.isEmpty()) {
			
			int val=q.poll();
			ans.add(val);
			if(q.isEmpty()) break;
			int temp=q.poll();
			q.add(temp);
			
		}
		for(int i=0;i<n;i++) {
			System.out.print(ans.poll()+" ");
		}
	}

}
