import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        char[] str = br.readLine().toCharArray();
        int bcnt = 0; //연속되지 않는 파랑색
        int rcnt = 0; //연속되지 않는 빨간색

        for (int i = 0; i<str.length; i++){
            char color = str[i];
            if(i==0){ //시작점
                if(color == 'B') bcnt++;
                else rcnt++;
            }else{
                if(str[i-1]==color) continue; //연속된 경우 패스
                if(color == 'B') bcnt++;
                else rcnt++;
            }
        }
        System.out.println(bcnt > rcnt ? rcnt+1 : bcnt +1);
        br.close();
    }
}