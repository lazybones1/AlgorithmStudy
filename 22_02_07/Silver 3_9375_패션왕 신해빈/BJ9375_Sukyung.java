package day0207;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BJ9375 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < TC; tc++) {
			int num = Integer.parseInt(br.readLine());
			Map<String, Integer> map = new HashMap<>();
			String category;
			int answer = 1;

			for (int i = 0; i < num; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				st.nextToken();
				category = st.nextToken();

				if (map.containsKey(category)) {
					map.put(category, map.get(category) + 1);
				} else {
					map.put(category, 1);
				}
			}
			for (Integer count : map.values()) {
				answer *= (count + 1);
			}
			System.out.println(answer - 1);
		}
	}
}
