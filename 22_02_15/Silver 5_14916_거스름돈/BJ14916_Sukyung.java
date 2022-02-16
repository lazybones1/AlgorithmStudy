package day0215;

import java.util.Scanner;

public class BJ14916_Sukyung {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int count = 0;
		
		while (N > 0) {
			if (N % 5 == 0) {
				N -= 5;
				count++;
			} else if (N % 2 == 0) {
				N -= 2;
				count++;
			} else if (N > 5) {
				N -= 5;
				count++;
			} else {
				count = -1;
				break;
			}
		}
		System.out.println(count);
	}
}
