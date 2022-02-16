import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Main {
    static int n, ans;
    static Dish[] dishes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        dishes = new Dish[n];
        ans = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            dishes[i] = new Dish(s, b);
        }

        solve(0, 0, 0, 1, 0);

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }

    public static void solve(int start, int cnt, int flag, int s, int b) {
        if (cnt != 0) ans = Math.min(ans, Math.abs(s - b));
        if (cnt == n) {
            return;
        }
        for (int i = start; i < n; i++) {
            if ((flag & 1 << i) != 0) continue;
            solve(start+1, cnt + 1, flag | 1 << i, s * dishes[i].s, b + dishes[i].b);
        }
    }

    static class Dish {
        int b;
        int s;

        public Dish(int s, int b) {
            {
                this.s = s;
                this.b = b;
            }
        }
    }
}
