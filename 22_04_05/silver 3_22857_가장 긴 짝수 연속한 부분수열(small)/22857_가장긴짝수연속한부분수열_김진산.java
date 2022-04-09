import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int nums[] = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        //투포인터 사용
        int left = 0;
        int right = 0;
        int evenCnt = 0;
        int oddCnt = 0;
        int ans = 0;

        for (int i = 0; i<N; i++){
            right = i;
            if (nums[right] % 2 == 0){
                ans = Math.max(++evenCnt, ans);
            }else{
                oddCnt++;
                while (oddCnt > K){ //홀수 수 제한
                    if (nums[left++] %2 == 0){
                        evenCnt--;
                    }else {
                        oddCnt--;
                    }
                }
            }
        }
        System.out.println(ans);

        br.close();
    }
}