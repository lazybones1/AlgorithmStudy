package day0223;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ14502_Sukyung {
	static int N, M, answer;
	static int[][] map;
	static boolean[][] visit;
	static int[] comb;
	static ArrayList<Point> blank;
	static ArrayList<Point> virus;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		comb = new int[3];
		blank = new ArrayList<Point>();
		virus = new ArrayList<Point>();
		answer = Integer.MIN_VALUE;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0)
					blank.add(new Point(i, j));
				if (map[i][j] == 2)
					virus.add(new Point(i, j));
			}
		}
		combination(0, 0);
		System.out.println(answer);
	}

	public static int[][] copyArray(int[][] map) {
		int[][] copy = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copy[i][j] = map[i][j];
			}
		}
		return copy;
	}

	public static void combination(int start, int count) {
		if (count == 3) {
			int[][] copy = copyArray(map);
			for (int i = 0; i < 3; i++) {
				copy[blank.get(comb[i]).i][blank.get(comb[i]).j] = 1;
			}
			visit = new boolean[N][M];
			bfs(copy);
			return;
		}
		for (int i = start; i < blank.size(); i++) {
			comb[count] = i;
			combination(i + 1, count + 1);
		}
	}

	public static void bfs(int[][] array) {
		Queue<Point> queue = new LinkedList<>();
		for (int i = 0; i < virus.size(); i++) {
			queue.offer(new Point(virus.get(i).i, virus.get(i).j));
			visit[virus.get(i).i][virus.get(i).j] = true;
		}
		int blankCnt = blank.size() - 3;

		while (!queue.isEmpty()) {
			Point now = queue.poll();

			for (int d = 0; d < 4; d++) {
				int nexti = now.i + dx[d];
				int nextj = now.j + dy[d];

				if (nexti >= 0 && nexti < N && nextj >= 0 && nextj < M && !visit[nexti][nextj]
						&& array[nexti][nextj] == 0) {
					array[nexti][nextj] = 2;
					blankCnt--;
					queue.offer(new Point(nexti, nextj));
					visit[nexti][nextj] = true;
				}
			}
		}
		answer = Math.max(answer, blankCnt);
	}

	public static class Point {
		int i, j;

		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
