import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main{

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int map[][] = new int[n][n];
        for (int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long dp[][] = new long[n][n];
        dp[0][0] = 1;

        for (int i = 0; i<n; i++){
            for (int j = 0; j<n; j++){
                if (i == n-1 && j == n-1) break;
                if (dp[i][j] != 0){
                    int nexty = i + map[i][j];
                    int nextx = j + map[i][j];
                    if (nexty < n) dp[nexty][j] += dp[i][j];
                    if (nextx < n) dp[i][nextx] += dp[i][j];
                }
            }
        }

//        for (int i = 0; i<n; i++){
//            for (int j = 0; j<n; j++){
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();

        System.out.println(dp[n-1][n-1]);

        br.close();
    }
}