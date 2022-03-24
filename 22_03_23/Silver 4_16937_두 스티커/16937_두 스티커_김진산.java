import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


class Main{

    static int h, w, n, sticker[][], map[][], choose[];
    static int max;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        h = Integer.parseInt(st.nextToken()); //높이
        w = Integer.parseInt(st.nextToken()); //너비
        n = Integer.parseInt(br.readLine()); //스티커수
        sticker = new int[n][2]; //스티커 배열
        map = new int[h][w]; //스티커 확인용
        choose = new int[2]; //선택 스티커
        max = 0;

        //스티커 정보 입력
        for (int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            sticker[i][0] = Integer.parseInt(st.nextToken());
            sticker[i][1] = Integer.parseInt(st.nextToken());
        }

        chooseSticker(0,0);
        System.out.println(max);
        br.close();
    }

    //스티커 2개 선정
    static void chooseSticker(int cnt, int start){
        if (cnt == 2){
            attach();
            return;
        }
        for (int i = start; i<n; i++){
            choose[cnt] = i;
            chooseSticker(cnt+1, i+1);
        }
    }

    //붙이기
    static void attach(){
        //높이 합이 작은 경우, 높이+너비합이 작은 경우, 너비합이 작은경우 가능
        if (sticker[choose[0]][0] + sticker[choose[1]][0] <= h && Math.max(sticker[choose[0]][1], sticker[choose[1]][1]) <=w){
            cal();
        }else if(sticker[choose[0]][0] + sticker[choose[1]][1] <= h && Math.max(sticker[choose[0]][1], sticker[choose[1]][0]) <=w){
            cal();
        }else if(sticker[choose[0]][1] + sticker[choose[1]][0] <= h && Math.max(sticker[choose[0]][0], sticker[choose[1]][1]) <=w){
            cal();
        }else if (sticker[choose[0]][1] + sticker[choose[1]][1] <= h && Math.max(sticker[choose[0]][0], sticker[choose[1]][0]) <=w){
            cal();
        }else if(sticker[choose[0]][0] + sticker[choose[1]][1] <= w && Math.max(sticker[choose[0]][1], sticker[choose[1]][0]) <=h){
            cal();
        }else if(sticker[choose[0]][1] + sticker[choose[1]][0] <= w && Math.max(sticker[choose[0]][0], sticker[choose[1]][1]) <=h){
            cal();
        }else if (sticker[choose[0]][1] + sticker[choose[1]][1] <= w && Math.max(sticker[choose[0]][0], sticker[choose[1]][0]) <=h){
            cal();
        }else if (sticker[choose[0]][0] + sticker[choose[1]][0] <= w && Math.max(sticker[choose[0]][1], sticker[choose[1]][1]) <=h){
            cal();
        }
    }

    static void cal(){
        int cnt = sticker[choose[0]][0] * sticker[choose[0]][1] + sticker[choose[1]][0] * sticker[choose[1]][1];
        max = Math.max(max, cnt);
    }
}