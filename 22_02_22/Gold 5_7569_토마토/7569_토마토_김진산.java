import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int M, N, H, box[][][];
    //이동 : 상 하 좌 우 앞 뒤
    static int[] deltah = {1, -1, 0, 0, 0, 0};
    static int[] deltay = {0, 0, 0, 0, -1, 1};
    static int[] deltax = {0, 0, -1, 1, 0, 0};

    static Queue<Position> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        /*
        int M : 상자 가로 칸 수
        int N : 상자 세로 칸 수
        int H : 상자 높이 칸 수
        int[][][] box : 박스
         */
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        box = new int[H][N][M];
        queue = new LinkedList<>();
        int level = -1, n1 = 0, n2 = 0;

        //값 입력력
        for (int h = 0; h < H; h++) {
            for (int y = 0; y < N; y++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int x = 0; x < M; x++) {
                    box[h][y][x] = Integer.parseInt(st.nextToken());
                    if (box[h][y][x] == 1) {
                        queue.offer(new Position(h, y, x));
                        n1++;
                    }
                }
            }
        }

        while (!queue.isEmpty()) {
            Position tmp = queue.poll();

            for (int i = 0; i < 6; i++) {
                int nexth = tmp.h + deltah[i];
                int nexty = tmp.y + deltay[i];
                int nextx = tmp.x + deltax[i];
                if (0 <= nexth && nexth < H && 0 <= nexty && nexty < N && 0 <= nextx && nextx < M && box[nexth][nexty][nextx] == 0) {
                    box[nexth][nexty][nextx] = 1;
                    queue.offer(new Position(nexth, nexty, nextx));
                    n2++;
                }
            }

            if (--n1 <= 0) {
                n1 = n2;
                n2 = 0;
                level++;
            }
        }

        if (isfull()){
            System.out.println(level);
        }else{
            System.out.println("-1");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static boolean isfull(){
        for (int h = 0; h < H; h++) {
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < M; x++) {
                    if (box[h][y][x] == 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    static void draw(){
        for (int h = 0; h < H; h++) {
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < M; x++) {
                    System.out.print(box[h][y][x]);
                }
                System.out.println();
            }
            System.out.println("---");
        }
        System.out.println();
    }


    static class Position {
        int h;
        int y;
        int x;

        public Position(int h, int y, int x) {
            this.h = h;
            this.y = y;
            this.x = x;
        }
    }
}