import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Main {

    static int N, X;
    static List<Road> roads[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); //마을 구분
        int M = Integer.parseInt(st.nextToken()); //도로 개수
        X = Integer.parseInt(st.nextToken()); //집합장소

        //도로 정보 초기화
        roads = new List[N+1];
        for (int i = 1; i<=N; i++){
            roads[i] = new ArrayList<>();
        }

        //도로 정보 입력
        for (int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            roads[from].add(new Road(to, time)); //단방향 도로
        }

        //출발지에서 X까지 거리 모음
        int distance[] = new int[N+1]; //각 인덱스에서 X까지의 거리를 저장
        for (int i = 1; i<=N; i++){
            distance[i] = dijkstra(i)[X]; //i에서 출발하여 X까지의 거리 찾기
        }
        //X에서 각 출발지 까지 거리 모음
        int back[] = dijkstra(X);
        
        //출발지 -> X -> 출발지 거리 최대값 찾기
        int max = Integer.MIN_VALUE;
        for (int i = 1; i<=N; i++){
            max = Math.max(max, distance[i] + back[i]);
        }

        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
        br.close();
    }

    public static int[] dijkstra(int start){
        PriorityQueue<Road> pq = new PriorityQueue<>();

        boolean visited[] = new boolean[N+1];
        int distance[] = new int[N+1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        pq.offer(new Road(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()){
            Road now = pq.poll();

            if (visited[now.to]) continue;
            //X에서 출발 하여 목적지를 찾는경우는 모든 정점을 거쳐야함
            //출발지에서 X로 가는 경우 X에 도착하면 이후 과정은 진행 안함
            if (start!=X && now.to == X){
                return distance;
            }
            visited[now.to] = true;

            for (Road next : roads[now.to]){
                if (!visited[next.to] && distance[next.to] > distance[now.to] + next.time){
                    distance[next.to] = distance[now.to] + next.time;
                    pq.offer(new Road(next.to, distance[next.to]));
                }
            }
        }
        return distance;
    }

    static class Road implements Comparable<Road>{
        int to;
        int time;
        public Road(int to, int time){
            this.to = to;
            this.time = time;
        }

        @Override
        public int compareTo(Road o) {
            return time - o.time;
        }
    }
}