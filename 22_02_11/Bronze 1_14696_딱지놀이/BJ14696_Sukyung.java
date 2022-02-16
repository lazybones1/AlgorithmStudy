package day0211;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class BJ14696_Sukyung {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int TC = sc.nextInt();

		for (int tc = 1; tc <= TC; tc++) {
			int aLen = sc.nextInt();
			ArrayList<Integer> A = new ArrayList<>();
			int aSum = 0;
			for (int i = 0; i < aLen; i++) {
				A.add(sc.nextInt());
				aSum += A.get(i);
			}
			Collections.sort(A, Comparator.reverseOrder());

			int bLen = sc.nextInt();
			ArrayList<Integer> B = new ArrayList<>();
			int bSum = 0;
			for (int i = 0; i < bLen; i++) {
				B.add(sc.nextInt());
				bSum += B.get(i);
			}
			Collections.sort(B, Comparator.reverseOrder());

			int minLen = Math.min(aLen, bLen);
			for (int i = 0; i < minLen; i++) {
				if (A.get(i) > B.get(i)) {
					sb.append("A\n");
					break;
				} else if (A.get(i) < B.get(i)) {
					sb.append("B\n");
					break;
				} else {
					if (i != minLen - 1)
						continue;
					if (aSum == bSum) {
						sb.append("D\n");
						break;
					}
					if (aSum < bSum) {
						sb.append("B\n");
						break;

					}
					if (aSum > bSum) {
						sb.append("A\n");
						break;
					}
				}
			}
		}
		System.out.println(sb);
	}
}
