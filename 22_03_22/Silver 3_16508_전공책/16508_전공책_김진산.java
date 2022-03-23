import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;


class Main{

    static String t;
    static int n, ans;
    static Book[] book;
    static boolean visited[];
    static int alpha[], count[];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        t = st.nextToken();
        n = Integer.parseInt(br.readLine());
        ans = Integer.MAX_VALUE;

        visited = new boolean[n]; //방문여부 체크용
        book = new Book[n]; //전공책 배열
        alpha = new int[27]; //원하는 문자
        count = new int[27]; //선택된 문자

        for(int i = 0; i<t.length(); i++){
            alpha[t.charAt(i)-'A']++;
        }

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            book[i] =  new Book(Integer.parseInt(st.nextToken()), st.nextToken());
        }

        comb(0, 0);

        if (ans == Integer.MAX_VALUE)
            System.out.println(-1);
        else{
            System.out.println(ans);
        }

        br.close();
    }

    public static void comb(int cnt, int price) {
        if (cnt == n){
            if (check()){
                ans = Math.min(ans, price);
            }
            return;
        }

        //부분집합으로 선택 전공책
        //선택
        for(int j = 0; j<book[cnt].title.length(); j++){
            count[book[cnt].title.charAt(j) - 'A']++;
        }
        comb(cnt+1, price + book[cnt].price);

        //선택 안함
        for(int j = 0; j<book[cnt].title.length(); j++){
            count[book[cnt].title.charAt(j) - 'A']--;
        }
        comb(cnt+1, price);
    }

    //만들기 가능 여부 확인
    public static boolean check(){
        for (int i = 0; i<26; i++){
            if(count[i] < alpha[i]){
                return false;
            }
        }
        return true;
    }

    static class Book implements Comparable<Book>{
        int price;
        String title;
        public Book(int p, String t){
            this.price = p;
            this.title = t;
        }

        @Override
        public int compareTo(Book o) {
            return price - o.price;
        }
    }

}