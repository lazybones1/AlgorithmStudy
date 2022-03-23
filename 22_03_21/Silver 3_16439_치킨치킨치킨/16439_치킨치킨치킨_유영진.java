package week5;

import java.io.*;
import java.util.*;

public class Main_16439_치킨치킨치킨 { //이분탐색 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = stoi(st.nextToken());
		int M = stoi(st.nextToken());
		int arr[] = new int[M];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = stoi(st.nextToken());
		}
		int l=1;
		int r=1000000000;
		int answer = 1000000000;
		
		//이분탐색 
		while(l<=r) {
			int m = (l+r)/2;
			int res =  binary(arr, m);
			if(res <= N) {
				r=m-1;
				answer = Math.min(m, answer);
			}else if(res > N){
				l= m+1;
			}
		}
		System.out.println(answer);
	}

	static int binary(int arr[], int m) { 
		int res = 0;
		for (int i = 0; i < arr.length; i++) {
			int mok = arr[i] / m;
			if (arr[i] % m == 0) {
				res += mok;
			} else {
				res += (mok + 1);
			}
		}
		return res;
	}

	static int stoi(String s) {
		return Integer.valueOf(s);
	}
}