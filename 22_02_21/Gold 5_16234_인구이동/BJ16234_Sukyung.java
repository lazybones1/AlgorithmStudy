package day0221;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ16234_Sukyung {
	static int N, L, R;
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static boolean open;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int day = 0;
		while (true) {
			open = false;
			visit = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visit[i][j]) {
						peopleDiff(i, j, 1);
					}
				}
			}
			if (!open)
				break;
			day++;
		}
		System.out.println(day);
	}

	public static void peopleDiff(int i, int j, int count) {
		Queue<Point> queue = new LinkedList<Point>();
		Queue<Point> move = new LinkedList<Point>();
		queue.offer(new Point(i, j));
		move.offer(new Point(i, j));
		visit[i][j] = true;
		int movePeople = map[i][j];

		while (!queue.isEmpty()) {
			Point now = queue.poll();
			int nowPeople = map[now.i][now.j];

			for (int d = 0; d < 4; d++) {
				int nexti = now.i + dx[d];
				int nextj = now.j + dy[d];

				if (nexti >= 0 && nexti < N && nextj >= 0 && nextj < N && !visit[nexti][nextj]
						&& openLine(Math.abs(map[nexti][nextj] - nowPeople))) {
					open = true;
					count++;
					movePeople += map[nexti][nextj];
					queue.offer(new Point(nexti, nextj));
					move.offer(new Point(nexti, nextj));
					visit[nexti][nextj] = true;
				}
			}
		}
		int newPeople = movePeople / count;
		while (!move.isEmpty()) {
			Point now = move.poll();

			map[now.i][now.j] = newPeople;
		}
	}

	public static boolean openLine(int diff) {
		if (diff >= L && diff <= R)
			return true;
		return false;
	}

	public static class Point {
		int i, j;

		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
