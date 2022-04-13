import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        //두 문자열 입력 받음
        //이후 처리를 위해 앞에 공백 추가
        String arr1 = " " + br.readLine();
        String arr2 = " " + br.readLine();

        //각 부분수열간의 가장 긴 공통 수열 수
        int dp[][] = new int[arr1.length()][arr2.length()];

        /*
        가로 : 기준이 될 부분 수열
        세로 : 비교할 부분 수열
        같은 경우 : 우상단 값 +1
        다른 경우 : 위, 왼쪽 중 큰 값
				dp[i][j] = arr1의 i까지의 부분 수열과 arr2의 j까지의 부분수열중 가장 긴 부분수열의 길이
        \ - A C A Y K P 
        - 0 0 0 0 0 0 0
        C 0 0 1 1 1 1 1
        A 0 1 1 2 2 2 2
        P 0 1 1 2 2 2 3
        C 0 1 2 2 2 2 3
        A 0 1 2 3 3 3 3
        K 0 1 2 3 3 4 4
         */

        for (int i = 1; i<arr1.length(); i++){
            for (int j = 1; j<arr2.length(); j++){
                if (arr1.charAt(i) == arr2.charAt(j)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }

        System.out.println(dp[arr1.length()-1][arr2.length()-1]);

        br.close();
    }
}