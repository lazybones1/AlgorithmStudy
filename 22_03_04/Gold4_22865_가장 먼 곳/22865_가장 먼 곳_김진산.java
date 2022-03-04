import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Main {

    static int N, A,B,C;
    static List<Road> roads[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N  = Integer.parseInt(st.nextToken()); // 땅 후보 개수
        int distance[][] = new int[3][N+1];

        //친구집 까지의 거리
        st = new StringTokenizer(br.readLine(), " ");
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        roads = new List[N+1];
        for (int i = 1; i<=N; i++){
            roads[i] = new ArrayList<>();
        }

        int M = Integer.parseInt(br.readLine()); //도로의 수

        //도로 정보
        for (int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int D = Integer.parseInt(st.nextToken()); //땅
            int E = Integer.parseInt(st.nextToken()); //땅
            int L = Integer.parseInt(st.nextToken()); //땅 사이 거리
            roads[D].add(new Road(E, L)); //양방향 그래프
            roads[E].add(new Road(D, L));
        }

        distance[0] = dijkstra(A); //A에서의 거리값
        distance[1] = dijkstra(B); //B에서의 거리값
        distance[2] = dijkstra(C); //C에서의 거리값

        int max = Integer.MIN_VALUE;
        int ans = 0;

        for (int i = 1; i<=N; i++){
            int min = Integer.MAX_VALUE; //ABC와의 가장 가까운 거리 찾기
            min = Math.min(min, distance[0][i]);
            min = Math.min(min, distance[1][i]);
            min = Math.min(min, distance[2][i]);
            if (min > max){ //가장 가까운 거리가 큰것 찾기
                max = min;
                ans = i;
            }
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }

    public static int[] dijkstra(int start){
        PriorityQueue<Road> pq = new PriorityQueue<>();
        boolean visited[] = new boolean[N+1];
        int distance[] = new int[N+1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        distance[start] = 0;
        pq.offer(new Road(start, 0));

        while (!pq.isEmpty()){
            Road now = pq.poll();

            if (visited[now.to]) continue;
            visited[now.to] = true;

            for (Road next : roads[now.to]){
                if (!visited[next.to] && distance[next.to] > distance[now.to] + next.leng){
                    distance[next.to] = distance[now.to] + next.leng;
                    pq.offer(new Road(next.to, distance[next.to]));
                }
            }
        }

        return distance;
    }

    static class Road implements Comparable<Road>{
        int to;
        int leng;
        public Road(int to, int leng){
            this.to = to;
            this.leng = leng;
        }

        @Override
        public int compareTo(Road o) {
            return leng -o.leng;
        }
    }

}