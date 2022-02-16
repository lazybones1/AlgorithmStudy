package day0209;

import java.util.Scanner;
import java.util.Stack;

public class BJ1874_Sukyung {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Stack<Integer> stack = new Stack<>();
		int[] array = new int[N];

		for (int i = 0; i < N; i++) {
			array[i] = sc.nextInt();
		}
		int index = 0;
		boolean no = false;
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < N; i++) {
			if (no)
				break;
			boolean keep = false;
			while (!keep) {
				if (index > N) {
					sb.setLength(0);
					sb.append("NO");
					no = true;
					break;
				}
				if (stack.isEmpty() || array[i] != Integer.valueOf(stack.peek()).intValue()) {
					stack.push(++index);
					sb.append("+\n");
				} else {
					stack.pop();
					sb.append("-\n");
					keep = true;
				}
			}
		}
		System.out.println(sb);
	}
}
