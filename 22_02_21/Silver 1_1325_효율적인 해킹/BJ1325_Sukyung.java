package day0221;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ1325_Sukyung {
	static int N, M;
	static int[] computers;
	static boolean[] visit;
	static ArrayList<ArrayList<Integer>> trust;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		trust = new ArrayList<ArrayList<Integer>>();
		computers = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			trust.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			trust.get(from).add(to);
		}
		int max = Integer.MIN_VALUE;

		for (int i = 1; i <= N; i++) {
			visit = new boolean[N + 1];
			dfs(i);
		}
		for (int i = 1; i <= N; i++) {
			max = Math.max(max, computers[i]);
		}
		for (int i = 1; i <= N; i++) {
			if (computers[i] == max) {
				System.out.print(i + " ");
			}
		}
	}

	public static void dfs(int now) {
		visit[now] = true;

		for (int v : trust.get(now)) {
			if (!visit[v]) {
				computers[v]++;
				dfs(v);
			}
		}
	}
}
