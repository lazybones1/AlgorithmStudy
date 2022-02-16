import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Main{
    static int n;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        /*
        n : 주어지는 3의 배수
        ans : 자연수 3개로 분리하는 개수
         */
        n = Integer.parseInt(br.readLine());
        ans = 0;

        solve(0, 0);

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
    public static void solve(int num, int cnt){
        if(cnt == 2){
            //수 2개가 주어지면 나머지 수가 3의 배수일 경우 통과
            if ((n-num)%3 == 0 && num<n){
                ans++;
            }
            return;
        }else if(num > n){
            return;
        }
        for (int i = 1; i<=n/3; i++){
            //3의 배수로 재귀
            solve(num+(i*3), cnt+1);
        }
    }
}