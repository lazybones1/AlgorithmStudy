package day0207;

import java.util.Scanner;
import java.util.Stack;

public class BJ17608 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Stack<Integer> stack = new Stack<>();
		int N = sc.nextInt();
		for (int i = 0; i < N; i++) {
			stack.push(sc.nextInt());
		}
		int now = stack.pop();
		int count = 1;
		while (!stack.isEmpty()) {
			int top = stack.pop();
			if (now < top) {
				count++;
				now = top;
			}
		}
		System.out.println(count);
	}
}
