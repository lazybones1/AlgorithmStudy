package day0311;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ14621_Sukyung {
	static int N, M;
	static int[] parents;
	static String[] sex;
	static ArrayList<Edge> adjList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sex = new String[N + 1];
		adjList = new ArrayList<Edge>();
		st = new StringTokenizer(br.readLine(), " ");

		for (int i = 1; i <= N; i++) {
			sex[i] = st.nextToken();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			adjList.add(new Edge(u, v, d));
			adjList.add(new Edge(v, u, d));
		}
		makeSet();
		adjList.sort(null);
		int count = 0;
		int answer = 0;

		for (Edge e : adjList) {
			if (!sex[e.u].equals(sex[e.v]) && union(e.u, e.v)) {
				answer += e.distance;
				if (++count == N - 1) {
					break;
				}
			}
		}
		System.out.println(answer == 0 || count != N - 1 ? -1 : answer);
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

	public static class Edge implements Comparable<Edge> {
		int u, v, distance;

		public Edge(int u, int v, int distance) {
			this.u = u;
			this.v = v;
			this.distance = distance;
		}

		@Override
		public int compareTo(Edge o) {
			return this.distance - o.distance;
		}
	}
}
