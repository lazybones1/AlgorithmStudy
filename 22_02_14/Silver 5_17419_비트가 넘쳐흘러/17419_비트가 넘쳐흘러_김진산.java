import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Main{
    static int people[], choice[];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        //(~num) : 1의 보수
        //~num + 1 : 2의 보수
        // num & 2의 보수 : 가장 작은 비트의 1 위치
        // num - (num & ((~num) + 1))의 가능 수는 1의 개수

        int n = Integer.parseInt(br.readLine());
        String k = br.readLine();
        int idx = 0;
        for (int i = 0; i<n; i++){
            if (k.charAt(i) == '1') idx++;
        }

        bw.write(String.valueOf(idx));
        bw.flush();
        bw.close();
        br.close();
    }
}