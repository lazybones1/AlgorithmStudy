package day0228;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1719_Sukyung {
	static int N, M;
	static ArrayList<ArrayList<Edge>> adjList;
	static int[] cost;
	static int[][] answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adjList = new ArrayList<ArrayList<Edge>>();
		answer = new int[N + 1][N + 1];

		for (int i = 0; i <= N; i++) {
			adjList.add(new ArrayList<Edge>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList.get(u).add(new Edge(v, weight));
			adjList.get(v).add(new Edge(u, weight));
		}
		for (int i = 1; i <= N; i++) {
			dijstra(i);
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j)
					sb.append("- ");
				else
					sb.append(answer[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	public static void dijstra(int index) {
		cost = new int[N + 1];
		Arrays.fill(cost, Integer.MAX_VALUE);

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		cost[index] = 0;
		pq.offer(index);

		while (!pq.isEmpty()) {
			int current = pq.poll();

			for (Edge e : adjList.get(current)) {
				if (cost[e.v] > cost[current] + e.weight) {
					cost[e.v] = cost[current] + e.weight;
					answer[e.v][index] = current;
					pq.offer(e.v);
				}
			}
		}
	}

	public static class Edge {
		int v, weight;

		public Edge(int v, int w) {
			this.v = v;
			this.weight = w;
		}
	}
}
