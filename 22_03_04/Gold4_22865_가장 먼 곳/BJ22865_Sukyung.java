package day0304;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ22865_Sukyung {
	static int N;
	static int[] distance;
	static int[][] abcDistance;
	static ArrayList<ArrayList<Edge>> house;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] ABC = new int[3];

		for (int i = 0; i < 3; i++) {
			ABC[i] = Integer.parseInt(st.nextToken());
		}
		int M = Integer.parseInt(br.readLine());
		house = new ArrayList<ArrayList<Edge>>();

		for (int i = 0; i <= N; i++) {
			house.add(new ArrayList<Edge>());
		}
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			house.get(u).add(new Edge(v, cost));
			house.get(v).add(new Edge(u, cost));
		}
		int max = Integer.MIN_VALUE;
		int answer = 0;
		abcDistance = new int[3][N + 1];

		for (int i = 0; i < 3; i++) {
			dijkstra(ABC[i], i);
		}
		for (int i = 1; i <= N; i++) {
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < 3; j++) {
				if (min > abcDistance[j][i])
					min = abcDistance[j][i];
			}
			if (max < min) {
				max = min;
				answer = i;
			}
		}
		System.out.println(answer);
	}

	public static void dijkstra(int start, int index) {
		distance = new int[N + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(start, 0));
		distance[start] = 0;

		while (!pq.isEmpty()) {
			Edge current = pq.poll();

			for (Edge e : house.get(current.v)) {
				if (distance[e.v] > distance[current.v] + e.distance) {
					distance[e.v] = distance[current.v] + e.distance;
					abcDistance[index][e.v] = distance[current.v] + e.distance;
					pq.offer(new Edge(e.v, distance[e.v]));
				}
			}
		}
	}

	public static class Edge implements Comparable<Edge> {
		int v, distance;

		public Edge(int v, int distance) {
			this.v = v;
			this.distance = distance;
		}

		@Override
		public int compareTo(Edge o) {
			return this.distance - o.distance;
		}
	}
}
