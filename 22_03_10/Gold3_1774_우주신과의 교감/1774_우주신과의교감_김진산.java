import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    static int N;
    static List<Connection> connections[];
    static Position gots[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); // 우주신의 개수
        int M = Integer.parseInt(st.nextToken()); // 통로의 개수
        gots = new Position[N+1];
        connections = new List[N+1];

        for (int i = 1; i<=N; i++){
            connections[i] = new ArrayList<>();
        }

        //우주신 좌표
        for (int i = 1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            gots[i] = new Position(x, y);
        }

        //연결되어 있는 통로 정보
        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            connections[a].add(new Connection(b, 0));
            connections[b].add(new Connection(a, 0));
        }

        //프림 알고리즘 사용 - 정답이 int 범위를 넘어갈 수 있음
        System.out.printf("%.2f", prim());

        br.close();
    }

    public static double prim(){
        PriorityQueue<Connection> pq = new PriorityQueue<>();
        pq.offer(new Connection(1, 0));

        boolean visited[] = new boolean[N+1];
        double ans = 0;
        int cnt = 0;

        while (!pq.isEmpty()){
            Connection now = pq.poll();

            if (visited[now.num]) continue;

            visited[now.num] = true;
            ans += now.cost; //비용 더하기

            //다음 정점 선정
            boolean vertex[] = new boolean[N+1];

            for (Connection next : connections[now.num]){
                if (!visited[next.num]){
                    pq.offer(new Connection(next.num, next.cost));
                }
            }
            for (int i = 1; i<=N; i++){
                if (!visited[i] && !vertex[i]){
                    pq.offer(new Connection(i, distance(gots[i].x, gots[i].y, gots[now.num].x, gots[now.num].y)));
                }
            }
            //모든 정점 방문시 종료
            if (++cnt == N){
                break;
            }
        }
        return ans;
    }

    public static double distance(int x1, int y1, int x2, int y2){
        return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1 - y2, 2));
    }

    static class Position{
        int x;
        int y;
        public Position(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static class Connection implements Comparable<Connection>{
        int num;
        double cost;
        public Connection(int num, double cost){
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(Connection o) {
            return Double.compare(cost, o.cost);
        }
    }
}