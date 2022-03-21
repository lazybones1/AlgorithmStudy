import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


class Main{

    static int N, M, D, map[][], position[], ans;
    static int delta[][] = {{-1,0},{0, 1},{0, -1}};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); //행 수
        M = Integer.parseInt(st.nextToken()); //열 수
        D = Integer.parseInt(st.nextToken()); //공격거리

        position = new int[3]; //궁수 배치
        ans = 0; // 출력값

        //게임판
        map = new int[N][M];
        for (int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //조합 사용 -> 궁수 위치 완전탐색
        choicePosition(0, 0);

        System.out.println(ans);
        br.close();
    }

    //궁수 위치를 조합으로 설정
    public static void choicePosition(int cnt, int start){
        if (cnt == 3){
//            System.out.println(Arrays.toString(position));
            game();
            return;
        }
        for (int i = start; i<M; i++){
            position[cnt] = i;
            choicePosition(cnt+1, start+1);
        }
    }

    public static void game(){
        int sum = 0; //해당 게임에서 죽인 적의 수
        int copyMap[][] = cloneMap();
        for (int i = 0; i<N; i++){ //최대 턴수: N턴
//            printTest(copyMap);
            sum += attack(copyMap);
            moveEnemy(i, copyMap);
        }
//        System.out.println("------ " + sum);
        ans = Math.max(ans, sum);
    }

    //적 이동
    public static void moveEnemy(int turn, int[][] map){
        //turn : 진행 턴 수
        for (int i = N-1; i>=turn; i--){
            for (int j = 0; j<M; j++){
                //적 이동
                if (map[i][j] == 1){
                    map[i][j] = 0;
                    if (i+1<N){
                        map[i+1][j] = 1;
                    }
                }
            }
        }
    }

    //적 공격
    public static int attack(int[][] map){
        //궁수 공격, 죽인 적 수 반환
        Pos enemy[] = new Pos[3];
        int attact = 0;

        for (int i = 0; i<3; i++){
            enemy[i] = searchEnemy(position[i], map);
        }

        for (int i = 0; i<3; i++){
            //공격할수 있는 적이 있는 경우
           if (enemy[i] != null && map[enemy[i].y][enemy[i].x] == 1){
               map[enemy[i].y][enemy[i].x] = 0;
               attact++;
           }
        }
        return attact;
    }

    //bfs를 사용하여 공격 대상 선정 (이 함수에서 적 공격시 중복공격 허용이 안됨)
    public static Pos searchEnemy(int attacker, int[][] map){
        Queue<Pos> queue = new LinkedList<>();
        boolean visited[][] = new boolean[N][M];
        queue.offer(new Pos(N, attacker));
        Pos ememy = new Pos(Integer.MAX_VALUE, Integer.MAX_VALUE); // 적의 좌표
        int level = 0;

        while (!queue.isEmpty()){
            int size = queue.size();
            boolean find = false;

            for (int i = 0; i<size; i++){
                Pos now = queue.poll();

                //초기값에 궁수 위치가 N이므로 제어
                if (now.y != N && map[now.y][now.x]==1){
                    if (ememy.x > now.x){ //가장 왼쪽 적 찾기
                        ememy.x = now.x;
                        ememy.y = now.y;
                    }
                    //가까운 적이 선택된 경우 더 먼 적 확인 안함
                    find = true;
                    continue;
                }

                for (int j = 0; j<3; j++){
                    int nexty = now.y + delta[j][0];
                    int nextx = now.x + delta[j][1];
                    if (0<= nexty && nexty <N && 0<=nextx && nextx<M && !visited[nexty][nextx]){
                        visited[nexty][nextx] = true;
                        queue.offer(new Pos(nexty, nextx));
                    }
                }
            }
            if (find) return ememy;
            if (++level > D) break; //공격 거리 밖인 경우
        }
        return null;
    }

    //map 복사
    public static int[][] cloneMap(){
        int[][] tmpMap = new int[N][M];
        for (int i = 0; i<N; i++){
            for (int j = 0; j<M; j++){
                tmpMap[i][j] = map[i][j];
            }
        }
        return tmpMap;
    }

    //디버깅 테스트용
    static void printTest(int[][] map){
        for (int i = 0; i<N; i++){
            for (int j = 0; j<M; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    //죄표 표현 클래스
    static class Pos{
        int x, y;
        public Pos(int y, int x){
            this.x = x;
            this.y = y;
        }
    }
}