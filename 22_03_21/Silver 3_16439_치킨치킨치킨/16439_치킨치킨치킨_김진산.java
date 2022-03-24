import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


class Main{

    static int n, m, ans, map[][];
    static int choice[];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        ans = Integer.MIN_VALUE;
        map = new int[n][m];
        choice = new int[m]; //선택 치킨


        for (int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j<m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0,0);

        System.out.println(ans);
        br.close();
    }

    static void solve(int cnt, int start){
//치킨 3개 선택
        if (cnt == 3){
            int sum = 0;
            for (int i = 0; i<n; i++){
                sum += calStati(i); //회원들의 선호도 합
            }
            ans = Math.max(ans, sum);
            return;
        }
        for (int i = start; i<m; i++){
            choice[cnt] = i;
            solve(cnt+1, start+1);
        }
    }

    static int calStati(int p){ //회원 p의 치킨 선호도
        int max = 0;
        for (int i = 0; i<3; i++){
            max = Math.max(max, map[p][choice[i]]);
        }
        return max;
    }
}