package day0207;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1759 {
	static int L;
	static int C;
	static String[] word;
	static String[] password;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		sb = new StringBuilder();

		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine(), " ");
		word = new String[C];
		password = new String[L];

		for (int i = 0; i < C; i++) {
			word[i] = st.nextToken();
		}
		Arrays.sort(word);
		combination(0, 0);
		System.out.println(sb);
	}

	public static void combination(int start, int count) {
		if (count == L) {
			int ja = 0;
			int mo = 0;
			for (int i = 0; i < L; i++) {
				if (password[i].equals("a") || password[i].equals("e") || password[i].equals("i")
						|| password[i].equals("o") || password[i].equals("u"))
					mo++;
				else
					ja++;
			}
			if (mo >= 1 && ja >= 2) {
				for (int i = 0; i < L; i++) {
					sb.append(password[i]);
				}
				sb.append("\n");
			}
			return;
		}
		for (int i = start; i < C; i++) {
			password[count] = word[i];
			combination(i + 1, count + 1);
		}
	}
}
