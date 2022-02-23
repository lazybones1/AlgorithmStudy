package day0217;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1182_Sukyung {
	static int N, S, answer;
	static int[] num;
	static boolean[] used;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		num = new int[N];
		used = new boolean[N];
		answer = 0;
		st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		subSet(0);
		System.out.println(answer);
	}

	public static void subSet(int count) {
		if (count == N) {
			int sum = 0;
			int elementCount = 0;
			for (int i = 0; i < N; i++) {
				if (used[i]) {
					elementCount++;
					sum += num[i];
				}
			}
			if (sum == S && elementCount > 0) {
				answer++;
			}
			return;
		}
		used[count] = true;
		subSet(count + 1);
		used[count] = false;
		subSet(count + 1);
	}
}
