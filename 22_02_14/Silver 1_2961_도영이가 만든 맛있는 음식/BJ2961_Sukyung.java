package day0214;

import java.util.Scanner;

public class BJ2961_Sukyung {
	static int N;
	static int[] S;
	static int[] B;
	static int answer;
	static int count;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		S = new int[N];
		B = new int[N];
		answer = Integer.MAX_VALUE;
		count = 0;

		for (int i = 0; i < N; i++) {
			S[i] = sc.nextInt();
			B[i] = sc.nextInt();
		}
		generateSet(0, 1, 0);
		System.out.println(answer);
	}

	public static void generateSet(int index, int sSum, int bSum) {
		if (index == N) {
			count++;
			if (count != (int) (Math.pow(2, N)))
				answer = Math.min(answer, Math.abs(sSum - bSum));
			return;
		}
		generateSet(index + 1, sSum * S[index], bSum + B[index]);
		generateSet(index + 1, sSum, bSum);
	}
}
