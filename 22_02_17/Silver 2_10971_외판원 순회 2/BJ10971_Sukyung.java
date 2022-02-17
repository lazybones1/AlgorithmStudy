package day0217;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ10971_Sukyung {
	static int N, answer;
	static int[][] map;
	static boolean[] used;
	static int[] permu;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		used = new boolean[N];
		permu = new int[N];
		answer = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		permutation(0);
		System.out.println(answer);
	}

	public static void permutation(int count) {
		if (count == N) {
			int sum = 0;
			boolean go = true;
			for (int i = 0; i < N - 1; i++) {
				if (map[permu[i]][permu[i + 1]] != 0)
					sum += map[permu[i]][permu[i + 1]];
				else {
					sum = Integer.MAX_VALUE;
					go = false;
					break;
				}
			}
			if (!go) {
				sum = Integer.MAX_VALUE;
			} else if (map[permu[N - 1]][permu[0]] != 0)
				sum += map[permu[N - 1]][permu[0]];
			else {
				sum = Integer.MAX_VALUE;
			}
			answer = Math.min(answer, sum);
			return;
		}
		for (int i = 0; i < N; i++) {
			if (used[i])
				continue;
			permu[count] = i;
			used[i] = true;
			permutation(count + 1);
			used[i] = false;
		}
	}
}
