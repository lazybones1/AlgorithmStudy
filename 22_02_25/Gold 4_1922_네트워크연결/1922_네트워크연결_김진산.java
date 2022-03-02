import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
    static int N, M;
    static List<Edge> edges[];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        edges = new List[N+1];
        for (int i = 0; i<N+1; i++){
            edges[i] = new ArrayList<>();
        }

        for (int i= 0; i<M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges[a].add(new Edge(b, c)); //양방향 그래프
            edges[b].add(new Edge(a, c));
        }


        bw.write(String.valueOf(prim(N)));
        bw.flush();
        bw.close();
        br.close();
    }

    public static int prim(int v){
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[v+1];
        int ans = 0;
        int cnt = 0;

        pq.add(new Edge(1, ans)); //시작정점 : 1 //임의로 설정함

        while (!pq.isEmpty()){
            Edge edge = pq.poll();

            if (visited[edge.to]) continue; //다음 정점 방문 했으면 넘어감
            visited[edge.to] = true;
            ans += edge.cost;

            for (Edge next : edges[edge.to]){
                if (!visited[next.to])
                    pq.offer(next);
            }
            if (++cnt == v) break;
        }
        return ans;
    }

    static class Edge implements Comparable<Edge>{
        int to;
        int cost;
        public Edge(int to, int cost){
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return cost - o.cost;
        }
    }
}