package day0322;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ16508_Sukyung {
	static char[] word;
	static int N, answer;
	static Book[] books;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		word = br.readLine().toCharArray();
		N = Integer.parseInt(br.readLine());
		books = new Book[N];
		answer = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int price = Integer.parseInt(st.nextToken());
			String title = st.nextToken();
			books[i] = new Book(price, title);
		}
		subSet(0, 0, "");
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}

	public static void subSet(int count, int price, String title) {
		if (count == N) {
			char[] copy = copyChar(word);
			for (int i = 0; i < title.length(); i++) {
				for (int j = 0; j < word.length; j++) {
					if (copy[j] == title.charAt(i)) {
						copy[j] = ' ';
						break;
					}
				}
			}
			boolean choice = true;
			for (int i = 0; i < word.length; i++) {
				if (copy[i] != ' ')
					choice = false;
			}
			if (choice)
				answer = Math.min(answer, price);
			return;
		}
		subSet(count + 1, price + books[count].price, title + books[count].title);
		subSet(count + 1, price, title);
	}

	public static char[] copyChar(char[] word) {
		char[] copy = new char[word.length];
		for (int i = 0; i < word.length; i++) {
			copy[i] = word[i];
		}
		return copy;
	}

	public static class Book {
		int price;
		String title;

		public Book(int p, String t) {
			price = p;
			title = t;
		}
	}
}