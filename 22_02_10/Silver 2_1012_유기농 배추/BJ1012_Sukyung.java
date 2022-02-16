package day0210;

import java.util.Scanner;

public class BJ1012_Sukyung {
	static int[][] map;
	static int N;
	static int M;
	static boolean[][] visit;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();

		for (int tc = 1; tc <= TC; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			int K = sc.nextInt();
			map = new int[N][M];
			visit = new boolean[N][M];
			int answer = 0;

			for (int k = 0; k < K; k++) {
				int i = sc.nextInt();
				int j = sc.nextInt();
				map[i][j] = 1;
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 1 && !visit[i][j]) {
						dfs(i, j);
						answer++;
					}
				}
			}
			System.out.println(answer);
		}
	}

	public static void dfs(int i, int j) {
		visit[i][j] = true;
		for (int d = 0; d < 4; d++) {
			int nextR = i + dx[d];
			int nextC = j + dy[d];

			if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= M || visit[nextR][nextC] || map[nextR][nextC] == 0)
				continue;
			dfs(nextR, nextC);
		}
	}
}
