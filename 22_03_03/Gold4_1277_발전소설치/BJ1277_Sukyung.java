package day0303;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1277_Sukyung {
	static int N, W;
	static double M;
	static int[][] map;
	static double[] distance;
	static ArrayList<ArrayList<Edge>> powerStation;
	static ArrayList<ArrayList<Integer>> remainStation;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		M = Double.parseDouble(br.readLine());
		map = new int[N + 1][N + 1];
		powerStation = new ArrayList<ArrayList<Edge>>();
		remainStation = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i <= N; i++) {
			powerStation.add(new ArrayList<>());
		}
		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			map[n][0] = i;
			map[n][1] = j;
		}
		for (int i = 0; i <= N; i++) {
			remainStation.add(new ArrayList<>());
		}
		for (int w = 1; w <= W; w++) {
			st = new StringTokenizer(br.readLine(), " ");
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			remainStation.get(i).add(j);
			remainStation.get(j).add(i);
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i != j) {
					if (remainStation.get(i).contains(j)) {
						powerStation.get(i).add(new Edge(j, 0));
					} else {
						double calD = calculateDistance(map[i][0], map[i][1], map[j][0], map[j][1]);
						if (calD <= M)
							powerStation.get(i).add(new Edge(j, calD));
					}
				}
			}
		}
		dijkstra();
		System.out.println((int) Math.floor(distance[N] * 1000));
	}

	public static void dijkstra() {
		distance = new double[N + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.offer(1);
		distance[1] = 0;

		while (!pq.isEmpty()) {
			int current = pq.poll();

			for (Edge p : powerStation.get(current)) {
				if (distance[p.num] > distance[current] + p.distance) {
					distance[p.num] = distance[current] + p.distance;
					pq.offer(p.num);
				}
			}
		}
	}

	public static double calculateDistance(int si, int sj, int ei, int ej) {
		return Math.sqrt(Math.pow(Math.abs(si - ei), 2) + Math.pow(Math.abs(sj - ej), 2));
	}

	public static class Edge implements Comparable<Edge> {
		int num;
		double distance;

		public Edge(int num, double distance) {
			this.num = num;
			this.distance = distance;
		}

		@Override
		public int compareTo(Edge o) {
			if (this.distance < o.distance)
				return -1;
			if (this.distance > o.distance)
				return 1;
			return 0;
		}
	}
}
