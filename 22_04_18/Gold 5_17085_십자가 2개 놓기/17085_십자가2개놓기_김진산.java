import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    /**
     * 1. 2개의 # 선택
     * 2. 2개의 십자가 만들기
     *  1) 1번째 십자가 생성
     *  2) 2번째 십가사 생성 -> 가능한 만큼 크기 키우기
     *  3) 1, 2번째 십자가에서 크기 곱 구하기
     *  4) 1번 십자가 키우기 -> 1)로 이동
     * 3. 최대값 구하기
     * */
    
    static int N, M, ans;
    static char[][] map;
    static int[][] delta = {{0,1}, {0, -1}, {1,0}, {-1,0}};
    static boolean visited[][];
    static List<Point> p;
    static Point choice[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken()); //map의 세로
        M = Integer.parseInt(st.nextToken()); //map의 가로

        map = new char[N][M];
        p = new ArrayList<>(); //#의 위치를 담을 List

        for (int i = 0; i<N; i++){
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j<M; j++){
                if (map[i][j] == '#') { //#인경우 추가
                    p.add(new Point(i, j));
                }
            }
        }

        ans = 1; //출력값
        choice = new Point[2]; //0, 1 번째 선택된 것 찾기
        dfs(0, 0);

        System.out.println(ans);

        br.close();
    }

    //조합으로 2개의 위치 선정
    public static void dfs(int cnt, int start){
        if (cnt == 2){
            //2개 위치 선택
            visited = new boolean[N][M];
            expansion(); //십자가 2개 키우기
            return;
        }
        for (int i = start; i<p.size(); i++){
            choice[cnt] = p.get(i);
            dfs(cnt+1, i+1);
        }
    }

    //십자가 확장
    public static void expansion(){
        int level = 1; //현재 : 1단계 작은 크기 -> level1 에서 확장 가능하면 1단계 키우기 가능한 의미
        visited[choice[0].y][choice[0].x] = true; //1번 십자가 기준점
        visited[choice[1].y][choice[1].x] = true; //2번 십자가 기준점

        while (true){
            boolean firstS = true; //1번 십자가 확장 가능 여부
            //4방 탐색
            for (int i = 0; i<4; i++){
                int nexty = choice[0].y + (delta[i][0] * level);
                int nextx = choice[0].x + (delta[i][1] * level);
                if (0<= nexty && nexty<N && 0<=nextx && nextx<M && map[nexty][nextx] == '#' && !visited[nexty][nextx]){
                }else { //1개라도 만족하지 못하면 확장 불가능
                    firstS = false;
                    break;
                }
            }

            //방문 여부 체크
            if (firstS){ //확장이 가능한 경우
                for (int i = 0; i<4; i++){
                    int nexty = choice[0].y + (delta[i][0] * level);
                    int nextx = choice[0].x + (delta[i][1] * level);
                    visited[nexty][nextx] = true;
                }
                level++; //크기 증가
            }

            //2번째 십자가 확장
            int level2 = 1;
            while (true){ //반복
                if (firstS || level == 1){ //1번 십자가 확장 가능 혹은 크기가 1인경우(level ==1 이면 #한개 차지하고 있음)
                    boolean second = true;
                    for (int i = 0; i<4; i++){ //2번째 십자가 확장
                        int nexty = choice[1].y + (delta[i][0] * level2);
                        int nextx = choice[1].x + (delta[i][1] * level2);
                        if (0<= nexty && nexty<N && 0<=nextx && nextx<M && map[nexty][nextx] == '#' && !visited[nexty][nextx]){
                        }else {
                            //십자가를 만들지 못하는 경우
                            second = false;
                            break;
                        }
                    }
                    if (second){
                        //십자가 둘다 만드는 경우 - 계산 및 비교
                        level2++; //확장 가능하므로 크기 증가
//                        System.out.println(choice[0].y + " " + choice[0].x + " " + choice[1].y + " " + choice[1].x);
//                        System.out.println(level + " , " + level2);
                        ans = Math.max(ans, cal(level)*cal(level2)); //크기 곱
                    }else if(level2==1){ //둘 다 레벨이 1인경우 = 둘다 크기가 # 1개

                        ans = Math.max(ans, cal(level)*cal(level2));
                        break;
                    }else {
                        break;
                    }
                }else{
                    return;
                }
            }
            if (!firstS) return; //1번 십자가가 확장 실패하면 해당 메소드 종료
        }
    }

    //십자가 크기 계산
    public static int cal(int n){
        if (n==1) return 1;
        else return (n-1)*4 + 1;
    }

    static class Point{
        int y, x;
        public Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
}