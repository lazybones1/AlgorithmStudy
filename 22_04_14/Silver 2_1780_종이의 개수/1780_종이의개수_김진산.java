import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    static int N, map[][], ans[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());//격자의 크기 N*N

        map = new int[N][N]; //격자
        //격자 정보 입력
        for (int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //각 영역의 수
        ans = new int[3]; //[0]: 0, [1]= 1, [2] = -1

        cut(0, 0, N, N);
        System.out.println(ans[2]); //-1인 영역수
        System.out.println(ans[0]); //0인 영역수
        System.out.println(ans[1]); //1인 영역수

        br.close();
    }

    //영역 분리
    public static void cut(int sy, int sx, int ey, int ex){
//        print(sy, sx, ey, ex);
        if (!check(sy, sx, ey, ex)){ //영역이 만들어 지지 않은 경우
            int yIdx = (ey-sy)/3;
            int xIdx = (ex-sx)/3;

            //9등분
            for (int i = 0; i<3; i++){
                for (int j = 0; j<3; j++){
                    int y = sy + yIdx*i; //시작 좌표
                    int x = sx + xIdx*j; //종료 좌표
//                    System.out.println("(" + sy + " " +sx + ") | (" + ey + ", " +ex + ")");
//                    System.out.println(i + " "+ j + " | " + yIdx + " " + xIdx);
//                    System.out.println(y + " " + x + "  " + (y + yIdx) + " " + (x + xIdx));
//                    System.out.println("---------------------------------");
                    cut(y, x, y + yIdx, x+xIdx);
                }
            }
        }
    }

    //영역 확인 및 체크
    public static boolean check(int sy, int sx, int ey, int ex){
        int flag = map[sy][sx];
        for (int i= sy; i<ey; i++){
            for (int j = sx; j<ex; j++){
                if (map[i][j] != flag) return false;
            }
        }
        int idx = flag>=0? flag : 2;
        ans[idx]++; //해당 숫자 영역 수 증가
        return true;
    }

    //테스트용
    public static void print(int sy, int sx, int ey, int ex){
        for (int i= sy; i<ey; i++){
            for (int j = sx; j<ex; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}