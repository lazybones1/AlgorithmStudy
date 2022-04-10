package week7;
import java.util.Scanner;

public class Main_1912_연속합 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		int arr[] = new int[n];
		int dp[] = new int[n];
		int max = Integer.MIN_VALUE;
		
		for(int i=0;i<n;i++) {
			
			arr[i] = sc.nextInt();
		}
		

		dp[0] = arr[0];
					
		for(int i=1;i<n;i++) {
		
			dp[i] = Math.max(Math.max(arr[i], dp[i-1]+arr[i]),arr[i] + arr[i-1]);
		}
		

		for(int i=0;i<n;i++) {
			if(dp[i]>max)
				max = Math.max(max, dp[i]);
		}
		
		System.out.println(max);

	}

}