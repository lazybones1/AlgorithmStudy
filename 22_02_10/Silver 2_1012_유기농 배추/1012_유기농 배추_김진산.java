import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Main{
    static int M,N,K,ans, acre[][];
    static boolean visited[][];
    //하 우 상 좌 순
    static int[][] move = {{1,0},{0,1}, {-1,0}, {0,-1}};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int tc = Integer.parseInt(st.nextToken());

        for(int t = 1; t<=tc; t++){
            st = new StringTokenizer(br.readLine(), " ");
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            ans = 0;
            acre = new int[N][M];
            visited = new boolean[N][M];
            for(int i = 0; i<K; i++){
                st = new StringTokenizer(br.readLine(), " ");
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                acre[y][x] = 1;
            }


            for (int y = 0; y<N; y++){
                for (int x = 0; x<M; x++){
                    if(visited[y][x]) continue;
                    if(acre[y][x] == 1) {
                        ans++;
                        solve(y, x);
                    }
                }
            }
            bw.write(String.valueOf(ans)+"\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
    public static void solve(int y, int x){
        visited[y][x] = true;
        for (int i = 0; i<4; i++){
            int nextY = y + move[i][0];
            int nextX = x + move[i][1];
            if(0<= nextY && nextY <N && 0<= nextX && nextX<M){
                if (!visited[nextY][nextX] && acre[nextY][nextX] == 1){
                    solve(nextY, nextX);
                }
            }
        }
    }
}