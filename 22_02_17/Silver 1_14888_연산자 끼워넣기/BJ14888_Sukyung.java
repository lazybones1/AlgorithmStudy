package day0217;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14888_Sukyung {
	static int N, size, min, max;
	static int[] numbers, numtempOperator;
	static char[] tempOperator, operator, permuOperator;
	static char[] op = { '+', '-', '*', '/' };
	static boolean[] used;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		numbers = new int[N];
		numtempOperator = new int[4];
		tempOperator = new char[10];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine(), " ");
		int cnt = 0;
		size = 0;
		
		for (int i = 0; i < 4; i++) {
			numtempOperator[i] = Integer.parseInt(st.nextToken());
			size += numtempOperator[i];
			for (int j = 0; j < numtempOperator[i]; j++) {
				tempOperator[cnt++] = op[i];
			}
		}
		operator = new char[size];
		permuOperator = new char[size];
		used = new boolean[size];
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		System.arraycopy(tempOperator, 0, operator, 0, size);
		permutation(0);
		System.out.println(max);
		System.out.println(min);
	}

	public static int calculate(int sum, int num, char operator) {
		if (operator == '+') {
			sum += num;
		} else if (operator == '-') {
			sum -= num;
		} else if (operator == '*') {
			sum *= num;
		} else {
			sum /= num;
		}
		return sum;
	}

	public static void permutation(int count) {
		if (count == size) {
			int sum = numbers[0];
			for (int i = 0; i < size; i++) {
				sum = calculate(sum, numbers[i + 1], permuOperator[i]);
			}
			min = Math.min(min, sum);
			max = Math.max(max, sum);
			return;
		}
		for (int i = 0; i < size; i++) {
			if (used[i])
				continue;
			permuOperator[count] = operator[i];
			used[i] = true;
			permutation(count + 1);
			used[i] = false;
		}
	}

}
