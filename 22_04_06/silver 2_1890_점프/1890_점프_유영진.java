package week7;

import java.util.Scanner;

public class Main_1890_점프 {
	public static void main(String[] args) {
		
			Scanner sc = new Scanner(System.in);
			int[][] map = new int[101][101];
		    long[][] dp = new long[101][101]; 
		    int n = sc.nextInt();
		 
		    dp[0][0] = 1; 
		 
		    for (int i = 0; i < n; i++) {
		        for (int j = 0; j < n; j++) {
		            map[i][j] = sc.nextInt();
		        }
		    }
		 
		    for (int i = 0; i < n; i++) {
		        for (int j = 0; j < n; j++) {
		        	
		            if (dp[i][j] == 0 || (i == n - 1 && j == n - 1)) continue;
		            
		 
		            int dist = map[i][j];
		            int down = dist + i;
		            int right = dist + j;
		 
		            if (down < n) {
		                dp[down][j] += dp[i][j];
		            }
		 
		            if (right < n) {
		                dp[i][right] += dp[i][j];
		            }
		        }
		    }
		 
		    System.out.println(dp[n - 1][n - 1]);
		}
}

