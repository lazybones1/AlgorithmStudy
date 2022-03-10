package day0307;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1238_Sukyung {
	static int N, M, X;
	static int[] distance;
	static ArrayList<ArrayList<Edge>> adjList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		adjList = new ArrayList<ArrayList<Edge>>();
		int[] go = new int[N + 1];
		int max = Integer.MIN_VALUE;

		for (int i = 0; i <= N; i++) {
			adjList.add(new ArrayList<Edge>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			adjList.get(start).add(new Edge(end, time));
		}
		for (int i = 1; i <= N; i++) {
			dijkstra(i);
			go[i] = distance[X];
		}
		dijkstra(X);
		for (int i = 1; i <= N; i++) {
			max = Math.max(max, go[i] + distance[i]);
		}
		System.out.println(max);
	}

	public static void dijkstra(int index) {
		distance = new int[N + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);

		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		pq.offer(new Edge(index, 0));
		distance[index] = 0;

		while (!pq.isEmpty()) {
			Edge current = pq.poll();

			for (Edge e : adjList.get(current.v)) {
				if (distance[e.v] > distance[current.v] + e.time) {
					distance[e.v] = distance[current.v] + e.time;
					pq.offer(new Edge(e.v, distance[e.v]));
				}
			}
		}
	}

	public static class Edge implements Comparable<Edge> {
		int v, time;

		public Edge(int v, int time) {
			this.v = v;
			this.time = time;
		}

		@Override
		public int compareTo(Edge o) {
			return this.time - o.time;
		}
	}
}
