package day0216;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ17829_Sukyung {
	static int[][] CNN;
	static int[][] maxPoolCNN;
	static int[] four;
	static int count;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		CNN = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				CNN[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int size = N;
		count = 0;
		four = new int[4];
		while (size != 0) {
			maxPoolCNN = new int[size / 2][size / 2];
			pooling(0, 0, size, size);
			CNN = maxPoolCNN;
			if (CNN.length == 1) {
				System.out.println(CNN[0][0]);
				break;
			}
			size /= 2;
		}
	}

	private static void pooling(int si, int sj, int ei, int ej) {
		if (ei - si == 1) {
			four[count++] = CNN[si][sj];
			if (count == 4) {
				Arrays.sort(four);
				maxPoolCNN[si / 2][sj / 2] = four[2];
				count = 0;
				four = new int[4];
			}
			return;
		}
		int mi = (si + ei) / 2;
		int mj = (sj + ej) / 2;

		pooling(si, sj, mi, mj);
		pooling(si, mj, mi, ej);
		pooling(mi, sj, ei, mj);
		pooling(mi, mj, ei, ej);
	}
}
