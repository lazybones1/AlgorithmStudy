package day0210;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2178_Sukyung {
	static char[][] map;
	static int N;
	static int M;
	static boolean[][] visit;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visit = new boolean[N][M];
		answer = 0;

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		bfs();
		System.out.println(answer);
	}

	public static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(0, 0, 1));
		visit[0][0] = true;

		while (!queue.isEmpty()) {
			Point now = queue.poll();

			for (int d = 0; d < 4; d++) {
				int nextR = now.i + dx[d];
				int nextC = now.j + dy[d];

				if (nextR == N - 1 && nextC == M - 1) {
					answer = now.cnt + 1;
					return;
				}
				if (nextR >= 0 && nextR < N && nextC >= 0 && nextC < M && !visit[nextR][nextC]
						&& map[nextR][nextC] == '1') {
					visit[nextR][nextC] = true;
					queue.add(new Point(nextR, nextC, now.cnt + 1));
				}
			}
		}
	}

	public static class Point {
		int i, j, cnt;

		Point(int i, int j, int cnt) {
			this.i = i;
			this.j = j;
			this.cnt = cnt;
		}
	}
}
