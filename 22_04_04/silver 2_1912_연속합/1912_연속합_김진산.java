import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int nums[] = new int[n];
        int dp[] = new int[n];
        int ans = Integer.MIN_VALUE;

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i<n; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = nums[0];
        ans = dp[0];
        for (int i = 1; i<n; i++){
            dp[i] = Math.max(nums[i] , dp[i-1] + nums[i]);
            ans = Math.max(ans, dp[i]);
        }

        System.out.println(ans);

        br.close();
    }
}