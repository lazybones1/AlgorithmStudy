import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        /*
        문제 : A를 B로 바꾸기
        가능한 연산 : *2 또는 가장 오른쪽에 1추가
         */
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        //최단거리이므로 BFS 사용
        System.out.println(BFS(A, B));

        bw.flush();
        bw.close();
        br.close();
    }

    public static int BFS(int a, int b){
        //BFS 사용
        //최단 거리 찾기
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(a);
        //거리 측정을 위한 변수 선언 및 초기화
        int cnt = 0; //현재 거리 측정용
        int tmpcnt = 0; //다음 거리 측정용
        int idx = 1; //거리
        //중복 방문 방지를 위해 visited 선언
        boolean[] visited = new boolean[b+1];

        while (!queue.isEmpty()){
            int tmp = queue.poll();
            //정답 찾은 경우 idx 리턴
            if (tmp == b) {
                return idx;
            }
            //*2 연산 가능 확인 후 큐에 추가
            int num = tmp*2;
            if (num <= b && !visited[num]){
                visited[num] = true;
                queue.offer(num);
                tmpcnt++;
            }
            //+1 연산 가능 확인 후 큐에 추가
            long num2 = (long)tmp * 10 +1; //오버플로우 방지 (b = 1,000,000,000 인 경우)
            if (num2 <= b && !visited[(int)num2]){
                visited[(int)num2] = true;
                queue.offer((int)(num2));
                tmpcnt++;
            }
            //현재 거리 종료 거리 측정을 위한 변수 재 할당
            if (--cnt <= 0){
                cnt = tmpcnt;
                tmpcnt = 0;
                idx++;
            }
        }
        //경우의 수가 없는 경우 -1 리턴
        return -1;
    }
}