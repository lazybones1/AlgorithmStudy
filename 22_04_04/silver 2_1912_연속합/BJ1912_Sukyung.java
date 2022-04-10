package day0404;

import java.util.Scanner;

public class BJ1912_Sukyung {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int N = s.nextInt();
		int[] arr = new int[N];
		int[] DP = new int[N];

		for (int i = 0; i < N; i++)
			arr[i] = s.nextInt();

		DP[0] = arr[0];
		int max = arr[0];
		for (int i = 1; i < N; i++) {
			DP[i] = Math.max(DP[i - 1] + arr[i], arr[i]);
			max = Math.max(max, DP[i]);
		}
		System.out.println(max);
	}
}
