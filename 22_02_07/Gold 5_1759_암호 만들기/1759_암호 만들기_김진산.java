import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
    /*
    l : 암호의 길이
    c : 주어진 문자 수
    chars : 주어진 문자 배열
    visited : 방문 확인
     */
    static int l, c;
    static String[] chars;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        //값 할당
        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        chars = br.readLine().split(" ");
        visited = new boolean[c];
        //사전순 출력 -> 정렬
        Arrays.sort(chars);

        solve(0, 0,"");

        bw.flush();
        bw.close();
        br.close();
    }

    public static void solve(int cnt, int start, String s){
        if (cnt == l){
            //문제 조건 = 최소 한개의 모음(a,e,i,o,u), 최소 2개의 자음
            int n1 = 0;
            int n2 = 0;
            for(int i = 0; i<s.length(); i++){
                if(s.charAt(i) == 'a' || s.charAt(i) == 'e' || s.charAt(i) == 'i' || s.charAt(i) == 'o' || s.charAt(i) == 'u')
                    n1++;
                else n2++;
                if(n1 >= 1 && n2>=2) {
                    System.out.println(s);
                    return;
                }
            }
            return;
        }
        for (int i = start; i<c; i++){
            //조합
            if(visited[i]) continue;
            visited[i] = true;
            solve(cnt +1, i+1, s+chars[i].toString());
            visited[i] = false;
        }
    }
}