package day0208;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BJ2161_Sukyung {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Queue<Integer> queue = new LinkedList<>();
		int N = sc.nextInt();
		for (int i = 1; i <= N; i++) {
			queue.offer(i);
		}
		while (!queue.isEmpty()) {
			System.out.print(queue.poll() + " ");
			if (queue.isEmpty())
				break;
			int temp = queue.poll();
			queue.offer(temp);
		}
	}
}
