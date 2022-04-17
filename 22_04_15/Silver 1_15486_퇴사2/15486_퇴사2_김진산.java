import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken()); //남은 일수

        int t[] = new int[N+2]; //상담 시간 // 1일 ~ 종료일 N일 + 퇴사일 1일
        int p[] = new int[N+2]; //금액

        //정보 입력
        for (int i = 1; i<=N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        int dp[] = new int[N+2];

        int max  = -1;
        
        for (int i = 1; i<=N+1; i++){
           max = Math.max(max, dp[i]); //해당 일에서 가장 급여가 높은 값 찾기
           if (i+t[i] <= N+1){ //해당 요일 다음 요일 일당 확인
               dp[i+t[i]] = Math.max(max + p[i], dp[i + t[i]]);
           }
        }

        System.out.println(max);

        br.close();
    }
}