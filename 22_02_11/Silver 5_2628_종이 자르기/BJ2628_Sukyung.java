package day0211;

import java.util.Arrays;
import java.util.Scanner;

public class BJ2628_Sukyung {
	static int R;
	static int C;
	static int answer;
	static int[] cropR;
	static int[] cropC;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		int cropNum = sc.nextInt();
		cropR = new int[R + 1];
		cropC = new int[C + 1];
		answer = 0;
		int mode0 = 1;
		int mode1 = 1;

		initialize();
		for (int i = 0; i < cropNum; i++) {
			int mode = sc.nextInt();
			int num = sc.nextInt();

			if (mode == 0) {
				cropC[mode0++] = num;
			} else {
				cropR[mode1++] = num;
			}
		}
		cropR[0] = 0;
		cropC[0] = 0;
		Arrays.sort(cropR);
		Arrays.sort(cropC);
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				getArea(cropR[i], cropR[i - 1], cropC[j], cropC[j - 1]);
			}
		}
		System.out.println(answer);
	}

	public static void initialize() {
		for (int i = 0; i < R + 1; i++) {
			cropR[i] = R;
		}
		for (int i = 0; i < C + 1; i++) {
			cropC[i] = C;
		}
	}

	public static void getArea(int cropR, int nowR, int cropC, int nowC) {
		answer = Math.max(answer, (cropR - nowR) * (cropC - nowC));
	}
}
