import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
    static int n, map[][];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken()); // 배열 크기
        map = new int[n][n];
        //map 할당
        for (int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bw.write(String.valueOf(solve(n, 0,0,n-1,n-1)));

        bw.flush();
        bw.close();
        br.close();
    }
    static int solve(int n, int sx, int sy, int ex, int ey){
        if (n==1){
            //1칸인 경우 해당 숫자 반환
            return map[sy][sx];
        }

        int div = n/2;
        int mx = (sx+ex) / 2;
        int my = (sy+ey) / 2;
        int num[] = new int[4];

        //1사분면
        num[0] = solve(div, sx, sy, mx, my);
        //2사분면
        num[1] = solve(div, mx+1, sy, ex, my);
        //3사분면
        num[2] =solve(div, mx+1, my+1, ex, ey);
        //4사분면
        num[3] = solve(div, sx, my+1, mx, ey);

        //2번째로 큰수 반환
        Arrays.sort(num);
        return num[2];
    }
}