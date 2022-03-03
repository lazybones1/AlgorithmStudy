package day0223;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_7562_나이트의_이동 {

	static int[][] arr;
	static int[] di = {-1,-2,-2,-1,1,2,2,1};
	static int[] dj = {-2,-1,1,2,2,1,-1,-2};
	static int l;
	static int[][] visited;
	public static void main(String[] args) {
		
		Scanner sc= new Scanner(System.in);
		
		int tc= sc.nextInt();
		for(int t=1;t<=tc;t++) {
			l =sc.nextInt();
			arr= new int [l][l];
			visited = new int[l][l]; 
			
			int x1 = sc.nextInt();
			int y1 = sc.nextInt();
			int x2 = sc.nextInt();
			int y2 = sc.nextInt();
			cleanVisited();
			
			bfs(x1,y1,x2,y2);

	        System.out.println(visited[x2][y2]);
			
		}

	}
	
	static void bfs(int a,int b,int c,int d){

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(a,b));
        visited[a][b] = 0;

        while(!q.isEmpty()){
            Point p = q.poll();

            if(p.x == c && p.y == d) break;

            for(int i=0;i<8;i++){
                int ni = p.x + di[i];
                int nj = p.y + dj[i];

                if(0<=ni && ni<l && 0<=nj & nj<l &&
                        (visited[ni][nj]==-1 || visited[p.x][p.y]+1 < visited[ni][nj])
                ){
                    visited[ni][nj] = visited[p.x][p.y]+1;
                    q.add(new Point(ni,nj));
                }
            }


        }

    }
	
	static void cleanVisited(){
        for(int i=0;i<l;i++)
            for(int j=0;j<l;j++)
                visited[i][j]=-1;
    }

    static class Point{
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
