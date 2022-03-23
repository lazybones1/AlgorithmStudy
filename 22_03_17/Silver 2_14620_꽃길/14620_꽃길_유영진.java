package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main_14620_꽃길 {
    static int N;
    static int[][] cost;
    static int min = Integer.MAX_VALUE;
    static boolean[][] visited;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        
        cost = new int[N][N];
        visited = new boolean[N][N];
        
        for (int i = 0; i < N; i++) {
            StringTokenizer  st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        solve(0,0,1);
        System.out.println(min);
    }
    
    static void solve(int cnt, int sum, int x) {
        if(cnt == 3) {
            min = Math.min(min, sum);
            return;
        }
        
        for (int i = x; i < N-1; i++) {
            for (int j = 1; j < N-1; j++) {
                if(visited[i][j]) 
                    continue;
                if(available(i, j)==false) 
                    continue;
                int c = cost[i][j];
                visited[i][j] = true;
                
                for (int k = 0; k < 4; k++) {
                    visited[i+dx[k]][j+dy[k]] = true;
                    c += cost[i+dx[k]][j+dy[k]];
                }
                solve(cnt+1, sum +c, i); // 재귀 
                
                visited[i][j] = false;
                for (int k = 0; k < 4; k++) {
                    visited[i+dx[k]][j+dy[k]] = false;
                }
            }
        }
    }
    
    static boolean available(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(x < 0 ||  x >= N || y < 0 || y >= N || visited[nx][ny])
                return false;
        }
        return true;
    }
}
