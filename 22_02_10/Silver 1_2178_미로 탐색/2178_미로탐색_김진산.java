import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main{
    /*
    N : 배열의 세로 길이
    M : 배열 가로 길이
    map[][] : 미로
    ans : 정답 값
    move[][] : 우, 하, 상, 좌 순 이동
    visited[][] : 큐에 중복 안들어 가도록
    queue : bfs를 위한 큐
     */
    static int N, M, map[][], ans;
    static int[][] move = {{0, 1}, {-1,0}, {1,0}, {0, -1}}; //우 하 상 좌
    static boolean[][] visited;
    static Queue<Pos> queue;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        //변수 초기화 부
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];
        visited = new boolean[N+1][M+1];
        queue = new LinkedList<>();
        ans = 0;

        //map[][]에 값 입력부
        for (int y = 1; y<=N; y++){
            String str = br.readLine();
            for (int x = 1; x<=M; x++){
                map[y][x] = (int)(str.charAt(x-1) - '0');
            }
        }

        //루르에서 실행 / 루트 : (1,1)
        visited[1][1] = true;
        solve(1,1, 1);

        //BFS
        while (!queue.isEmpty()){
            Pos pos = queue.poll();
            boolean end = solve(pos.y, pos.x, pos.cnt);
            if(end){
                //도착시 최소 거리
                ans = pos.cnt;
            }
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
    public static boolean solve(int y, int x, int cnt){
        if (y == N && x == M){
            //최단거리 종료 조건
            return true;
        }
        for (int i = 0; i<4; i++){
            int nexty = y + move[i][0];
            int nextx = x + move[i][1];
            if(1<=nexty && nexty <N+1 && 1<=nextx && nextx < M+1 && map[nexty][nextx] ==1 && !visited[nexty][nextx]){
                //큐에 값 입력시 방문 -> 중복값 방지
                visited[nexty][nextx] = true;
                queue.offer(new Pos(nexty, nextx, cnt+1));
            }
        }
        return false;
    }

    static class Pos{
        int x;
        int y;
        int cnt;
        public Pos(int y, int x, int cnt){
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }
}