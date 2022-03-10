import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Main {

    static boolean visited[];
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visited = new boolean[200001];

        bw.write(String.valueOf(bfs()));
        bw.flush();
        bw.close();
        br.close();
    }

    public static int bfs(){
        Queue<Integer> queue = new LinkedList<>();
        int level = 0;
        visited[N] = true;
        queue.offer(N);

        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i<size; i++){
                int num = queue.poll();

                do{
                    visited[num] = true;
                    if (num == K){
                        return level;
                    }
                    if (num > 0 && !visited[num-1]){
                        visited[num-1] = true;
                        queue.offer(num-1);
                    }
	//순간이동은 0초
                    if (num+1<=K && !visited[num+1]){
                        visited[num+1] = true;
                        queue.offer(num+1);
                    }
                    num = num*2;

                }while (num!=0 && num < K*2);
            }
            level++;
        }
        return -1;
    }
}