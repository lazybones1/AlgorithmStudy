package day0222;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ7569_Sukyung {
	static int M, N, H, answer, go, tomatoCnt, notTomatoCnt;
	static int[][][] box;
	static int[][] start;
	static boolean[][][] visit;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };
	static Queue<Index> queue;
	static boolean keepGoing;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		box = new int[M][N][H];
		start = new int[M * N * H][3];
		visit = new boolean[M][N][H];
		answer = 0;
		go = 0;
		tomatoCnt = 0;
		notTomatoCnt = 0;
		queue = new LinkedList<>();
		keepGoing = true;

		for (int h = 0; h < H; h++) {
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					box[i][j][h] = Integer.parseInt(st.nextToken());
					if (box[i][j][h] == 1) {
						start[tomatoCnt][0] = i;
						start[tomatoCnt][1] = j;
						start[tomatoCnt++][2] = h;
					}
				}
			}
		}
		checkTomato();
		if (notTomatoCnt != 0) {
			bfs();
			notTomatoCnt = 0;
			checkTomato();
		}
		if (notTomatoCnt != 0)
			System.out.println(-1);
		else
			System.out.println(answer);
	}

	public static void bfs() {
		for (int n = 0; n < tomatoCnt; n++) {
			queue.offer(new Index(start[n][0], start[n][1], start[n][2], 1));
			visit[start[n][0]][start[n][1]][start[n][2]] = true;
		}

		while (!queue.isEmpty() && keepGoing) {
			Index now = queue.poll();

			for (int d = 0; d < 4; d++) {
				int nexti = now.i + dx[d];
				int nextj = now.j + dy[d];
				int up = now.h - 1;
				int down = now.h + 1;

				if (nexti >= 0 && nexti < M && nextj >= 0 && nextj < N && !visit[nexti][nextj][now.h]
						&& box[nexti][nextj][now.h] == 0) {
					box[nexti][nextj][now.h] = 1;
					visit[nexti][nextj][now.h] = true;
					if (notTomatoCnt == ++go) {
						answer = now.cnt;
						keepGoing = false;
						break;
					}
					queue.offer(new Index(nexti, nextj, now.h, now.cnt + 1));
				}
				if (up >= 0 && !visit[now.i][now.j][up] && box[now.i][now.j][up] == 0) {
					box[now.i][now.j][up] = 1;
					visit[now.i][now.j][up] = true;
					if (notTomatoCnt == ++go) {
						answer = now.cnt;
						keepGoing = false;
						break;
					}
					queue.offer(new Index(now.i, now.j, up, now.cnt + 1));
				}
				if (down < H && !visit[now.i][now.j][down] && box[now.i][now.j][down] == 0) {
					box[now.i][now.j][down] = 1;
					visit[now.i][now.j][down] = true;
					if (notTomatoCnt == ++go) {
						answer = now.cnt;
						keepGoing = false;
						break;
					}
					queue.offer(new Index(now.i, now.j, down, now.cnt + 1));
				}
			}
		}
	}

	public static class Index {
		int i, j, h, cnt;

		public Index(int i, int j, int h, int cnt) {
			this.i = i;
			this.j = j;
			this.h = h;
			this.cnt = cnt;
		}

	}

	public static void checkTomato() {
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				for (int h = 0; h < H; h++) {
					if (box[i][j][h] == 0)
						notTomatoCnt++;
				}
			}
		}
	}
}
