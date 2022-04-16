import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    static int N, M, K;
    static Fireball map[][];
    static Queue<Fireball> fireballsQueue;
    static int delta[][] = {{-1,0}, {-1, 1}, {0, 1}, {1,1}, {1, 0}, {1,-1}, {0, -1}, {-1, -1}}; //상 우상 우 우하 하 좌하 좌 좌상

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken()); // 격자 크기
        M = Integer.parseInt(st.nextToken()); // 파이어볼 수
        K = Integer.parseInt(st.nextToken()); //파이어볼 이동 수

        //파이어볼 정보 입력
       fireballsQueue = new LinkedList<>();

        for (int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken()); //y좌표
            int c = Integer.parseInt(st.nextToken()); //x좌표
            int m = Integer.parseInt(st.nextToken()); //질량
            int s = Integer.parseInt(st.nextToken()); //속도
            int d = Integer.parseInt(st.nextToken()); //방향
            fireballsQueue.offer(new Fireball(r, c, m , s, d));
        }

        System.out.println(simulation());

        br.close();
    }

    //K번 이동
    public static int simulation(){
        for (int i = 0; i<K; i++){
            //격자 // 격자 크기는 1부터 시작 ~ N포함 까지
            map = new Fireball[N+1][N+1];
            moveFireballs(); //파이어볼 이동
//            printmap();
            divideFireball(); //파이어볼 분열
        }
        //이동 종료후 남은 파이어볼수 반환
        return remainMass();
    }

    //파이어볼 이동
    public static void moveFireballs(){
        //현재 가지고 있는 파이어볼 수만큼 이동
        while (!fireballsQueue.isEmpty()){
            Fireball now = fireballsQueue.poll();

            //파이어볼 이동
//            System.out.print(now.r + " " + now.c + " -> ");
            now.r = movePosition(now.r, now.s * delta[now.d][0]);
            now.c = movePosition(now.c, now.s * delta[now.d][1]);
//            System.out.println(now.r + " " + now.c);

            //파이어볼 배치
            if (map[now.r][now.c] == null){
                map[now.r][now.c] = now;
            }else{
                map[now.r][now.c].cnt++; //해당 위치의 파이어볼 수 증가
                map[now.r][now.c].s += now.s; //해당 위치의 파이어볼 속도 합치기
                map[now.r][now.c].m += now.m; //해당 위치의 파이어볼 질량 증가
                if (map[now.r][now.c].dcheck && map[now.r][now.c].d %2 == now.d%2){
                    //홀짝 유지하고 있는경우
                }else {
                    //홀짝 풀린 경우
                    map[now.r][now.c].dcheck = false;
                }
            }
        }
    }

    //파이어볼 분열
    public static void divideFireball(){
        for (int i = 1; i<=N; i++){
            for (int j = 1; j<=N; j++){
                if (map[i][j] != null && map[i][j].cnt >1){ //2개 이상 파이어볼이 존재 하는 구간
                    int m = map[i][j].m / 5;
                    if (m == 0) continue; //질량이 0이면 파이어볼 소멸

                    int s = map[i][j].s / map[i][j].cnt;
                    int idx = map[i][j].dcheck? 0 : 1; //방향 통일 : 0, 아니면 1
                    for (; idx<8; idx+=2){
                        fireballsQueue.offer(new Fireball(i, j, m, s, idx));
                    }
                }else if (map[i][j] != null){
                    fireballsQueue.offer(map[i][j]);
                }
            }
        }
    }

    //남은 파이어볼 질량 계산
    public static int remainMass(){
        int sum = 0;
        while (!fireballsQueue.isEmpty()){
            Fireball now = fireballsQueue.poll();
            sum+=now.m;
        }
        return sum;
    }

   //이동 거리 계산
    public static int movePosition(int point, int idx){
        int tmp = idx % N; //(N만큼 이동 = 자기 자신)
        if (point + tmp < 1){ 
            return N + (point+tmp);
        }else if(point + tmp > N){
            return (point+tmp) - N;
        }
        return point+tmp;
    }

    public static void printmap(){
        for (int i = 1; i<=N; i++){
            for (int j = 1; j<=N; j++){
                if (map[i][j] != null){
                    System.out.print("("+map[i][j].cnt + " " + map[i][j].m + ") ");
                }else {
                    System.out.print("( 0 ) ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    //파이어볼 정보 클래스
    static class Fireball{
        int cnt; //파이어볼 분열을 위한 map의 해당 위치 파이어볼 수
        boolean dcheck; //파이어볼들의 방향이 모두 홀짝통일 = true

        int r; //y좌표
        int c; //x좌표
        int m; //질량
        int s; //속도
        int d; //방향

        public Fireball(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
            this.cnt = 1;
            dcheck = true;
        }
    }
}