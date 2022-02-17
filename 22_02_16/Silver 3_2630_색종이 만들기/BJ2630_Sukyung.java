package day0216;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2630_Sukyung {
	static int[][] paper;
	static int blue;
	static int white;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		blue = 0;
		white = 0;
		paper = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		makeColor(0, 0, N);
		System.out.println(white);
		System.out.println(blue);
	}

	public static void makeColor(int x, int y, int N) {
		int first = paper[x][y];
		boolean same = true;
		for (int i = x; i < x + N; i++) {
			for (int j = y; j < y + N; j++) {
				if (first != paper[i][j]) {
					same = false;
				}
			}
		}

		if (same) {
			if (first == 1)
				blue++;
			else
				white++;
			return;
		}
		makeColor(x, y, N / 2);
		makeColor(x, y + N / 2, N / 2);
		makeColor(x + N / 2, y, N / 2);
		makeColor(x + N / 2, y + N / 2, N / 2);
	}
}
