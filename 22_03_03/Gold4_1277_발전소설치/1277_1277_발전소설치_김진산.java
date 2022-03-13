import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Main {

    static int N, W;
    static double M;
    static List<Edge> edges[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); //발전소 수
        W = Integer.parseInt(st.nextToken()); //전선 수
        M = Double.parseDouble(br.readLine()); //제한 길이

        //간선 정보 초기화
        edges = new List[N+1];
        for (int i = 1; i<=N; i++){
            edges[i] = new ArrayList<>();
        }

        //발전소 위치 정보 입력
        Powerhouse powerhouse[] = new Powerhouse[N+1];
        for (int i = 1; i<=N; i++){ //발전소 위치
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            powerhouse[i] = new Powerhouse(x, y);
        }

        //현재 남아 있는 전선이 잇고 있는 두 발전소
        for (int i = 0; i<W; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());
            edges[p1].add(new Edge(p2, 0));
            edges[p2].add(new Edge(p1, 0));
        }


        //간선정보입력 - 두 발전소 사이 거리 계산
        for (int i = 1; i<N; i++){
            for (int j = i+1; j<=N; j++){
                double leng = calLeng(powerhouse[i], powerhouse[j]);
                if (Double.compare(leng, M) <= 0){ //제한 길이 보다 작은 경우
                    edges[i].add(new Edge(j, leng));
                    edges[j].add(new Edge(i, leng));
                }
            }
        }

        System.out.println(dijkstra()); //출력

        bw.flush();
        bw.close();
        br.close();
    }

    public static int dijkstra(){
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        boolean visited[] = new boolean[N+1];
        double distance[] = new double[N+1];
        Arrays.fill(distance, Double.MAX_VALUE);

        distance[1] = 0;
        pq.offer(new Edge(1, 0));

        while (!pq.isEmpty()){
            Edge now = pq.poll();

            if (visited[now.to]) continue;
            visited[now.to] = true;
            if (now.to == N){ //목적지인 경우 종료
                break;
            }

            for (Edge next : edges[now.to]){
                if (!visited[next.to] && distance[next.to] > distance[now.to] + next.leng){
                    distance[next.to] = distance[now.to] + next.leng;
                    pq.offer(new Edge(next.to, distance[next.to]));
                }
            }
        }
        return (int)(distance[N]*1000); //결과값 반환
    }

    public static double calLeng(Powerhouse p1, Powerhouse p2){
        //발전소 사이 거리 구하기
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    static class Powerhouse{
        //발전소 정보
        int x;
        int y;
        public Powerhouse(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge>{
        //발전소 사이 정보
        int to; //목적지
        double leng; //거리
        public Edge(int to, double leng){
            this.to = to;
            this.leng = leng;
        }

        @Override
        public int compareTo(Edge o) { //거리 오름차순
            return Double.compare(leng, o.leng);
        }
    }
}