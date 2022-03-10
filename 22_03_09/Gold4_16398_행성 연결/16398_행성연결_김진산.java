import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Main {

    static int N, map[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); 
        map = new int[N][N];

        for (int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //프림 알고리즘 사용 - 정답이 int 범위를 넘어갈 수 있음
        bw.write(String.valueOf(Math.round(prim())));

        bw.flush();
        bw.close();
        br.close();
    }

    public static double prim(){
        PriorityQueue<Planet> pq = new PriorityQueue<>();
        pq.offer(new Planet(0, 0));

        boolean visited[] = new boolean[N+1];
        double ans = 0; //최소비용
        int cnt = 0; //방문 정점 수
        int max = Integer.MIN_VALUE; 

        while (!pq.isEmpty()){
            Planet now = pq.poll();

            if (visited[now.num]) continue;

            visited[now.num] = true;
            ans += now.cost; //비용 더하기

            max = Math.max(max, now.cost);

            //다음 정점 선정
            for (int i = 0; i<N; i++){
                if (!visited[i]){
                    pq.offer(new Planet(i, map[now.num][i]));
                }
            }
            //모든 정점 방문시 종료
            if (++cnt == N){
                break;
            }
        }
        return ans;
    }
    static class Planet implements Comparable<Planet>{
        int num;
        int cost;
        public Planet(int num, int cost){
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(Planet o) {
            return cost - o.cost;
        }
    }
}