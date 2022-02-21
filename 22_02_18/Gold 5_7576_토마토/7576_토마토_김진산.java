import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Main {
    /*
    N: 상자 세로
    M: 상자 가로
    box[][] : 토마토 상자
    move : 이동
    queue : BNF 큐
     */
    static int N, M, box[][];
    static int[][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};//상 하 좌 우
    static boolean[][] visited;
    static Queue<Position> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        box = new int[N][M];
        visited = new boolean[N][M];
        queue = new LinkedList<>();

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < M; x++) {
                box[y][x] = Integer.parseInt(st.nextToken());
                if (box[y][x] == 1) {
                    queue.add(new Position(y, x, 0));
                }
            }
        }

        //모두 익을 수 있는 경우
        int idx = 0;
        boolean isEnd = true;
        if (queue.isEmpty()){
            //익은 토마토가 존재하지 않는 경우
            bw.write(String.valueOf(-1));
        } else{
            while (!queue.isEmpty()) {
                boolean suc = false;
                Position tmp = queue.poll();
                suc = addQueue(tmp.y, tmp.x, tmp.cnt);
                if (!isEnd) {
                    isEnd = suc;
                }
                if (queue.isEmpty()) {
                    //모두 탐색하여 큐가 빈 경우
                    if(isDoit()){
                        bw.write(String.valueOf(tmp.cnt));
                    }else{
                        bw.write(String.valueOf(-1));
                    }
                    break;
                } else if (idx != tmp.cnt) {
                    if (!isEnd) {
                        //탐색할 영역이 더이상 없음
                        if(isDoit()){
                            bw.write(String.valueOf(tmp.cnt));
                        }else{
                            bw.write(String.valueOf(-1));
                        }
                        break;
                    } else {
                        idx = tmp.cnt;
                        isEnd = false;
                    }
                }
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static boolean isDoit() {
        //안익은 토마토 인접에 토마토가 없는 경우 판단
        boolean doit = true;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if(box[y][x] == 0)
                    return false;
            }
        }
        return doit;
    }

    static boolean addQueue(int y, int x, int cnt) {
        box[y][x] = 1;
        boolean success = false;
        for (int i = 0; i < 4; i++) {
            int nexty = y + move[i][0];
            int nextx = x + move[i][1];
            if (0 <= nexty && nexty < N && 0 <= nextx && nextx < M && !visited[nexty][nextx] && box[nexty][nextx] == 0) {
                visited[nexty][nextx] = true;
                queue.offer(new Position(nexty, nextx, cnt + 1));
                success = true;
            }
        }
        return success;
    }

    static void print(int cnt) {
        System.out.println(cnt);
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                System.out.print(box[y][x] + " ");
            }
            System.out.println();
        }
    }


    static class Position {
        int y;
        int x;
        int cnt;

        public Position(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }
}