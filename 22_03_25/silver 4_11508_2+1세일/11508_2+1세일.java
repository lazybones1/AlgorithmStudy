import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


class Main{

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        Integer C[] = new Integer[N];

        for (int i = 0; i<N; i++){
            C[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(C, (o1, o2) -> o2 - o1);

        int ans = 0;

        for (int i = 0; i<N; i++){
            if(i%3 == 2) continue;
            ans+=C[i];
        }

        System.out.println(ans);
        br.close();
    }

}