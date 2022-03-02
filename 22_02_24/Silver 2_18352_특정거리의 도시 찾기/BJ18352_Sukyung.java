package day0224;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ18352_Sukyung {
	static int N, M, K, X;
	static LinkedList<Integer>[] adjList;
	static int[] cost;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		adjList = new LinkedList[N + 1];

		for (int i = 1; i <= N; i++) {
			adjList[i] = new LinkedList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adjList[u].add(v);
		}
		dijstra();

		for (int i = 1; i <= N; i++) {
			if (cost[i] == K)
				sb.append(i + "\n");
		}
		if (sb.length() == 0)
			sb.append(-1);
		System.out.println(sb);
	}

	public static void dijstra() {
		cost = new int[N + 1];
		Arrays.fill(cost, Integer.MAX_VALUE);

		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		cost[X] = 0;
		pq.add(X);

		while (!pq.isEmpty()) {
			int current = pq.poll();

			for (int out : adjList[current]) {
				if (cost[out] > cost[current] + 1) {
					cost[out] = cost[current] + 1;
					pq.add(out);
				}
			}
		}
	}
}
