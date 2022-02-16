package day0208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BJ5430_Sukyung {
	static Deque<Integer> deque;
	static boolean reverse;
	static boolean error;
	static int size;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			String function = br.readLine();
			int N = Integer.parseInt(br.readLine());
			deque = new LinkedList<>();
			reverse = false;
			error = false;
			size = N;
			String array = br.readLine();
			array = array.replace("[", "");
			array = array.replace("]", "");
			StringTokenizer st = new StringTokenizer(array, ",");
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < N; i++) {
				deque.offer(Integer.parseInt(st.nextToken()));
			}
			for (int i = 0; i < function.length(); i++) {
				if (!error)
					functionDo(function.charAt(i));
			}
			if (error) {
				sb.append("error");
			} else {
				sb.append("[");
				boolean isEmpty = true;
				while (!deque.isEmpty()) {
					isEmpty = false;
					sb.append(reverse ? deque.pollLast() + "," : deque.pollFirst() + ",");
				}
				if (!isEmpty)
					sb.setLength(sb.length() - 1);
				sb.append("]");
			}
			System.out.println(sb);
		}
	}

	static void functionDo(char toDo) {
		switch (toDo) {
		case 'R':
			reverse = !reverse;
			break;
		case 'D':
			if (size - 1 < 0) {
				error = true;
				break;
			}
			if (reverse)
				deque.pollLast();
			else
				deque.pollFirst();
			size--;
			break;
		}
	}
}
