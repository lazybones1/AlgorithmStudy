import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int V = Integer.parseInt(st.nextToken()); //정점의 수
        int E = Integer.parseInt(st.nextToken()); //간선늬 수
        int K = Integer.parseInt(br.readLine()); // 시작 정점

        List<Edge> list[] = new ArrayList[V+1];
        for (int i = 1; i<=V; i++){ //배열 초기화
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i<=E; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken()); //시작 정점
            int v = Integer.parseInt(st.nextToken()); //도착 정점;
            int w = Integer.parseInt(st.nextToken());//간선의 가중치

            list[u].add(new Edge(v, w));
        }

        boolean[] visited = new boolean[V+1]; //도달 완료한 정점 표시
        int[] distance = new int[V+1]; //각 정점 까지의 비용

        Arrays.fill(distance, Integer.MAX_VALUE); //초기화
        distance[K] = 0; //시작 정점의 거리는 0

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(K, 0));

        //우선순위큐 사용
        while (!pq.isEmpty()){
            Edge cur = pq.poll();

            if (visited[cur.to]) continue; //확정된 점점이면 다음꺼 확인

            visited[cur.to] = true;

	//간선 탐색
            for (Edge e : list[cur.to]){
                if (!visited[e.to] && distance[e.to] > distance[cur.to] + e.weight)
                    distance[e.to] = distance[cur.to] + e.weight;
                pq.offer(new Edge(e.to, distance[e.to]));
            }
        }

        for (int i = 1; i<=V; i++){
            if (distance[i] == Integer.MAX_VALUE)
                bw.write("INF\n");
            else
                bw.write(distance[i] + "\n");
        }

        bw.write("");
        bw.flush();
        bw.close();
        br.close();
    }

    static class Edge implements Comparable<Edge> {
        int to;
        int weight;

        public Edge(int to, int weight){
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return weight - o.weight;
        }
    }
}