package day0210;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1012_¿Ø±‚≥ÛπË√ﬂ {
	static int t;
	static int m, n, k;
	static int[][] arr;
	static boolean[][] visit;
	static int ans;

	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(bf.readLine());
		for (int tc = 0; tc < t; tc++) {
			ans=0;
			StringTokenizer st = new StringTokenizer(bf.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());

			arr = new int[n][m];
			visit = new boolean[n][m];
			for (int i = 0; i < k; i++) {
				StringTokenizer str = new StringTokenizer(bf.readLine());
				int x = Integer.parseInt(str.nextToken());
				int y = Integer.parseInt(str.nextToken());
				arr[y][x] = 1;
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (arr[i][j] == 1 && !visit[i][j]) {
						dfs(i, j);
						ans++;
					}
				}
			}
			System.out.println(ans);
		}

	}

	static void dfs(int nowi, int nowj) {
		visit[nowi][nowj]=true;
		
		for(int d=0;d<4;d++) {
			int nexti = nowi + di[d];
			int nextj = nowj + dj[d];
			
			if(nexti>=0&&nextj>=0&&nexti<n
					&&nextj<m&&arr[nexti][nextj]==1&&!visit[nexti][nextj]) {
				dfs(nexti,nextj);
			}
		}
	}

}
