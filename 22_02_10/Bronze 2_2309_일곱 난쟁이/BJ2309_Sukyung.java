package day0210;

import java.util.Arrays;
import java.util.Scanner;

public class BJ2309_Sukyung {
	static int[] people;
	static int[] seven;
	static boolean keepGoing;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		people = new int[9];
		seven = new int[7];
		keepGoing = true;

		for (int i = 0; i < 9; i++) {
			people[i] = sc.nextInt();
		}
		Arrays.sort(people);
		combination(0, 0, 0);
	}

	public static void combination(int start, int count, int sum) {
		if (count == 7) {
			if (sum == 100) {
				keepGoing = false;
				for (int i = 0; i < 7; i++) {
					System.out.println(seven[i]);
				}
			}
			return;
		}
		for (int i = start; i < 9; i++) {
			if (!keepGoing)
				break;
			seven[count] = people[i];
			combination(i + 1, count + 1, sum + people[i]);
		}
	}
}
