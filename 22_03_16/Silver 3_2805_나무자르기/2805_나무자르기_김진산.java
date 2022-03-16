import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.util.Arrays.sort;

class Main{

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        long wood[] = new long[n];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i<n; i++){
            wood[i] = Integer.parseInt(st.nextToken());
        }
        //이진탐색: 정렬된 배열을 바탕으로 수행
        sort(wood);

        //이진 탐색
        long s = 0, e = wood[n-1];
        int ans = 0;

        while(s<=e){
            int mid = (int)(s+e)/2;
            long count = 0;
            for(int i = 0; i<n; i++){
                if(wood[i] - mid <= 0) continue;
                else count += wood[i] - mid;
            }
            if(count>=m){
                ans = Math.max(ans, mid);
                s = mid +1;
            }else{
                e = mid -1;
            }
        }

        System.out.println(ans);
        br.close();
    }
}