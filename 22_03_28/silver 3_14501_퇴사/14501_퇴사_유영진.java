package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14501_퇴사 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int[] T = new int[n + 1];
		int[] P = new int[n + 1];
		int[] DP = new int[n + 2];

		for (int i = 1; i <= n; i++) {
			StringTokenizer stt = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(stt.nextToken());
			P[i] = Integer.parseInt(stt.nextToken());
		}

		for (int i = n; i > 0; i--) {
			if (i + T[i] > n + 1)
				DP[i] = DP[i + 1];
			else {
				DP[i] = Math.max(DP[i + 1], P[i] + DP[i + T[i]]);
			}
		}
		System.out.println(DP[1]);
	}
}
