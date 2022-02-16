package day0207;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ10828 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < TC; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			String toDo = st.nextToken();
			if (toDo.equals("push")) {
				stack.push(Integer.parseInt(st.nextToken()));
			} else if (toDo.equals("pop")) {
				if (stack.isEmpty())
					System.out.println(-1);
				else
					System.out.println(stack.pop());
			} else if (toDo.equals("size")) {
				System.out.println(stack.size());
			} else if (toDo.equals("empty")) {
				if (stack.isEmpty())
					System.out.println(1);
				else
					System.out.println(0);
			} else if (toDo.equals("top")) {
				if (stack.isEmpty())
					System.out.println(-1);
				else
					System.out.println(stack.peek());
			}
		}
	}
}
