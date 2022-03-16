package day0316;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2805_Sukyung {
	static int N, M;
	static int[] tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		tree = new int[N];
		st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			tree[i] = num;
		}
		Arrays.sort(tree);
		System.out.println(binarySearch(0, tree[N - 1]));
	}

	public static long binarySearch(int start, int end) {
		int mid = (start + end) / 2;

		if (start > end)
			return mid;

		long count = 0;
		for (int t : tree) {
			if (t > mid) {
				count += t - mid;
			}
		}
		if (count < M)
			return binarySearch(start, mid - 1);
		else
			return binarySearch(mid + 1, end);
	}
}
