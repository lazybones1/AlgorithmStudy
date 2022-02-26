package day0223;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ7562_Sukyung {
	static int N, starti, startj, answer;
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = { -1, -2, -2, -1, 1, 2, 2, 1 };
	static int[] dy = { -2, -1, 1, 2, -2, -1, 1, 2 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visit = new boolean[N][N];
			st = new StringTokenizer(br.readLine(), " ");
			starti = Integer.parseInt(st.nextToken());
			startj = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine(), " ");
			int destinationi = Integer.parseInt(st.nextToken());
			int destinationj = Integer.parseInt(st.nextToken());
			map[destinationi][destinationj] = 1;
			answer = Integer.MAX_VALUE;

			if (starti == destinationi && startj == destinationj) {
				System.out.println(0);
			} else {
				bfs();
				System.out.println(answer);
			}
		}
	}

	public static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(starti, startj, 0));
		map[starti][startj] = -1;

		while (!queue.isEmpty()) {
			Point now = queue.poll();

			for (int d = 0; d < 8; d++) {
				int nexti = now.i + dx[d];
				int nextj = now.j + dy[d];

				if (nexti >= 0 && nexti < N && nextj >= 0 && nextj < N && map[nexti][nextj] == 1) {
					answer = Math.min(answer, now.cnt + 1);
					return;
				}
				if (nexti >= 0 && nexti < N && nextj >= 0 && nextj < N && map[nexti][nextj] != -1) {
					map[nexti][nextj] = -1;
					queue.offer(new Point(nexti, nextj, now.cnt + 1));
				}
			}
		}
	}

	public static class Point {
		int i, j, cnt;

		public Point(int i, int j, int cnt) {
			this.i = i;
			this.j = j;
			this.cnt = cnt;
		}
	}
}
