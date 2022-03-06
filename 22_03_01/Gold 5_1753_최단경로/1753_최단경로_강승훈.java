import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int K = Integer.parseInt(br.readLine()); // 시작점

        // Point[] point = new Point[V + 1];
        ArrayList<Point>[] point = new ArrayList[V + 1];

        for (int i = 0; i <= V; i++) {
            point[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken()); // u 에서
            int v = Integer.parseInt(st.nextToken()); // v 로 가는
            int w = Integer.parseInt(st.nextToken()); // 가중치 w의 값

            point[u].add(new Point(v, w));

        }
        // for(int i = 1; i <= V; i++) {
        // System.out.println(point[i].toString());
        // }
        //////////// end input/////////////
        if (V == 1) {
            System.out.println(0);
            return;
        }

        int[] result = dijkstra(V, K, point);
        for (int i = 1; i <= V; i++) {
            if (result[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(result[i]);
            }
        }

    }

    public static class Point {
        int to;
        int weight;

        public Point(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

    }

    public static int[] dijkstra(int V, int K, ArrayList<Point>[] point) {
        boolean[] visited = new boolean[V + 1]; // 해당 정점이 최소값 세팅이 되었을 때 방문처리를 함

        int[] distance = new int[V + 1];
        Arrays.fill(distance, Integer.MAX_VALUE); // 최소값 비교를 위해 초기 값을 가장 큰 값으로 설정
        distance[K] = 0; // 시작 값 설정을 위해 K번째 값을 최소값으로 설정 -> 최소값 비교할 때 무조건 K가 선택되도록

        for (int i = 0; i < V; i++) {// 모든 지점에 대해서 반복
            // if (point[i] == null || point[K] == null) {
            // continue;
            // }
            int min = Integer.MAX_VALUE; // 비교를 위해 가장 큰 값
            int now = 1; // 최소값을 가진 정점의 위치를 기록
            for (int j = 1; j <= V; j++) {
                if (!visited[j] && min > distance[j]) {// 최소값 설정이 되지 않고 방문하려는 최소값을 찾아감
                    // 처음 시작점 값을 0으로 설정했으니 무조건 거길 방문하고 시작함
                    min = distance[j];// 해당 정점으로의 최소 값
                    now = j; // 해당 정점의 인덱스
                }
            }

            if (visited[now] || point[now] == null)
                continue;
            visited[now] = true; // 최소경로로 최소값을 가졌으니 기록함

            for (Point data : point[now]) { // 해당 지점에서 연결된 모든 정점에 대해 반복
                if (!visited[data.to] && distance[data.to] > distance[now] + data.weight) {
                    distance[data.to] = distance[now] + data.weight;
                }
            }

        }

        return distance; // 경로를 저장한 배열 반환
    }

}