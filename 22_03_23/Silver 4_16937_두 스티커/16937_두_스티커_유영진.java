package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16937_두_스티커 {

	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(bf.readLine());
		
		for(int t=1; t<=tc; t++) {
			int n = Integer.parseInt(bf.readLine());
			
			int[][] arr = new int[2][n+1];
			int[][]	dp = new int[2][n+1];
			for(int i=0; i<2; i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine());
				for(int j=1; j<n+1; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			dp[0][1] = arr[0][1];
			dp[1][1] = arr[1][1];
			for(int j=2; j<n+1; j++) {
				dp[0][j] = Math.max(dp[1][j-1], dp[1][j-2]) + arr[0][j];
				dp[1][j] = Math.max(dp[0][j-1], dp[0][j-2]) + arr[1][j];
			}
			System.out.println(Math.max(dp[0][n], dp[1][n]));
		}
	}
}