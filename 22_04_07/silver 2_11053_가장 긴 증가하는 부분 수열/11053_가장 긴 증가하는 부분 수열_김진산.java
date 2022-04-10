import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main{

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());

        int nums[] = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i<N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int dp[] = new int[N];
        int idx = 0;
        dp[0] = nums[0];

        for (int i = 0; i<N; i++){

            int start = 0, end = idx;
            while (start <= end){
                int mid = (start + end) / 2;
                if (dp[mid] > nums[i]){
                    end = mid -1;
                }else if (dp[mid] < nums[i]){
                    start = mid +1;
                }else if (dp[mid] == nums[i]){
                    start = mid;
                    break;
                }
            }
            if (idx < start){
                idx = start;
            }
            dp[start] = nums[i];
        }

//        System.out.println(Arrays.toString(dp));

        System.out.println(idx+1);


        br.close();
    }
}