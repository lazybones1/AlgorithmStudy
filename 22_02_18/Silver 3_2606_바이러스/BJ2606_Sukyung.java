package day0218;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class BJ2606_Sukyung {
	static int N, linkNum;
	static boolean[] visit;
	static Computer[] computers;
	static Set<Integer> answer;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		linkNum = sc.nextInt();
		computers = new Computer[linkNum];
		visit = new boolean[linkNum];
		answer = new HashSet<Integer>();

		for (int n = 0; n < linkNum; n++) {
			int i = sc.nextInt();
			int j = sc.nextInt();

			if (i <= j) {
				computers[n] = new Computer(i, j);
			} else {
				computers[n] = new Computer(j, i);
			}
		}
		Arrays.sort(computers);
		bfs();
		System.out.println(answer.size() - 1 > 0 ? answer.size() - 1 : 0);
	}

	public static void bfs() {
		Queue<Computer> link = new LinkedList<>();
		if (computers[0].i == 1) {
			link.offer(computers[0]);
			answer.add(computers[0].i);
			answer.add(computers[0].j);
			visit[0] = true;
		}

		while (!link.isEmpty()) {
			int nowi = link.peek().i;
			int nowj = link.peek().j;
			link.poll();

			for (int n = 1; n < linkNum; n++) {
				if (!visit[n] && (computers[n].i == nowi || computers[n].i == nowj || computers[n].j == nowi
						|| computers[n].j == nowj)) {
					visit[n] = true;
					answer.add(computers[n].i);
					answer.add(computers[n].j);
					link.offer(computers[n]);
				}
			}
		}
	}

	public static class Computer implements Comparable<Computer> {
		int i, j;

		public Computer(int i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public int compareTo(Computer o) {
			return this.i - o.i; // i로 오름차순 정렬
		}
	}
}
