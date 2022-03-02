package day0301;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1753_Sukyung {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(br.readLine());
		Graph[] graph = new Graph[V + 1];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[u] = new Graph(v, graph[u], w);
		}
		int[] distance = new int[V + 1];
		boolean[] visit = new boolean[V + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;

		for (int i = 1; i <= V; i++) {
			int min = Integer.MAX_VALUE;
			int current = 0;

			for (int j = 1; j <= V; j++) {
				if (!visit[j] && min > distance[j]) {
					min = distance[j];
					current = j;
				}
			}
			visit[current] = true;

			for (Graph temp = graph[current]; temp != null; temp = temp.link) {
				if (!visit[temp.u] && distance[temp.u] > distance[current] + temp.w) {
					distance[temp.u] = distance[current] + temp.w;
				}
			}
		}
		for (int i = 1; i <= V; i++) {
			if (distance[i] == Integer.MAX_VALUE)
				System.out.println("INF");
			else
				System.out.println(distance[i]);
		}
	}

	public static class Graph {
		int u, w;
		Graph link;

		public Graph(int u, Graph link, int w) {
			this.u = u;
			this.link = link;
			this.w = w;
		}
	}
}
