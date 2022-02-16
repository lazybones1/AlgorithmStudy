package day0211;

import java.util.Scanner;

public class BJ2491_Sukyung {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] numbers = new int[N + 1];
		int[] plus = new int[N + 1];
		int[] minus = new int[N + 1];
		int max = 1;

		for (int i = 0; i < N; i++) {
			numbers[i] = sc.nextInt();
		}

		plus[0] = 1;
		minus[0] = 1;
		for (int i = 1; i <= N; i++) {
			if (numbers[i - 1] == numbers[i]) {
				plus[i] = 1 + plus[i - 1];
				minus[i] = 1 + minus[i - 1];
				max = Math.max(plus[i - 1], max);
				max = Math.max(minus[i - 1], max);
			}
			if (numbers[i - 1] < numbers[i]) {
				plus[i] = 1 + plus[i - 1];
				minus[i] = 1;
				max = Math.max(plus[i - 1], max);
				max = Math.max(minus[i - 1], max);
			}
			if (numbers[i - 1] > numbers[i]) {
				minus[i] = 1 + minus[i - 1];
				plus[i] = 1;
				max = Math.max(plus[i - 1], max);
				max = Math.max(minus[i - 1], max);
			}
		}
		System.out.println(max);
	}
}
