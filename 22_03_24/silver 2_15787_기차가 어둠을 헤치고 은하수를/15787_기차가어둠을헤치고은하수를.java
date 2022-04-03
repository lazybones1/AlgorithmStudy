import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;


class Main{
    


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int train[][] = new int[N+1][21];
        boolean visites[] = new boolean[N+1];

        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int cmd = Integer.parseInt(st.nextToken());
            int t = 0;
            int x = 0;
            switch (cmd){
                case 1:
                    t = Integer.parseInt(st.nextToken());
                    x = Integer.parseInt(st.nextToken());
                    if (train[t][x] == 0) train[t][x] = 1;
                    break;
                case 2:
                    t = Integer.parseInt(st.nextToken());
                    x = Integer.parseInt(st.nextToken());
                    if (train[t][x] == 1) train[t][x] = 0;
                    break;
                case 3:
                    t = Integer.parseInt(st.nextToken());
                    train[t][0] = 0;
                    for (int j = 20; j>0; j--){
                        train[t][j] = train[t][j-1];
                    }
                    break;
                case 4:
                    t = Integer.parseInt(st.nextToken());
                    for (int j = 0; j<20; j++){
                        train[t][j] = train[t][j+1];
                    }
                    train[t][0] = 0;
                    train[t][20] = 0;
                    break;
            }
        }
        Set<String> set = new HashSet<>();
        for(int i = 1; i<=N; i++){
            set.add(Arrays.toString(train[i]));
        }

        System.out.println(set.size());
        br.close();
    }

}