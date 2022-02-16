package day0208;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_1158_¿ä¼¼Çª½º {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();

		Queue<Integer> q = new LinkedList<>();
		LinkedList<Integer> ans = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			q.add(i + 1);
		}

		int temp = 0;
		int k = 0;
		int size = N;

		while (size != 0) {

			temp = q.poll();
			if (k==K-1) {
				ans.add(temp);
				size--;
				k=0;
			} else {
				q.add(temp);
				k++;
			}
				
			

		}

		System.out.print("<");

		for (int i = 0; i < ans.size(); i++) {
			if (i == N - 1)
				System.out.print(ans.get(i) + ">");
			else
				System.out.print(ans.get(i) + ", ");
		}
		
	}
}
