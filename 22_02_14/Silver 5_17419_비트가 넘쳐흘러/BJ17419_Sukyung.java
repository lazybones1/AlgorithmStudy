package day0214;

import java.util.Scanner;

public class BJ17419_Sukyung {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		String binary = sc.next();
		int answer = 0;
		for (int i = 0; i < N; i++) {
			if (binary.charAt(i) == '1')
				answer++;
		}
		System.out.println(answer);
	}
}
