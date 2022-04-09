import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {

    public static int[][] chicken;
    public static boolean[] check;
    public static int N, M;
    public static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        chicken = new int[N][M];
        check = new boolean[M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                chicken[i][j] = Integer.parseInt(st.nextToken());
            }
            // System.out.println(Arrays.toString(chicken[i]));
        }
        com(0, 0);
        System.out.println(result);
    }

    public static void com(int idx, int cnt) {
        if (cnt == 3) {// 3개 선택한 경우
            int sum = 0;
            int index = 0;
            int[] nums = new int[3];
            for (int i = 0; i < M; i++) {
                if (check[i]) {
                    nums[index] = i;
                    index++;
                }
            }
            // System.out.println(Arrays.toString(nums));
            for (int i = 0; i < N; i++) {
                int maxNum = 0;
                for (int n : nums) {
                    maxNum = Math.max(maxNum, chicken[i][n]);
                }
                sum += maxNum;
            }
            result = Math.max(result, sum);
            return;
        }

        if (idx == M) {
            return;
        }

        check[idx] = true;
        com(idx + 1, cnt + 1);
        check[idx] = false;
        com(idx + 1, cnt);
    }
}