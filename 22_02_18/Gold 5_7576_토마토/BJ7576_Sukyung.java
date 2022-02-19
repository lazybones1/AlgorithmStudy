package day0218;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ7576_Sukyung {
	static int M, N, answer, go, tomatoCnt, notTomatoCnt;
	static int[][] box, start;
	static boolean[][] visit;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };
	static Queue<Index> queue;
	static boolean keepGoing;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		box = new int[M][N];
		start = new int[M * N][2];
		visit = new boolean[M][N];
		answer = 0;
		go = 0;
		tomatoCnt = 0;
		notTomatoCnt = 0;
		queue = new LinkedList<>();
		keepGoing = true;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
				if (box[i][j] == 1) {
					start[tomatoCnt][0] = i;
					start[tomatoCnt++][1] = j;
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
			queue.offer(new Index(start[n][0], start[n][1], 1));
			visit[start[n][0]][start[n][1]] = true;
		}

		while (!queue.isEmpty() && keepGoing) {
			Index now = queue.poll();

			for (int d = 0; d < 4; d++) {
				int nexti = now.i + dx[d];
				int nextj = now.j + dy[d];

				if (nexti >= 0 && nexti < M && nextj >= 0 && nextj < N && !visit[nexti][nextj]
						&& box[nexti][nextj] == 0) {
					box[nexti][nextj] = 1;
					visit[nexti][nextj] = true;
					if (notTomatoCnt == ++go) {
						answer = now.cnt;
						keepGoing = false;
						break;
					}
					queue.offer(new Index(nexti, nextj, now.cnt + 1));
				}
			}
		}
	}

	public static class Index {
		int i, j, cnt;

		public Index(int i, int j, int cnt) {
			this.i = i;
			this.j = j;
			this.cnt = cnt;
		}

	}

	public static void checkTomato() {
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (box[i][j] == 0)
					notTomatoCnt++;
			}
		}
	}
}
