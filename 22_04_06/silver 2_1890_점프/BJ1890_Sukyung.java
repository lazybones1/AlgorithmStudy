package day0406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1890_Sukyung {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		long[][] DP = new long[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		DP[0][0] = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == N - 1 && j == N - 1)
					break;
				if (DP[i][j] == 0)
					continue;
				
				int nexti = i + map[i][j];
				int nextj = j + map[i][j];
				
				if (nexti < N)
					DP[nexti][j] += DP[i][j];
				if (nextj < N)
					DP[i][nextj] += DP[i][j];
			}
		}
		System.out.println(DP[N - 1][N - 1]);
	}
}
