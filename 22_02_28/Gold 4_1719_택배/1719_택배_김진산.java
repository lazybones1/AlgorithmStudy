import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Main {

    static int n;
    static List<Edge> edges[];
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken()); //집하장 수
        int m = Integer.parseInt(st.nextToken()); //경로의 수
        sb = new StringBuilder(); //정답 출력용

        edges = new List[n + 1]; //간선 리스트 배열

        for (int i = 1; i <= n; i++) { //간선 배열 초기화
            edges[i] = new ArrayList<>();
        }

        //간선 정보 입력
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            edges[from].add(new Edge(to, time));
            edges[to].add(new Edge(from, time));
        }

        for (int i = 1; i <= n; i++) {
            //각 집화점에서 다익스트라
            dijkstra(i);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean visited[] = new boolean[n + 1];
        int distance[] = new int[n + 1];
        int path[] = new int[n + 1];

        Arrays.fill(distance, Integer.MAX_VALUE);

        distance[start] = 0;
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge vertax = pq.poll(); //선택한 정점

            if (visited[vertax.num]) continue; //방문했던 정점인 경우 패스

            visited[vertax.num] = true; //정정 확정

            for (Edge e : edges[vertax.num]) { //간선 탐색
                if (!visited[e.num] && distance[e.num] > distance[vertax.num] + e.time) {
                    distance[e.num] = distance[vertax.num] + e.time;
                    if (vertax.num == start) { //시작 정점인 경우 자신의 값을 path에 입력
                        path[e.num] = e.num;
                    } else { //시작정점이 아닌 경우 현재 보고 있는 정점의 path 값 입력
                        path[e.num] = path[vertax.num];
                    }
                    pq.offer(new Edge(e.num, distance[e.num]));
                }
            }
        }

        //path 정보 sb에 변환하여 저장
        for (int i = 1; i <= n; i++) {
            if (i == start)
                sb.append("- ");
            else sb.append(path[i] + " ");
        }
        sb.append("\n");
    }

    //간선 클래스
    static class Edge implements Comparable<Edge>{
        int num;
        int time;

        public Edge(int num, int time) {
            this.num = num;
            this.time = time;
        }

        @Override
        public int compareTo(Edge o) {
            return time - o.time;
        }
    }
}