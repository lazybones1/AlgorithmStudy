package day0224;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main_18352_특정거리의_도시찾기 {

	static ArrayList<ArrayList<Integer>> graph=new ArrayList<ArrayList<Integer>>();
	static int[] dist=new int[300001];
	public static void main(String[] args) throws IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<=n;i++) {
			graph.add(new ArrayList<Integer>());
			dist[i]=-1;
		}
		for(int i =0;i<m;i++) {
			StringTokenizer stt = new StringTokenizer(bf.readLine());
			int a =Integer.parseInt(stt.nextToken());
			int b =Integer.parseInt(stt.nextToken());
			
			graph.get(a).add(b);
		}
		
		dist[x]=0; 
		Queue<Integer> q=new LinkedList<>();
		q.offer(x);
		while(!q.isEmpty()) {
			int now=q.poll();
			
			for(int i=0;i<graph.get(now).size();i++) {
				int next=graph.get(now).get(i);
				if(dist[next]==-1) {
					
					dist[next]=dist[now]+1;
					q.offer(next);
				}
			}
		}
		
		boolean check=false;
		
		for(int i=1;i<=n;i++) {
			if(dist[i]==k) {
				System.out.println(i);
				check=true;
			}
		}
	
		if(check==false)
			System.out.println(-1);
		
	}

}
