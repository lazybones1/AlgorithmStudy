package day0221;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BJ11725_Sukyung {
	static int N;
	static ArrayList<ArrayList<Integer>> list;
	static int[] parent;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		list = new ArrayList<ArrayList<Integer>>();
		parent = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<Integer>());
		}

		for (int i = 2; i <= N; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			list.get(a).add(b);
			list.get(b).add(a);
		}
		bfs();
		for (int i = 2; i <= N; i++) {
			System.out.println(parent[i]);
		}
	}

	public static void bfs() {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(1);
		parent[1] = 1;

		while (!queue.isEmpty()) {
			int current = queue.poll();

			for (int v : list.get(current)) {
				if (parent[v] == 0) {
					parent[v] = current;
					queue.offer(v);
				}
			}
		}
	}
}
