import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*BOJ_12919_A와 B 2*/
/*
T를 줄여가며 확인
 1. T의 시작이 B인 경우 B제거 후 뒤집기
 2. T의 끝이 A인 경우 A 제거
 3. S와 T의 길이가 같은경우 비교
 */
class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        String S = br.readLine();
        String T = br.readLine();

        int rs = dfs(S, T) ? 1 : 0;

        System.out.println(rs);

        br.close();
    }

    public static boolean dfs(String S, String T){
        if (S.length() == T.length()){
            if(S.equals(T)){
                return true;
            }
            return false;
        }
        boolean tmp = false;
        if (T.charAt(0) == 'B'){
            String sub = T.substring(1);
            StringBuilder sb = new StringBuilder(sub);
            tmp = tmp ? true : dfs(S, sb.reverse().toString()); //tmp가 이미 true인 경우 다른거 안해도 됨
        }
        if (T.charAt(T.length()-1) == 'A'){
            tmp = tmp ? true : dfs(S, T.substring(0, T.length() - 1));
        }
        return tmp;
    }
}