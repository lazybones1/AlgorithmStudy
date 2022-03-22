import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{

    static int N;
    static char genders[];
    static List<Road> roads[];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        genders = new char[N+1];
        roads = new List[N+1];
        for (int i = 1; i<=N; i++){
            roads[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i<=N; i++){
            genders[i] = st.nextToken().charAt(0);
        }

        for (int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            roads[u].add(new Road(v, d));
            roads[v].add(new Road(u, d));
        }

        System.out.println(prim());
        br.close();
    }

    public static int prim(){
        PriorityQueue<Road> pq = new PriorityQueue<>();
        pq.offer(new Road(1, 0));

        boolean visited[] = new boolean[N+1];
        int ans = 0;
        int cnt = 0;

        while (!pq.isEmpty()){
            Road now = pq.poll();

            if (visited[now.to]) continue;
            visited[now.to] = true;

            ans += now.distance;
            if (++cnt == N){
                return ans;
            }

            for (Road next : roads[now.to]){
                if (!visited[next.to] && genders[next.to] != genders[now.to])
                    pq.offer(new Road(next.to, next.distance));
            }
        }
	//모든 연결이 안되는 경우
        return -1;
    }

    static class Road implements Comparable<Road>{
        int to;
        int distance;

        public Road(int to, int distance){
            this.to = to;
            this.distance = distance;
        }

        @Override
        public int compareTo(Road o) {
            return distance - o.distance;
        }
    }
}