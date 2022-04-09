import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main{

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken()); //마을 수
        int E = Integer.parseInt(st.nextToken()); //도로 수

        //간선정보 배열
        int map[][] = new int[V+1][V+1];
        for (int []m : map){
            Arrays.fill(m, Integer.MAX_VALUE);
        }

        //간선 정보 입력
        for (int i = 0; i<E; i++){ //단방향
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); //출발지
            int b = Integer.parseInt(st.nextToken()); //도착지
            int c = Integer.parseInt(st.nextToken()); //거리
            map[a][b] = c;
        }

        //플로이드 와샬
        for (int k = 1; k<=V; k++){ //경유지
            for (int i = 1; i<=V; i++){ //시작점
                if (i==k) continue;
                for (int j = 1; j<=V; j++){ //종료점
                    if (j==k) continue;
                    if (map[i][k] != Integer.MAX_VALUE && map[k][j] != Integer.MAX_VALUE){ // i -> K -> j가 가능한지 판단
//                        System.out.println(i + " " + k + " " + j);
                        map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                    }
                }
            }
        }

//        for (int i = 1; i<=V; i++){
//            for (int j = 1; j<=V; j++){
//                System.out.print(map[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();

        //사이클 = 자기 자신으로 돌아오는 것 있는지 확인 -> 대각선 확인
        int min = Integer.MAX_VALUE;
        for (int i = 1; i<=V; i++){
            if (map[i][i] != Integer.MAX_VALUE){
                min = Math.min(min, map[i][i]);
            }
        }

        if (min == Integer.MAX_VALUE){
            System.out.println(-1);
        }
        else{
            System.out.println(min);
        }
        br.close();
    }

    static class Road{
        int to;
        int cost;
        public Road(int to, int cost){
            this.to = to;
            this.cost = cost;
        }
    }
}