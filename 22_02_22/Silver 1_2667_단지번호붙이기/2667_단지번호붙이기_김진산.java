import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Main{
    static int n;
    static int[][] move = {{-1,0}, {1,0}, {0,-1}, {0,1}}; //상 하 좌 우
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        char[][] map = new char[n][n];
        List<Integer> list = new ArrayList<>();

        for (int y = 0; y<n; y++){
            map[y] = br.readLine().toCharArray();
        }

        for (int y = 0; y<n; y++){
            for (int x = 0; x<n; x++){
                if (map[y][x] == '1'){
                    list.add(solve(y, x, map));
                }
            }
        }
        Collections.sort(list);
        int cnt = list.size();
        bw.write(cnt + "\n");
        for (int i = 0; i<cnt; i++){
            bw.write(list.get(i) + "\n");
        }
        bw.write("");
        bw.flush();
        bw.close();
        br.close();
    }

    public static int solve(int y, int x, char[][] map){
        //단지 내부 개수 찾기
        Queue<Position> queue = new LinkedList<>();
        int cnt = 0; //집 수

        map[y][x] = '0';
        queue.offer(new Position(y,x));

        while (!queue.isEmpty()){
            Position tmp = queue.poll();
            cnt++;

            for (int i = 0; i<4; i++){
                int nexty = tmp.y + move[i][1];
                int nextx = tmp.x + move[i][0];
                if (0<= nextx && nextx<n && 0<= nexty && nexty<n && map[nexty][nextx] == '1'){
                    map[nexty][nextx] = '0';
                    queue.offer(new Position(nexty, nextx));
                }
            }
        }

        return cnt;
    }
    static class Position{
        int y;
        int x;
        public Position(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
}