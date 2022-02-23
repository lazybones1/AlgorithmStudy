package day0217;

import java.util.Scanner;

public class Main_1182_부분수열의합 {
	static int[] arr;
	static int n, s;
	static int cnt;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		s = sc.nextInt();
		cnt=0;
		arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		
		fun(0,0);
		if(s==0) cnt-=1;
		System.out.println(cnt);
	}

	static void fun(int idx,int sum) {

		if (idx == n) {
			if (sum == s) {
				cnt++;	
			}
			return;
		}
		fun(idx+1,sum+arr[idx]);
		fun(idx+1,sum);
	}
}
