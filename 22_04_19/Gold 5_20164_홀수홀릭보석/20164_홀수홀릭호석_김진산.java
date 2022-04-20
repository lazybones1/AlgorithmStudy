import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*BOJ_20164_홀수 홀릭 호석*/
/*
* 1. 재귀를 통해 모든 경우 탐색
*   1) 문자열의 홀수 개수 구하기
*   2) 문자열의 길이가 1인경우 최대, 최소와 비교
*   3) 문자열 길이가 2인경우 각 자리의 수 합 구하고 재귀 돌리기
*   4) 문자열 길이가 3 이상인경우 2개의 기준점을 선택하고 문자열을 분리한 후 합 계산 후 재귀 돌리기
* 2. 최소값, 최대값 출력
*/
class Main {

    static int max, min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        String N = br.readLine();

        max = Integer.MIN_VALUE; //최대값
        min = Integer.MAX_VALUE; //최솟값

        dfs(0, N);//재귀를 통해서 모든 경우의 수 탐색

        System.out.println(min + " " + max);
        br.close();
    }

    public static void dfs(int cnt, String N){
        cnt += oddCnt(N);
        if (N.length() == 1){ //수가 1자리인 경우
            max = Math.max(max, cnt);
            min = Math.min(min, cnt);
            return;
        }else if(N.length() == 2){
            int newNum = (int)(N.charAt(0) - '0') + (int)(N.charAt(1) - '0');
            dfs(cnt, Integer.toString(newNum));
        }else{
            for(int i = 0;i<N.length()-2; i++){ //1번 기준
                for (int j = i+1; j<N.length()-1; j++){ //2번 기준 (마지막이 될경우 숫자를 3개 못만듬)
                    String s1 = N.substring(0, i+1); //시작~1번 기준 까지
                    String s2 = N.substring(i+1, j+1); //1번 기준 +1부터 2번 기준 까지
                    String s3 = N.substring(j+1); //2번 기준 +1 부터 3번 까지

                    int newNum = Integer.parseInt(s1) + Integer.parseInt(s2) + Integer.parseInt(s3); //값 계산
                    dfs(cnt, Integer.toString(newNum));
                }
            }
        }
    }

    //각 자리 중에서 홀수의 개수
    public static int oddCnt(String N){
        int cnt = 0;
        int leng = N.length();
        for(int i = 0; i<leng; i++){
            if (((int)(N.charAt(i)) - '0') % 2 != 0){
                cnt++;
            }
        }
        return cnt;
    }
}