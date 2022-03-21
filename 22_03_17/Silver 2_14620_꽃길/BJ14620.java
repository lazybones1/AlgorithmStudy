package day0317;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14620 {
	static int N;
	static int[][] map;
	static int[] dx = { -1, 1, 0, 0, 0 };
	static int[] dy = { 0, 0, 0, 1, -1 };
	static boolean[][] visit;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(flower(0, 0));
	}

	public static int flower(int count, int sum) {
		if (count == 3) {
			return sum;
		}
		int answer = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				boolean ok = true;
				int ground = 0;

				for (int d = 0; d < 5; d++) {
					int nowi = i + dx[d];
					int nowj = j + dy[d];

					if (nowi < 0 || nowi >= N || nowj < 0 || nowj >= N || visit[nowi][nowj]) {
						ok = false;
					}
				}
				if (ok) {
					for (int d = 0; d < 5; d++) {
						int nowi = i + dx[d];
						int nowj = j + dy[d];
						visit[nowi][nowj] = true;
						ground += map[nowi][nowj];
					}
					answer = Math.min(answer, flower(count + 1, sum + ground));
					for (int d = 0; d < 5; d++) {
						int nowi = i + dx[d];
						int nowj = j + dy[d];
						visit[nowi][nowj] = false;
					}
				}
			}
		}
		return answer;
	}
}
