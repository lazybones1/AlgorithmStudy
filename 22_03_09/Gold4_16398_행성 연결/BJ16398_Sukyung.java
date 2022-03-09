package day0309;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ16398_Sukyung {
	static int N;
	static long answer;
	static int[][] adjMatrix;
	static int[] minEdge;
	static boolean[] visit;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		adjMatrix = new int[N][N];
		minEdge = new int[N];
		answer = 0;

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
			minEdge[i] = Integer.MAX_VALUE;
		}
		prim();
		System.out.println(answer);
	}

	public static void prim() {
		visit = new boolean[N];
		minEdge[0] = 0;

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(0, minEdge[0]));

		while (!pq.isEmpty()) {
			Edge current = pq.poll();

			if (visit[current.num])
				continue;
			visit[current.num] = true;
			answer += current.cost;

			for (int i = 0; i < N; i++) {
				if (!visit[i] && adjMatrix[current.num][i] != 0 && minEdge[i] > adjMatrix[current.num][i]) {
					minEdge[i] = adjMatrix[current.num][i];
					pq.offer(new Edge(i, minEdge[i]));
				}
			}
		}
	}

	public static class Edge implements Comparable<Edge> {
		int num, cost;

		public Edge(int num, int cost) {
			this.num = num;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}
}
