package day0310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ1774_Sukyung {
	static int N;
	static int[] parents;
	static ArrayList<Edge> edge;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] universe = new int[N + 1][2];
		edge = new ArrayList<Edge>();

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			universe[i][0] = Integer.parseInt(st.nextToken());
			universe[i][1] = Integer.parseInt(st.nextToken());
		}
		for (int i = 1; i <= N - 1; i++) {
			for (int j = 2; j <= N; j++) {
				edge.add(new Edge(i, j,
						calculateDistance(universe[i][0], universe[i][1], universe[j][0], universe[j][1])));
			}
		}
		makeSet();
		edge.sort(null);
		double distance = 0;
		int count = 0;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (findSet(a) != findSet(b)) {
				count++;
				union(a, b);
			}
		}
		if (count < N - 1) {
			for (Edge e : edge) {
				if (union(e.x, e.y)) {
					distance += e.distance;
					if (++count == N - 1)
						break;
				}
			}
		}
		System.out.printf("%.2f", distance);
	}

	public static void makeSet() {
		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}

	public static int findSet(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = findSet(parents[a]);
	}

	public static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);

		if (aRoot == bRoot)
			return false;
		parents[bRoot] = aRoot;
		return true;
	}

	public static double calculateDistance(int sx, int sy, int ex, int ey) {
		return Math.sqrt(Math.pow(Math.abs(sx - ex), 2) + Math.pow(Math.abs(sy - ey), 2));
	}

	public static class Edge implements Comparable<Edge> {
		int x, y;
		double distance;

		public Edge(int x, int y, double distance) {
			this.x = x;
			this.y = y;
			this.distance = distance;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.distance, o.distance);
		}
	}
}
