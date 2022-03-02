package day0301;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1753_최단경로 {
	static int V;
	static int E;
	static int start;
	static LinkedList<Edge>[] adjList;
	static int[] dist; 
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		start = Integer.parseInt(br.readLine());

		adjList = new LinkedList[V+1]; 
		
		for(int v=1; v<=V; v++) {
			adjList[v] = new LinkedList<>(); 
		}
		
		for(int e=0; e<E; e++) {
			StringTokenizer stt = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(stt.nextToken());
			int b = Integer.parseInt(stt.nextToken());
			int weight = Integer.parseInt(stt.nextToken());
			
			adjList[a].add(new Edge(b, weight));
		} 
		
		dijkstra();
		
		StringBuilder sb = new StringBuilder();
		for(int v=1; v<=V; v++) {
			if(dist[v]==Integer.MAX_VALUE) {
				sb.append("INF");
			}
				
			else {
				sb.append(dist[v]);
				}
				
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static void dijkstra() {
		dist = new int[V+1];
		
		Arrays.fill(dist, Integer.MAX_VALUE); 
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		dist[start] = 0;
		
		pq.add(new Edge(start, 0));
		
		while(!pq.isEmpty()) {
			Edge edge = pq.poll(); 
			
			int curr = edge.to; 
			
			for(Edge out:adjList[curr]) { 
				if(dist[out.to] > dist[curr]+out.weight) { 
					dist[out.to] = dist[curr]+out.weight; 
					pq.add(new Edge(out.to, dist[out.to])); 
				}
			}
		}
	}
	
	static class Edge implements Comparable<Edge>{
		int to;
		int weight;
		
		Edge(int to, int w){
			this.to = to;
			this.weight = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
}
