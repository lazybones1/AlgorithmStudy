package day0208;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BJ1158_Sukyung {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		Queue<Integer> q = new LinkedList<>();
		StringBuilder sb = new StringBuilder();

		for (int i = 1; i <= N; i++) {
			q.offer(i);
		}
		sb.append("<");
		for (int i = 0; i < N; i++) {
			for (int j = 1; j < K; j++) {
				q.offer(q.poll());
			}
			sb.append(q.poll() + ", ");
		}
		sb.setLength(sb.length() - 2);
		sb.append(">");
		System.out.println(sb);
	}
}
