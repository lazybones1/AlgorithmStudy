package day0218;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2606_바이러스 {
	static int V,E;
	static int[][] arr;
	static Queue<Integer> queue = new LinkedList<>();
	static boolean[] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		V = Integer.parseInt(bf.readLine());	
		E = Integer.parseInt(bf.readLine());
		arr = new int[V+1][V+1];
		visited = new boolean[V+1];
		for(int i=0;i<E;i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			arr[e][s] = arr[s][e] = 1;
		}
		queue.offer(1);
		
		bfs();
		
		int result=0;
 
		for(int i=2;i<=V;i++) {
			if(visited[i]) result++;
		}
		System.out.println(result);
		
	}
	private static void bfs() {
		visited[1]=true;
		while(!queue.isEmpty()) {
			int s=queue.poll();
			for (int i = 1; i <= V; i++) {
				if (arr[s][i] == 1 && visited[i] == false) {
					queue.offer(i);
					visited[i] = true;

				}
			}

		}
		
	}

}