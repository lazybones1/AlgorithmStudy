import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


class Main{

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());

        int stairs[] = new int[N+1];
        int dp[] = new int[N+1];

        for (int i = 1; i<=N; i++){
            stairs[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = 0;
        for (int i = 1; i<=N; i++){
            if (i == 1) //1인경우 획득 가능 점수는 1가지
                dp[1] = stairs[i];
            else if(i==2){ //3계단 연속 안됨
                dp[i] = Math.max(dp[i-1] + stairs[i], dp[i-2] + stairs[i]); //1계단 전 or 2계단 전
            }
            else{
                //1계단 뛰기
                //1계단 전 : dp[i-1] + stairs[i]

                //2계단 뛰기
                //2계단 전 : dp[i-2] + stairs[i]

                //연속 3계단은 안됨 -> 3계단 전 + 바로전 계단 + 현재
                //3계단 전 : dp[i-3] + stairs[i-1] + stairs[i]
                dp[i] = Math.max(dp[i-2] + stairs[i], dp[i-3] + stairs[i-1] + stairs[i]);
            }
        }

        System.out.println(dp[N]);
        br.close();
    }
}