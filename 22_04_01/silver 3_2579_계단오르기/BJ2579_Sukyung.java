package day0401;

import java.util.Scanner;

public class BJ2579_Sukyung {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] stairs = new int[N];
		int[] DP = new int[N];

		for (int i = 0; i < N; i++) {
			stairs[i] = sc.nextInt();
		}
		if (N >= 3) {
			DP[0] = stairs[0];
			DP[1] = Math.max(stairs[0] + stairs[1], stairs[1]);
			DP[2] = Math.max(stairs[0] + stairs[2], stairs[1] + stairs[2]);

			for (int i = 3; i < N; i++) {
				DP[i] = Math.max(DP[i - 2], DP[i - 3] + stairs[i - 1]) + stairs[i];
			}
		} else if (N == 2) {
			DP[0] = stairs[0];
			DP[1] = Math.max(stairs[0] + stairs[1], stairs[1]);
		} else {
			DP[0] = stairs[0];
		}
		System.out.println(DP[N - 1]);
	}
}
