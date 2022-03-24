import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pQueue = new PriorityQueue<>(Collections.reverseOrder());

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                pQueue.offer(Integer.parseInt(st.nextToken()));
            }
        }
        for (int i = 0; i < N - 1; i++) {
            pQueue.poll();
        }
        System.out.println(pQueue.poll());
    }

}