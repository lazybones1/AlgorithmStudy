import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
    /*
    map[][] : 능력치 판
    N : 선수 수
    visited : 방문 여부 확인
    team1,2 : 각 팀원 리스트
    ans : 능력치 최소 차
     */
    static int[][] map;
    static int N;
    static boolean[] visited;
    static List<Integer> team1, team2;
    static int ans;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        //변수 선언부
        N = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1];
        visited = new boolean[N+1];
        team1 = new ArrayList<>();
        team2 = new ArrayList<>();
        ans = Integer.MAX_VALUE;
        //map 채우기
        for (int i = 1; i<=N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1 ; j<=N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //풀이
        solve(1, 0, "");
        //출력부
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
    public static void solve(int start, int cnt, String s){
        //선수 쌍 만들기 함수
        if(cnt == N/2+1){
            //1명-3명 == 3명-1명이므로 멈춤
            return;
        }
        if (cnt > 0){
            //팀에 1명 이상일 경우
            team1.clear();
            team2.clear();
            for (int i = 1; i<=N; i++){
                //방문 여부에 따라 팀원 분배
                if(visited[i]){
                    team1.add(i);
                }else {
                    team2.add(i);
                }
            }
            //능력치 차이 최소 구하기
            ans = Math.min(ans, Math.abs(calPoints(team1) - calPoints(team2)));
        }
        for (int i = start; i<=N; i++){
            //선수쌍 만들기 - 조합
            if(visited[i]) continue;
            visited[i] = true;
            solve(i+1, cnt + 1, s+Integer.toString(i));
            visited[i] = false;
        }
    }
    public static int calPoints(List<Integer> l){
        //선수 리스트로 부터 능력치 값 차이 구하기
        int num = 0;
        for (int i = 0; i<l.size()-1; i++){
            for(int j = i+1; j<l.size(); j++){
                //i번 j번 있을 경우 Sij, Sji 구해야함
                num += map[l.get(i)][l.get(j)];
                num += map[l.get(j)][l.get(i)];
            }
        }
        return num;
    }
}