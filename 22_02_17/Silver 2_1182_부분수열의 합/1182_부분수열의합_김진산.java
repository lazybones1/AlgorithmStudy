import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Main{
    static int ans;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        //N: 정수의 개수
        //S: 정수의 합이 되는 경우
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] nums = new int[N];
        ans = 0;

        //배열 채우기
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i<N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        solve(nums, N, S, 0, 0);

        if (S == 0) ans--; //0인경우 아무것도 선택 안할 수 있음

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }

    public static void solve(int[] nums, int N, int S, int start, int nowSum){
        //조합
        if (nowSum == S){
            //가능한 경우
            ans++;
        }

        for (int i = start; i<N; i++){
            solve(nums, N, S, i+1, nowSum + nums[i]);
        }
    }
}