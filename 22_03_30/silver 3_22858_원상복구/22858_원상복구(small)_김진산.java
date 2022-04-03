import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int s[] = new int[n+1]; //k번 섞은 후 배치
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i<=n; i++){
            s[i] = Integer.parseInt(st.nextToken());
        }

        int d[] = new int[n+1]; //카드 섞기 정보
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i<=n; i++){
            d[i] = Integer.parseInt(st.nextToken());
        }

        ///카드 되돌리기 작업
        int p[] = new int[n+1];
        for (int i = 0; i<k; i++){
            for (int j = 1; j<=n; j++){ //p[j] 구하기                
                p[d[j]] = s[j];
            }

            for (int j = 1; j<=n; j++){ //s에 p 복사
                s[j] = p[j];
            }
        }

        for (int j = 1; j<=n; j++){
            System.out.print(p[j] + " ");
        }
        br.close();
    }
}