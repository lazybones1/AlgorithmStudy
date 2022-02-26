package day0222;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ16918_Sukyung {
	static int R, C;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static ArrayList<Point> bomb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		char[][] map = new char[R][C];
		char[][] totalBomb = new char[R][C];
		char[][] nearBomb = new char[R][C];
		char[][] reversedBomb = new char[R][C];
		char[][] result = new char[R][C];

		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				totalBomb[i][j] = 'O';
				map[i][j] = line.charAt(j);
			}
		}
		if (N == 1) {
			result = map;
		} else if (N % 2 == 0) {
			result = totalBomb;
		} else if (N % 4 == 1) {
			checkBomb(map);
			nearBomb = bfs(map, new boolean[R][C]);
			reversedBomb = reverseArray(nearBomb);
			checkBomb(reversedBomb);
			nearBomb = bfs(reversedBomb, new boolean[R][C]);
			reversedBomb = reverseArray(nearBomb);
			result = reversedBomb;

		} else if (N % 4 == 3) {
			checkBomb(map);
			nearBomb = bfs(map, new boolean[R][C]);
			reversedBomb = reverseArray(nearBomb);
			result = reversedBomb;
		}
		for (char[] r : result) {
			for (char c : r) {
				System.out.print(c);
			}
			System.out.println();
		}
	}

	public static void checkBomb(char[][] map) {
		bomb = new ArrayList<>();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'O')
					bomb.add(new Point(i, j));
			}
		}
	}

	public static char[][] reverseArray(char[][] nearBomb) {
		char[][] reversedBomb = new char[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				reversedBomb[i][j] = nearBomb[i][j] == '.' ? 'O' : '.';
			}
		}
		return reversedBomb;
	}

	public static char[][] bfs(char[][] map, boolean[][] visit) {
		char[][] nearBomb = new char[R][C];
		nearBomb = map;
		Queue<Point> queue = new LinkedList<>();
		for (int i = 0; i < bomb.size(); i++) {
			queue.offer(new Point(bomb.get(i).i, bomb.get(i).j));
			visit[bomb.get(i).i][bomb.get(i).j] = true;
		}

		while (!queue.isEmpty()) {
			Point now = queue.poll();

			for (int d = 0; d < 4; d++) {
				int nexti = now.i + dx[d];
				int nextj = now.j + dy[d];

				if (nexti >= 0 && nexti < R && nextj >= 0 && nextj < C && !visit[nexti][nextj]) {
					nearBomb[nexti][nextj] = 'O';
					visit[nexti][nextj] = true;
				}
			}
		}
		return nearBomb;
	}

	public static class Point {
		int i, j;

		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
