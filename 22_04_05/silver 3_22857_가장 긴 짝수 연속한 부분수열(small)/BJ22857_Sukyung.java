package day0405;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ22857_Sukyung {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] num = new int[N + 1];
		int[][] DP = new int[N + 1][K + 1];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		int answer = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j <= K; j++) {
				if (num[i] % 2 == 0) {
					DP[i][j] = DP[i - 1][j] + 1;
				} else if (j != 0 && num[i] % 2 != 0) {
					DP[i][j] = DP[i - 1][j - 1];
				}
				answer = Math.max(answer, DP[i][j]);
			}
		}
		System.out.println(answer);
	}
}
