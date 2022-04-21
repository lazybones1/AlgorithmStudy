import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*BOJ_14503_로봇 청소기*/
class Main {

    static int N, M, map[][], robotY, robotX, robotD, clean;
    static int delta[][] = {{-1,0}, {0,1}, {1,0}, {0, -1}};//상 우 하 좌

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        st = new StringTokenizer(br.readLine(), " ");
        robotY = Integer.parseInt(st.nextToken());
        robotX = Integer.parseInt(st.nextToken());
        robotD = Integer.parseInt(st.nextToken());

        for (int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        clean = 0;
        solution();

        System.out.println(clean);
        br.close();
    }

    public static void solution(){ //y좌표, x좌표, 방향
        while (true){
            if (map[robotY][robotX] == 0)  clean++;
            map[robotY][robotX] = -1; //현재 위치 청소

//            printtest(); //디버깅용 출력 합수

            int cnt = 0;
            for (int i = 0; i<4; i++){
                robotD = indexing(robotD, 1); //왼쪽 바라보기

                int nexty = robotY + delta[robotD][0];
                int nextx = robotX + delta[robotD][1];
                if (0<=nexty && nexty<N && 0<=nextx && nextx<M && map[nexty][nextx] == 0){ //다음 구역 갈수 있는 경우
                    //로봇 위치 변경
                    robotY = nexty;
                    robotX = nextx;
                    break;
                }else {
                    cnt++; //돌아가는 횟수 체크용
                }
            }

            if (cnt == 4){ //4방향 청소 하지 않은 빈 공간이 존재하지 않음
                //후진
                int nexty = robotY + delta[indexing(robotD, 2)][0];
                int nextx = robotX + delta[indexing(robotD, 2)][1];
                if (0<=nexty && nexty<N && 0<=nextx && nextx<M && map[nexty][nextx] != 1){ //뒤가 벽이 아닌 경우
                    robotY = nexty;
                    robotX = nextx;
                }else{ //이동 방향이 없는 경우 종료
                    return;
                }
            }
        }
    }

    //현재 방향의 왼쪽 방향 찾기
    public static int indexing(int direction, int cnt){
        direction = direction - cnt;
        if (direction < 0){
            return 4 + direction;
        }
        return direction;
    }


    public static void printtest(){
        for (int i = 0; i<N; i++){
            for (int j = 0; j<M; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}