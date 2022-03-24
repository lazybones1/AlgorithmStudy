package day0321;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ16439_Sukyung {
	static int N, M, answer;
	static int[][] map;
	static int[] comb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		comb = new int[3];
		answer = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		combination(0, 0);
		System.out.println(answer);
	}

	public static void combination(int start, int count) {
		if (count == 3) {
			int sum = 0;
			for (int i = 0; i < N; i++) {
				int max = 0;
				for (int j = 0; j < 3; j++) {
					max = Math.max(max, map[i][comb[j]]);
				}
				sum += max;
			}
			answer = Math.max(answer, sum);
			return;
		}
		for (int i = start; i < M; i++) {
			comb[count] = i;
			combination(i + 1, count + 1);
		}
	}
}
