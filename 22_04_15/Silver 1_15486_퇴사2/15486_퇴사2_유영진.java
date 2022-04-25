package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15486_퇴사2 {
	  public static void main(String[] args) throws IOException {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        int N = Integer.parseInt(br.readLine());

	        int [] t = new int[N+1];
	        int [] p = new int[N+1];
	      
	        for(int i = 0; i < N; ++i)
	        {
	        	StringTokenizer st = new StringTokenizer(br.readLine());
	            t[i] = Integer.parseInt(st.nextToken());
	            p[i] = Integer.parseInt(st.nextToken());
	        }

	        int [] dp = new int[N+1];
	        int ans = Integer.MIN_VALUE;
	        for(int i = 0; i <= N; ++i)
	        {
	            ans = Math.max(ans,dp[i]);

	            int next = i + t[i];
	            if(next <= N)
	            {
	                dp[next] = Math.max(dp[next],ans+p[i]);
	            }
	        }
	        System.out.print(ans);
	    }
}
