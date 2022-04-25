package day0421;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1713_Sukyung {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Picture[] pictures = new Picture[N];
		for (int i = 0; i < N; i++) {
			pictures[i] = new Picture(0, 0);
		}
		int vote = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		for (int v = 0; v < vote; v++) {
			int num = Integer.parseInt(st.nextToken());

			boolean old = false;
			for (int i = 0; i < N; i++) {
				if (pictures[i].num == num) {// 후보에 있을 경우
					pictures[i].vote += 1; // 추천수 증가
					old = true;
					break;
				}
			}
			if (!old) { // 후보가 없을 경우
				int min = Integer.MAX_VALUE; // 삭제될 후보 고르기
				for (int i = 0; i < N; i++) {
					min = Math.min(pictures[i].vote, min);
				}
				for (int i = 0; i < N - 1; i++) {
					if (pictures[i].vote == min) { // 새로운 후보 넣기 위해 당기기
						for (int j = i; j < N - 1; j++) {
							pictures[j] = pictures[j + 1];
						}
						break;
					}
				}
				pictures[N - 1] = new Picture(num, 1); // 마지막에 새로운 후보 넣기
			}
		}
		Arrays.sort(pictures);
		for (int i = 0; i < N; i++) {
			if (pictures[i].num != 0)
				System.out.print(pictures[i].num + " ");
		}
	}

	public static class Picture implements Comparable<Picture> {
		int num, vote;

		public Picture(int n, int v) {
			num = n;
			vote = v;
		}

		@Override
		public int compareTo(Picture o) {
			return this.num - o.num;
		}
	}
}
