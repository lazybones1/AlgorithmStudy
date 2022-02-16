package day0209;

import java.util.Scanner;
import java.util.stream.IntStream;

public class BJ15661_Sukyung {
	static int[][] team;
	static int[] startComb;
	static int[] linkComb;
	static int N;
	static int answer;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		team = new int[N][N];
		answer = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				team[i][j] = sc.nextInt();
			}
		}
		for (int i = 1; i < N; i++) {
			startComb = new int[i];
			linkComb = new int[N - i];
			combination(0, 0, i);
		}
		System.out.println(answer);
	}

	public static void combination(int start, int count, int R) {
		if (count == R) {
			int startSkill = 0;
			int linkSkill = 0;
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				int num = i;
				if (!IntStream.of(startComb).anyMatch(x -> x == num)) {
					linkComb[cnt++] = i;
				}
			}
			for (int i = 0; i < linkComb.length; i++) {
				for (int j = 0; j < linkComb.length; j++) {
					if (i == j) {
						break;
					}
					linkSkill += team[linkComb[i]][linkComb[j]];
					linkSkill += team[linkComb[j]][linkComb[i]];
				}
			}
			for (int i = 0; i < count; i++) {
				for (int j = 0; j < count; j++) {
					if (i == j) {
						break;
					}
					startSkill += team[startComb[j]][startComb[i]];
					startSkill += team[startComb[i]][startComb[j]];
				}
			}
			answer = Math.min(answer, Math.abs(startSkill - linkSkill));
			return;
		}
		for (int i = start; i < N; i++) {
			startComb[count] = i;
			combination(i + 1, count + 1, R);
		}
	}
}
