import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*BOJ_17610_양팔저울*/
class Main {

    static int k, s, weight[], cal[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        k = Integer.parseInt(st.nextToken()); // 추의 개수

        s = 0; //모든 추의 무게 합

        //추 무게 입력
        st = new StringTokenizer(br.readLine(), " ");
        weight = new int[k];
        for(int i = 0; i<k; i++){
            weight[i] = Integer.parseInt(st.nextToken());
            s+=weight[i]; //추 무게 합 구하기
        }

        cal = new int[s+1]; //만들수 있는 모든 경우의 수 저장

        subset(0, 0); //부분집합을 이용하여 모든 경우의 수 구하기

        int ans = 0;
        for(int i = 1; i<=s; i++){
            if(cal[i] != 1){ //만들수 없는 경우
                ans++;
            }
        }

//        System.out.println(Arrays.toString(cal));

        System.out.println(ans);
        br.close();
    }

    //부분집합
    public static void subset(int idx, int cnt){
        if(idx == k){
            return;
        }
        subset(idx +1, cnt); //선택 안한 경우

        // + 로 사용한 경우
        if(cnt + weight[idx] > 0){
            cal[cnt + weight[idx]] = 1;
        }
        subset(idx + 1, cnt + weight[idx]);

        // - 로 사용한 경우
        if(cnt - weight[idx] > 0){
            cal[cnt - weight[idx]] = 1;
        }
        subset(idx + 1, cnt - weight[idx]);
    }
}