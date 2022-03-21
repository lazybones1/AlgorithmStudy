import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


class Main{

    static int N, ans, map[][];
    static int[][] delta = {{1,0}, {-1,0}, {0,-1}, {0,1}};
    static boolean[][] visited;
//    static Pos pos[]; //테스트용

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); //화단 크기
        map = new int[N][N];
        ans = Integer.MAX_VALUE;
        visited = new boolean[N][N];
//        pos = new Pos[3]; //테스트용

        for (int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0,0);

        System.out.println(ans);
        br.close();
    }

    //씨앗 자리 선정
    static void dfs(int cnt, int sum){
        if (cnt == 3){
            ans = Math.min(ans, sum); //최소비용 계산
            return;
        }
        
        //전체 탐색
        for (int i = 1; i<N-1; i++){
            for (int j = 1; j<N-1; j++){

                if(visited[i][j] || check(i,j)) continue; //필요한 5칸이 모두 방문 했던 경우
                
                visited[i][j] = true; //방문표시
                int cost = blossom(i, j); //방문표시, 해당 장소에서의 비용 

//                pos[cnt] = new Pos(i, j); //테스트용 좌표값 확인
                
                dfs(cnt+1, sum+cost);

                clearBlossom(i, j); //방문 해제
                visited[i][j] = false; //방문 해제
            }
        }
    }

    //개화
    static int blossom(int y, int x){
        int cost = map[y][x];
        for (int i = 0; i<4; i++){
            int nexty = y + delta[i][0];
            int nextx = x + delta[i][1];
            
            cost += map[nexty][nextx];
            visited[nexty][nextx] = true;
        }
        return cost;
    }

    //방문 해제
    static void clearBlossom(int y, int x){
        for (int i = 0; i<4; i++){
            int nexty = y + delta[i][0];
            int nextx = x + delta[i][1];

            visited[nexty][nextx] = false;
        }
    }

    //개화 가능 여부
    static boolean check(int y, int x){
        for (int i = 0; i<4; i++){
            int nexty = y + delta[i][0];
            int nextx = x + delta[i][1];
            if(visited[nexty][nextx]) { //개화 불가능
                return true;
            }
        }
        return false;
    }

    //테스트용 
//    static class Pos{
//        int y, x;
//        public Pos(int y, int x){
//            this.y = y;
//            this.x = x;
//        }
//
//        @Override
//        public String toString() {
//            return "Pos{" +
//                    "y=" + y +
//                    ", x=" + x +
//                    '}';
//        }
//    }
}