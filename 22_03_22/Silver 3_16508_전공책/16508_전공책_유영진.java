package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



public class Main_16508_전공책 {

	public static List<Book> books = new ArrayList<>();

	public static String T;
	public static int[] count = new int[26];
	public static int[] select_cnt = new int[26];

	public static int N;
	public static int answer = 987654321;

	public static void main(String[] args) throws IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		T = bf.readLine();

		for (int i = 0; i < T.length(); i++) {
			count[T.charAt(i) - 'A']++;
		}

		N = Integer.parseInt(bf.readLine());

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			
			books.add(new Book(Integer.parseInt(st.nextToken()), st.nextToken()));
		}

		dfs(0, 0);
		System.out.println(answer == 987654321 ? -1 : answer);
	}

	public static void dfs(int index, int total) {
		if (index == N) {
			if (check())
				answer = Math.min(answer, total);
			return;
		}

		for (int i = 0; i < books.get(index).getTitle().length(); i++)
			select_cnt[books.get(index).getTitle().charAt(i) - 'A']++;

		dfs(index + 1, total + books.get(index).getPrice());

		for (int i = 0; i < books.get(index).getTitle().length(); i++)
			select_cnt[books.get(index).getTitle().charAt(i) - 'A']--;

		dfs(index + 1, total);

	}

	public static boolean check() {
		for (int i = 0; i < 26; i++)
			if (count[i] > select_cnt[i])
				return false;
		return true;
	}

}

class Book {
	int price;
	String title;

	public Book(int price, String title) {
		this.price = price;
		this.title = title;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
