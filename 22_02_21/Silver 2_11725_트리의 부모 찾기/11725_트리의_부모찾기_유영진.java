package day0221;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_11725_트리의_부모찾기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bf.readLine());
		List<Integer> list[] = new ArrayList[n+1];
		boolean visit[] = new boolean[n+1];
		int ans[]= new int[n+1];
		
		for(int i=0; i<list.length;i++) {
			list[i]= new ArrayList<Integer>();
		}
		
		for(int i=0;i<n-1;i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			list[b].add(a);
		}
		
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(1);
		visit[1]= true;
		
		while(!queue.isEmpty()) {
			int num = queue.poll();
			for(int i:list[num]) {
				if(!visit[i]) {
					visit[i]= true;
					ans[i]=num;
					queue.add(i);
				}
			}
		}
		for(int i=2; i<ans.length;i++) {
			System.out.println(ans[i]);
		}
		
	}
}
