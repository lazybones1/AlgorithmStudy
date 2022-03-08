import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Main {

    static int N;
    static List<Road> roads[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); // 집의 개수
        int M = Integer.parseInt(st.nextToken()); // 길의 개수

        //간선정보 초기화
        roads = new List[N+1];
        for (int i = 1; i<=N; i++){
            roads[i] = new ArrayList<>();
        }

        //간선정보 입력, 양방향 그래프
        for (int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            roads[A].add(new Road(B, C));
            roads[B].add(new Road(A, C));
        }

        //프림 알고리즘 사용
        bw.write(String.valueOf(prim()));

        bw.flush();
        bw.close();
        br.close();
    }

    public static int prim(){
        PriorityQueue<Road> pq = new PriorityQueue<>();
        pq.offer(new Road(1, 0));

        boolean visited[] = new boolean[N+1];
        int ans = 0; //최소비용
        int cnt = 0; //방문 정점 수
        int max = Integer.MIN_VALUE; //사용 도로중 가장 비용이 큰 것

        while (!pq.isEmpty()){
            Road now = pq.poll();
            
            if (visited[now.num]) continue;
            
            visited[now.num] = true;
            ans += now.cost; //비용 더하기
            
            max = Math.max(max, now.cost); //사용도로 비용 큰 값 구하기

            //다음 정점 선정
            for (Road next : roads[now.num]) {
                if (!visited[next.num])
                    pq.offer(next);
            }
            //모든 정점 방문시 종료
            if (++cnt == N){
                break;
            }
        }
        //2개의 마을로 분할
        //비용이 가장 큰 도로 제거
        return ans - max;
    }

    static class Road implements Comparable<Road>{
        int num;
        int cost;
        public Road(int num, int cost){
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(Road o) {
            return cost - o.cost;
        }
    }
}