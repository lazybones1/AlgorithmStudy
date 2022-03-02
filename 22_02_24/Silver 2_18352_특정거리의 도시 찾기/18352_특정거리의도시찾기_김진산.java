import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Main{
    static int N, M, K, X, distance[];
    static List<Integer> list[];
    static boolean visited[];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); //도시의 개수
        M = Integer.parseInt(st.nextToken()); //도로의 개수
        K = Integer.parseInt(st.nextToken()); //거리 정보
        X = Integer.parseInt(st.nextToken()); //출발 도시의 번호
        distance = new int[N+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        visited = new boolean[N+1];

        list = new ArrayList[N+1]; //시작 점점 : 1
        for (int i = 0; i<N+1; i++){ //초기화
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()); //출발 도시
            int B = Integer.parseInt(st.nextToken()); //도착 도시
            list[A].add(B); //간선 정보 저장
        }

        //bfs로 최단 거리 탐색
        bw.write(bfs());

        bw.flush();
        bw.close();
        br.close();
    }

    public static String bfs(){
        StringBuilder sb = new StringBuilder(); //결과 출력
        Queue<Integer> queue = new LinkedList<>();
        int level = 0; //거리 확인용

        visited[X] = true;
        queue.offer(X); //시작 정점

        while (!queue.isEmpty()){
            int size = queue.size();

            for (int i = 0; i< size; i++){
                int num = queue.poll();

                int listSize = list[num].size();
                for (int j = 0; j<listSize; j++){ //간선으로 연결된 정점 큐에 추가
                    if (visited[list[num].get(j)]) continue;
                    visited[list[num].get(j)] = true;
                    queue.offer(list[num].get(j));
                }
            }

            if (++level == K){ //거리가 K인 친구들
                if (queue.isEmpty()){ //없는 경우 -1
                    return "-1";
                }

                //최단거리 K를 가지는 정점들 저장 배열
                int[] arr = new int[queue.size()];
                int idx = 0;
                while (!queue.isEmpty()){
                    arr[idx++] = queue.poll();
                }
                Arrays.sort(arr); //정렬
                for (int i = 0; i<idx; i++){
                    sb.append(arr[i]+"\n");
                }
                return sb.toString();
            }
        }
        return "-1"; //K까지 못가는 경우 -1 출력력
    }

    //시간초과 발생
//    public static void dfs(int cnt, int before){
//       if (cnt == 0){ //0이면 시작 정점
//            distance[before] = 0;
//        }else if (cnt <= K){  //최단 거리 구하기
//            distance[before] = Math.min(distance[before], cnt);
//        }else if(cnt>K){// K이상 넘어가면 구할 필요 없음
//            return;
//        }
//
//        int size = list[before].size();
//        for (int i = 0; i<size; i++){
//            if (visited[list[before].get(i)]) continue;
//            visited[list[before].get(i)] = true;
//            dfs(cnt +1, list[before].get(i));
//            visited[list[before].get(i)] = false;
//        }
//    }

}