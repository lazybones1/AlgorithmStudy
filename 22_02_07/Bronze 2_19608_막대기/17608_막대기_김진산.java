import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(br.readLine());
        Stack<Integer> stick = new Stack<>();
        int ans = 1;
        for(int i = 0; i<n; i++){
            stick.push(Integer.parseInt(br.readLine()));
        }
        int right = stick.peek();

        while (!stick.empty()){
            int now = stick.pop();
            if(now > right){
                ans++;
                right = now;
            }
        }
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}