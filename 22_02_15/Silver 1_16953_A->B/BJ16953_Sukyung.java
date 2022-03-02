package day0215;

import java.util.Scanner;

public class BJ16953_Sukyung {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int A = sc.nextInt();
		int B = sc.nextInt();
		int count = 1;

		while (A != B) {
			if (B % 10 == 1)
				B /= 10;
			else if (B % 2 == 0)
				B /= 2;
			else {
				count = -1;
				break;
			}
			count++;
			if (A > B) {
				count = -1;
				break;
			}
		}
		System.out.println(count);
	}
}
