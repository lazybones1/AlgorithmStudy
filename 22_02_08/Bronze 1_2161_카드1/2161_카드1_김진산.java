import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        Queue<Integer> q = new LinkedList<>();
        int n = Integer.parseInt(br.readLine());
        for (int i = 1; i<=n; i++){
            q.add(i);
        }
        while (!q.isEmpty()){
            System.out.print(q.poll() + " ");
            if(!q.isEmpty()){
                int num = q.poll();
                q.add(num);
            }
        }


        bw.flush();
        bw.close();
        br.close();
    }
}