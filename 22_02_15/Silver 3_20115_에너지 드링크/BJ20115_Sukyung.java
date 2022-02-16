package day0215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ20115_Sukyung {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		double[] energyDrinks = new double[N];

		for (int i = 0; i < N; i++) {
			energyDrinks[i] = Double.parseDouble(st.nextToken());
		}
		Arrays.sort(energyDrinks);

		double makeOne = 0;
		int start = 0;
		int end = N - 1;
		while (true) {
			if (start == end) {
				if ((energyDrinks[end] - (int) energyDrinks[end]) != 0.0)
					System.out.println(energyDrinks[end]);
				else
					System.out.println((int) energyDrinks[end]);
				break;
			}
			makeOne = energyDrinks[start] / 2 + energyDrinks[end];
			energyDrinks[end] = makeOne;
			start++;
		}
	}
}
