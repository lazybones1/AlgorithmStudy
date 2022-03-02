import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main{
		//나이트의 이동 8가지방향
    static int delta[][] = {{-1, -2}, {-2, -1}, {-1, 2}, {-2, 1}, {1, -2}, {2, -1}, {1, 2}, {2, 1}}; //좌상1, 좌상2, 우상1, 우상2, 좌하1, 좌하2, 우하1, 우하2

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int tc = Integer.parseInt(st.nextToken());
        for (int t = 1; t<=tc; t++){
						//테스트 케이스 시작

            int l = Integer.parseInt(br.readLine());

						//초기 나이트 위치
            st = new StringTokenizer(br.readLine(), " ");
            int knightx = Integer.parseInt(st.nextToken());
            int knighty = Integer.parseInt(st.nextToken());
						//목적지 위치
            st = new StringTokenizer(br.readLine(), " ");
            int wantx = Integer.parseInt(st.nextToken());
            int wanty = Integer.parseInt(st.nextToken());

            int num = bfs(l, knighty, knightx, wanty, wantx);

            bw.write(num + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static int bfs(int l, int sy, int sx, int ey, int ex){
        //bfs로 최단거리 탐색
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[l][l]; //방문 표시
        int level = 0; //몇번째에 되었는지 표시

        visited[sy][sx] = true;
        queue.offer(new Point(sy, sx));

        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i<size; i++){
                Point tmp = queue.poll();

                if (tmp.x == ex && tmp.y == ey){
                    return level;
                }
								//8가지 방향으로 이동
                for (int j = 0; j<8; j++){
                    int nexty = tmp.y + delta[j][0];
                    int nextx = tmp.x + delta[j][1];

                    //큐 삽입 조건
                    if (0<= nexty && nexty< l && 0 <= nextx && nextx <l && !visited[nexty][nextx]){
                        visited[nexty][nextx] = true;
                        queue.offer(new Point(nexty, nextx));
                    }
                }
            }
            level++;
        }
        return -1;
    }

    static class Point{
        int x;
        int y;
        public Point(int y, int x){
            this.x = x;
            this.y = y;
        }
    }
}