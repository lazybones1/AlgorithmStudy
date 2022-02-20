package day0216;

import java.util.Scanner;

public class BJ2447_Sukyung {
	static char[][] star;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int N = sc.nextInt();
		star = new char[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				star[i][j] = ' ';
			}
		}
		printStar(0, 0, N);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(star[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	public static void printStar(int x, int y, int size) {
		if (size == 1) {
			star[x][y] = '*';
			return;
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (!(i == 1 && j == 1))
					printStar(x + i * size / 3, y + j * size / 3, size / 3);
			}
		}
	}
}
