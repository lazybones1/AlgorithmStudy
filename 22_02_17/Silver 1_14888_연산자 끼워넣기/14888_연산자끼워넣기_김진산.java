import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.StreamSupport;

class Main {
    static int N;
    static int[] nums;
    static int[] operNums;
    static int min, max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        /*
        N = 수의 개수
        nums[] = 주어지는 수
        operNums[] = +,-,*,/ 개수
        min = 최소값
        max = 최대값
         */
        N = Integer.parseInt(st.nextToken());
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;

        nums = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i<N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        operNums = new int[4];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i<4; i++){
            operNums[i] = Integer.parseInt(st.nextToken());
        }

        solve(1, nums[0]);
        bw.write(max + "\n"+ min);
        bw.flush();
        bw.close();
        br.close();
    }
    public static void solve(int idx, int result){
        if(idx == N){
            min = Math.min(min, result);
            max = Math.max(max, result);
            return;
        }
        int resultVal = result;
        for (int i = 0; i<4; i++){
            if(operNums[i] <= 0) continue;
            switch (i){
                case 0:
                    //+
                    resultVal = result + nums[idx];
                    break;
                case 1:
                    //-
                    resultVal = result - nums[idx];
                    break;
                case 2:
                    //*
                    resultVal = result * nums[idx];
                    break;
                case 3:
                    // /
                    resultVal = result / nums[idx];
                    break;
            }
            operNums[i]--;
            solve(idx+1, resultVal);
            operNums[i]++;
        }
    }

}