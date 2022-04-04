import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{

    /**왼전탐색*/
    static int n, max, t[], p[];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken()); //퇴사까지 남은 시간
        t = new int[n+1]; //상담 시간
        p = new int[n+1]; //비용

        max = Integer.MIN_VALUE; //최대 비용

        //입력 부
        for (int i = 1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i<=n; i++){
            //시작 날자 구하기
            dfs(i, 0);
        }

        System.out.println(max);

        br.close();
    }

    public static void dfs(int day, int cost){
        if(day > n+1) {
            //날자가 넘어가는 경우
            return;
        }else if(day == n+1){
            //마지막날까지 상담 한경우
            max = Math.max(max, cost);
            return;
        }else{
            //그외 모든 경우
            max = Math.max(max, cost);
        }
        if(day + t[day] <= n+1){
            //해당 날자 상담 하는 경우
            dfs(day + t[day], cost + p[day]);
        }
        //그냥 넘어가는 경우
        dfs(day +1, cost);
    }
}