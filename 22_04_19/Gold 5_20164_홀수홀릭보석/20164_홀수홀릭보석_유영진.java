package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_20164_홀수홀릭보석 {
	static int n;
    static int min;
    static int max;
    public static void main(String[] args) throws IOException {
    	
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(bf.readLine());
        min = Integer.MAX_VALUE;
        max = 0;
        func(n, countOdd(n));
        System.out.println(min+" "+max);
        
    }

    static void func(int num, int cnt) {
        
    	// 한 자리
        if (num <= 9) {
            min = Math.min(min, cnt);
            max = Math.max(max, cnt);
            return;
        }

        // 두 자리
        if (num <= 99) {
            int next = (num / 10) + (num % 10);
            func(next, countOdd(next) + cnt);
        }

        // 세 자리 이상
        String s = Integer.toString(num);

        for (int i = 0; i < s.length() - 2; i++) {
            for (int j = i + 1; j < s.length() - 1; j++) {
                String x1 = s.substring(0, i + 1);
                String x2 = s.substring(i + 1, j + 1);
                String x3 = s.substring(j + 1, s.length()); 

                int nx = Integer.parseInt(x1) + Integer.parseInt(x2) + Integer.parseInt(x3);
                func(nx, countOdd(nx) + cnt);
                
            }
        }
    }

   
    static int countOdd(int x) {
        int ret = 0;
        while (x > 0) {
            int digit = x % 10;
            if (digit % 2 == 1) ret++;
            x /= 10;
        }
        return ret;
    }
}
