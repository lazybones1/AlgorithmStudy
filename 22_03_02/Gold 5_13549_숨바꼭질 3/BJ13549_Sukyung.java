package day0302;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BJ13549_Sukyung {
	static int N, K, answer;
	static int[] move = { 2, -1, 1 };
	static boolean[] visit;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		visit = new boolean[100001];
		answer = Integer.MAX_VALUE;
		
		bfs();
		System.out.println(answer);
	}

	public static void bfs() {
		Queue<Number> queue = new LinkedList<>();
		queue.offer(new Number(N, 0));
		visit[N] = true;

		while (!queue.isEmpty()) {
			Number now = queue.poll();

			if (now.num == K) {
				answer = now.count;
				return;
			}
			for (int i = 0; i < 3; i++) {
				int nextNum = 0;
				int nextCount = 0;

				if (i == 0) {
					nextNum = now.num * move[i];
					nextCount = now.count;
				} else {
					nextNum = now.num + move[i];
					nextCount = now.count + 1;
				}
				if (nextNum >= 0 && nextNum <= 100000 && !visit[nextNum]) {
					queue.offer(new Number(nextNum, nextCount));
					visit[nextNum] = true;
				}
			}
		}
	}

	public static class Number {
		int num, count;

		public Number(int num, int count) {
			this.num = num;
			this.count = count;
		}
	}
}
