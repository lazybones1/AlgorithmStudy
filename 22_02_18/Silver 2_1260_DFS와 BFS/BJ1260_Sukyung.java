package day0218;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BJ1260_Sukyung {
	static int N, M, V;
	static int[][] graph;
	static boolean[] visit;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		V = sc.nextInt();
		graph = new int[N + 1][N + 1];
		visit = new boolean[N + 1];

		for (int n = 0; n < M; n++) {
			int i = sc.nextInt();
			int j = sc.nextInt();
			graph[i][j] = graph[j][i] = 1;
		}
		dfs(V);
		System.out.println();
		visit = new boolean[N + 1];
		bfs();
	}

	public static void bfs() {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(V);
		visit[V] = true;
		System.out.print(V + " ");

		while (!queue.isEmpty()) {
			int now = queue.poll();

			for (int j = 1; j <= N; j++) {
				if (graph[now][j] == 1 && !visit[j]) {
					queue.offer(j);
					visit[j] = true;
					System.out.print(j + " ");
				}
			}
		}
	}

	public static void dfs(int now) {
		visit[now] = true;
		System.out.print(now + " ");

		for (int j = 1; j <= N; j++) {
			if (graph[now][j] == 1 && !visit[j]) {
				dfs(j);
			}
		}
	}
}
