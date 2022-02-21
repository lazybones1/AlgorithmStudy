import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    static int N, L, R, A[][], sum;
    static int[][] move = {{0,1}, {1,0}, {0,-1}, {-1,0}};
    static boolean[][] visited;
    static List<Position> unions;
    static int ans;
    static boolean cycle;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        /*
        N : 땅 크기
        L : 최소 인구차이
        R : 최대 인구차이
        A[][] : 각 땅의 인구
        move[][] : 우, 하, 좌, 상 순서
        visited[][] : 방문 여부 확인
        unions : 연합이 된 땅들 좌표 리스트
        sum : 연합 인구수
        ans : 사이클 수
        cycle : 사이클 가능 여부
         */
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        visited = new boolean[N][N];
        ans = 0;

        for(int y = 0; y<N; y++){
            st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x<N; x++){
                A[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        do{
            visited = new boolean[N][N];
            cycle = false;
            for(int y = 0; y<N; y++){
                for (int x = 0; x<N; x++){
                    if(visited[y][x]) continue;
                    unions = new ArrayList<>();
                    sum = 0;
                    sum = checkOpen(y,  x, 0);
                    if (unions.size() > 1){
                        migrate();
                        cycle = true;
                    }
                }
            }
            if(cycle == true) ans++;
        }while (cycle);

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
    public static int checkOpen(int y, int x, int people){
        visited[y][x] = true;
        //국경 열지 확인
        int peoplenum = people;
        for (int i = 0; i<4; i++){
            int nexty = y + move[i][0];
            int nextx = x + move[i][1];
            if(0<= nexty && nexty<N && 0<= nextx && nextx<N){
                if (visited[nexty][nextx]) continue;
                //이동
                int sub = Math.abs(A[y][x] - A[nexty][nextx]);
                //조건 확인
                if(L<= sub && sub <= R){
                    peoplenum += checkOpen(nexty, nextx, people);
                }
            }
        }
        unions.add(new Position(y,x));
        return peoplenum + A[y][x];
    }
    public static void migrate(){
        //국민 이주
        int contrys = unions.size();
        int people = sum/contrys;
        for (int i = 0; i<contrys; i++){
            A[unions.get(i).y][unions.get(i).x] = people;
        }
    }

    static class Position{
        int x;
        int y;
        public Position(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
}