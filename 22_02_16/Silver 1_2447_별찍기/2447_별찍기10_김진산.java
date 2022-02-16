import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Main{
    static char[][] map; // 별찍을 배열
    static StringBuilder sb; //출력을 위한 sb

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken()); //크기
        map = new char[n+1][n+1];
        sb = new StringBuilder();

        initMap(n); //맵 초기화
        solve(n, 0,0); //별찍기
        draw(n); //출력

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static void initMap(int n){
        //map ' '로 채우기
        for (int i = 0; i<=n; i++){
            for (int j = 0; j<=n; j++){
                map[i][j] = ' ';
            }
        }
    }

    static void solve(int n, int y, int x){
        if (n==1){ //1일때만 별찍기
            map[y][x] = '*';
            return;
        }

        int div = n/3; //3등분
        for (int i = 0; i<3; i++){ //9개 칸 돌기
            for (int j = 0; j<3; j++){ 
                if (i==1 && j==1) continue; //중앙은 비우기
                solve(div, y + (div * i), x +(div*j)); //나머지는 별찍기 (if n=9 -> 0, 3, 6 +ㅁ)
            }
        }
    }

    static void draw(int n){ //결과 출력
        for (int i = 0; i<n; i++){
            for (int j = 0; j<n; j++){
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
    }
}