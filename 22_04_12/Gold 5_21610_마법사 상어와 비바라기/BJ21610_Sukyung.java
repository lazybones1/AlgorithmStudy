package day0427;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ21610 {
	static int N, M;
	static int[][] map;
	static int[][] move;
	static int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static ArrayList<Point> cloud;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		move = new int[M][2];
		cloud = new ArrayList<Point>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			move[i][0] = d - 1;
			move[i][1] = s;
		}
		cloud.add(new Point(N - 1, 0));
		cloud.add(new Point(N - 1, 1));
		cloud.add(new Point(N - 2, 0));
		cloud.add(new Point(N - 2, 1));

		for (int i = 0; i < M; i++) {
			moveCloud(move[i][0], move[i][1]);
			rain();
			copyWater();
			makeCloud();
		}
		System.out.println(calculateWater());
	}

	public static int calculateWater() {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sum += map[i][j];
			}
		}
		return sum;
	}

	public static void makeCloud() {
		ArrayList<Point> newCloud = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] >= 2) {
					boolean same = false;
					for (int k = 0; k < cloud.size(); k++) {
						if (cloud.get(k).i == i && cloud.get(k).j == j) {
							same = true;
							break;
						}
					}
					if (!same) {
						map[i][j] -= 2;
						newCloud.add(new Point(i, j));
					}
				}
			}
		}
		cloud = newCloud;
	}

	public static int[][] deepcopy(int[][] map) {
		int[][] copy = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copy[i][j] = map[i][j];
			}
		}
		return copy;
	}

	public static void copyWater() {
		Queue<Point> queue = new LinkedList<>();
		queue.addAll(cloud);
		int[][] copy = deepcopy(map);

		while (!queue.isEmpty()) {
			Point now = queue.poll();
			int water = 0;

			for (int d = 1; d < 8; d += 2) {
				int nexti = now.i + dx[d];
				int nextj = now.j + dy[d];

				if (nexti >= 0 && nexti < N && nextj >= 0 && nextj < N && map[nexti][nextj] > 0) {
					water++;
				}
			}
			copy[now.i][now.j] += water;
		}
		map = copy;
	}

	public static void moveCloud(int d, int s) {
		ArrayList<Point> newCloud = new ArrayList<>();
		for (int i = 0; i < cloud.size(); i++) {
			int nexti = (cloud.get(i).i + dx[d] * s + 10000 * N) % N;
			int nextj = (cloud.get(i).j + dy[d] * s + 10000 * N) % N;
			newCloud.add(new Point(nexti, nextj));
		}
		cloud = newCloud;
	}

	public static void rain() {
		for (int i = 0; i < cloud.size(); i++) {
			map[cloud.get(i).i][cloud.get(i).j] += 1;
		}
	}

	public static class Point {
		int i, j;

		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
