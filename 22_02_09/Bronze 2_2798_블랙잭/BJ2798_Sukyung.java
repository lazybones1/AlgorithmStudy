package day0209;

import java.util.Arrays;
import java.util.Scanner;

public class BJ2798_Sukyung {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] cards = new int[N];

		for (int i = 0; i < N; i++) {
			cards[i] = sc.nextInt();
		}

		int minAbs = Integer.MAX_VALUE;
		int answer = 0;
		boolean keepGoing = true;
		Arrays.sort(cards);
		for (int i = 0; i < N - 2; i++) {
			if (!keepGoing)
				break;
			for (int j = 1; j < N - 1; j++) {
				if (!keepGoing)
					break;
				for (int k = 2; k < N; k++) {
					int sum = cards[i] + cards[j] + cards[k];
					if (cards[i] == cards[j] || cards[j] == cards[k] || cards[i] == cards[k])
						continue;
					if (sum > M) {
						break;
					}
					if (minAbs > M - sum) {
						minAbs = M - sum;
						answer = sum;
						if (minAbs == 0) {
							keepGoing = false;
							break;
						}
					}
				}
			}
		}
		System.out.println(answer);
	}
}
