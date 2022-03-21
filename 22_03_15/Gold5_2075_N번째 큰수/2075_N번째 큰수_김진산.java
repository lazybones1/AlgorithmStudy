import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.util.Arrays.sort;

class Main{

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int map[][] = new int[n][n];

        //내림차순 정렬 정수 우선순위큐
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j<n; j++){
                pq.offer(Integer.parseInt(st.nextToken()));
            }
        }

        int ans = 0;
        for(int i = 0; i<n; i++){
            ans =pq.poll();
        }
        System.out.println(ans);
        br.close();
    }
}