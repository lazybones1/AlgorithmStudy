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

        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t<=tc; t++){
            String command = br.readLine();
            int n = Integer.parseInt(br.readLine());

//            StringTokenizer st = new StringTokenizer(br.readLine(), "[],"); 사용시 편리함
            String string = br.readLine();
            String[] str = string.substring(1,string.length()-1).split(",");

            Deque<Integer> deque = new LinkedList<>();
            boolean direction = false;
            boolean success = true;

            for (int i = 0; i<n; i++){
                deque.add(Integer.parseInt(str[i]));
            }

            for (int i = 0; i<command.length(); i++){
                if(command.charAt(i) == 'R'){
                    direction = !direction;
                }else if(command.charAt(i) == 'D'){
                    if (deque.size() == 0){
                        success = false;
                        break;
                    }
                    else if(direction)
                        deque.pollLast();
                    else if(!direction)
                        deque.pollFirst();
                }
            }

            if (!success) bw.write("error\n");
            else {
                bw.write("[");
                while (!deque.isEmpty()){
                    if (direction)
                        bw.write(String.valueOf(deque.pollLast()));
                    else
                        bw.write(String.valueOf(deque.pollFirst()));
                    if(deque.size() != 0)
                        bw.write(",");
                }
                bw.write("]\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}