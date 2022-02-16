import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        /*
        tc : 테스트 케이스 수
        closes : 의상을 저장할 map
        n : 의상의 수
        name : 의상 이름
        type : 의상 종류
        ans : 조합 가지수
         */
        int tc = Integer.parseInt(br.readLine());
        Map<String, Integer> closes;

        for(int t = 0; t<tc; t++){
            int n = Integer.parseInt(br.readLine());
            closes = new HashMap<>();
            int ans = 0;

            for (int i = 0; i<n; i++){
                st = new StringTokenizer(br.readLine(), " ");
                String name = st.nextToken();
                String type = st.nextToken();
                //이미 있는경우 숫자만(key) 만 증가
                if(closes.get(type) == null){
                    closes.put(type, 1);
                }else{
                    closes.put(type, closes.get(type)+1);
                }
            }

            if(closes.size() == 0){
                //의상이 없는 경우
                ans = 0;
            }else{
                //(각 의상별 수 +1)의 곱들 -1(아무것도 안입는 경우)
                ans = 1;
                for (String s : closes.keySet()){
                    ans *= closes.get(s) + 1;
                }
                ans -= 1;
            }
            bw.write(String.valueOf(ans)+"\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}